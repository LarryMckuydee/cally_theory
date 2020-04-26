package app.services;

import java.util.HashMap;
import java.util.Queue;
import java.util.Random;

import app.models.CallRequest;
import app.models.Employee;
import app.queues.JobQueues;

public class ResponseCall implements Runnable {
    private JobQueues jobQueues;
    private CallRequest callRequest;
    private Queue<CallRequest> currentQueue;
    private EmployeeCollection employees;
    private Employee employee;
    
    public ResponseCall(Employee employee, JobQueues jobQueues, EmployeeCollection employees) {
        this.employee = employee;
        this.jobQueues = jobQueues;
        this.employees = employees;
    }

    public void processCall() {
        CallRequest callRequest = jobQueues.getQueueByLevel(employee.getLevel()).poll();

        // if nothing in queue, do nothing
        if (callRequest == null) {
            return;
        }

        callRequest.assignedTo(employee);
        
        // engaging call
        // if dice bigger than 3 problem solve, else problem is not solve
        if (rollDice() > 3) {
            solve(callRequest, employee);
        } else {
            failToSolve(callRequest, employee);
        }
    }

    private void solve(CallRequest callRequest, Employee employee) {
        // mark as resolve
        callRequest.resolved(employee);
    }

    private void failToSolve(CallRequest callRequest, Employee employee) {
        if (employee.getLevel() < 3) {
            // mark as not resolve and push to higher level of employee to handle
            callRequest.unresolved(employee);
            ReceiveCall rc = new ReceiveCall(callRequest, jobQueues, employees);
            // dispatch to higher level queue
            rc.dispatchToQueueLevel(employee.getLevel() + 1);

            return;
        }

        // if is level 3 (PM), will be mark as not resolve
        callRequest.unresolved(employee);        
    }
    private int rollDice() {
        Random rand = new Random();
        return rand.nextInt(6 - 1 + 1) + 1;
    }

    @Override
    public void run() {
        processCall();
    }
}