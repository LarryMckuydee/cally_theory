package app.services;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import app.models.CallRequest;
import app.models.Employee;
import app.queues.JobQueues;

public class CallHandler {
    // pick a request and assign to queue
    private Employee employee;
    private CallRequest callRequest;
    private JobQueues jobQueues;
    private EmployeeCollection employees;

    public CallHandler(JobQueues jobQueues, EmployeeCollection employees) {
        this.jobQueues = jobQueues;
        this.employees = employees;
    }

    public void processCall(Employee employee) {
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
            // dispatch to higher level queue
            dispatchCallRequestToQueueLevel(callRequest, employee.getLevel() + 1);

            return;
        }

        // if is level 3 (PM) and still unable to resolve, will be mark as can't be solve
        callRequest.unresolved(employee);        
        callRequest.setAsCantBeSolve();
    }

    public void dispatchCallRequestToQueueLevel(CallRequest callRequest, int level) {
        // if it is not level 3 (PM) and size for current level queue is higher or equal to available size
        do {
            if (jobQueues.getQueueByLevel(level).size() >= employees.getAvailableEmployeesByLevel(level).size()) {
                level++;
                continue;
            }
            
            System.out.println("Current Thread ID- " + Thread.currentThread().getId() + " level " + level);
            jobQueues.getQueueByLevel(level).add(callRequest);
            callRequest.setAsIsProcessed();
            System.out.println("CallRequest: " + callRequest.getUUID() + " been dispatch to queue level: " + level);
            break;
        } while (level <= 3);
        // if (level < 3 && queueMap.get(level).size() >= employees.getAvailableEmployeesByLevel(level).size()) {
        //     dispatchToQueueLevel(level++);
        // } else {
        //     queueMap.get(level).add(callRequest);
        // }
    }

    private int rollDice() {
        Random rand = new Random();
        return rand.nextInt(6 - 1 + 1) + 1;
    }
}