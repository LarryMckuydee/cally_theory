package app.services;

import java.util.HashMap;
import java.util.Queue;

import app.models.CallRequest;

public class ReceiveCall implements Runnable {
    private HashMap<Integer,Queue<CallRequest>> queueMap;
    private CallRequest callRequest;
    private Queue<CallRequest> currentQueue;
    private EmployeeCollection employees;
    
    public ReceiveCall(CallRequest callRequest, HashMap<Integer,Queue<CallRequest>> queueMap, EmployeeCollection employees) {
        this.callRequest = callRequest;
        this.queueMap = queueMap;
        this.employees = employees;
    }

    public void dispatchToQueueLevel(int level) {
        // if it is not level 3 (PM) and size for current level queue is higher or equal to available size
        do {
            if (queueMap.get(level).size() >= employees.getAvailableEmployeesByLevel(level).size()) {
                level++;
                continue;
            }
            
            System.out.println("Current Thread ID- " + Thread.currentThread().getId() + " level " + level);
            queueMap.get(level).add(callRequest);
            break;
        } while (level <= 3);
        // if (level < 3 && queueMap.get(level).size() >= employees.getAvailableEmployeesByLevel(level).size()) {
        //     dispatchToQueueLevel(level++);
        // } else {
        //     queueMap.get(level).add(callRequest);
        // }
    }

    @Override
    public void run() {
        dispatchToQueueLevel(1);
    }
}