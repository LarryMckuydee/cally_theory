package app.services;

import app.models.CallRequest;
import app.queues.JobQueues;
import app.models.collections.EmployeeCollection;

public class ReceiveCall implements Runnable {
    private JobQueues jobQueues;
    private CallRequest callRequest;
    private EmployeeCollection employees;
    
    public ReceiveCall(CallRequest callRequest, JobQueues jobQueues, EmployeeCollection employees) {
        this.callRequest = callRequest;
        this.jobQueues = jobQueues;
        this.employees = employees;
    }

    /** 
     * Dispatch callRequest into level 1 queue
     */
    @Override
    public void run() {
        synchronized(this) {
            CallHandler ch = new CallHandler(jobQueues, employees);
            ch.dispatchCallRequestToQueueLevel(callRequest, 1);
        }
    }
}