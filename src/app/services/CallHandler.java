package app.services;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import app.models.CallRequest;
import app.models.Employee;

public class CallHandler implements Callable {
    // pick a request and assign to queue
    private Thread t;
    private Employee employee;
    private HashMap<Integer,Queue<CallRequest>> queueMap;
    private int level;
    private Queue<CallRequest> currentQueue;

    //public CallHandler(Employee employee, HashMap<Integer,Queue<CallRequest>> queueMap) {
    public CallHandler(CallRequest employee, HashMap<Integer,Queue<CallRequest>> queueMap) {
        //this.employee = employee;
        this.queueMap = queueMap;
        this.level = 1;
        this.currentQueue = queueMap.get(this.level);
    }

    private void dispatchCall(CallRequest callRequest, Queue<CallRequest> queue) {
        queue.add(callRequest);
    }
    
    private void processCall(Employee employee, Queue<CallRequest> queue) {
        CallRequest cr = queue.poll();
        cr.assignedTo(employee);
    }

    // private CallRequest processCall(CallRequest callRequest) {
    //     // if roll dice number bigger than 3 determine the issue resolve else if not resolve
    //     if (rollDice() > 3) {
    //         callRequest.resolved(employee);
    //     }     

    //     return callRequest;
    // }
    
    private int rollDice() {
        Random rand = new Random();
        return rand.nextInt(6 - 1 + 1) + 1;
    }

    private void solve(CallRequest callRequest) {
        callRequest.resolved(employee);
    }

    private void notSolve(CallRequest callRequest) {
        // failed to solve, push to 1 queue higher

    }

    @Override
    public Object call() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }
    // @Override
    // public void call() {
    //     // TODO Auto-generated method stub
    //     System.out.println("Assigning call for employee " + employee.getName());
    //     dispatchCall(callRequest, currentQueue);
    //     System.out.println("Done assigned for employee " + employee.getName());
    // }
}