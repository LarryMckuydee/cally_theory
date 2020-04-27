package app.services;

import app.models.CallRequest;
import app.queues.JobQueues;
import app.models.collections.EmployeeCollection;
import app.models.collections.CallRequestCollection;

public class ReceiveCall implements Runnable {
    private JobQueues jobQueues;
    private CallRequestCollection callRequests;
    private EmployeeCollection employees;
    
    public ReceiveCall(CallRequestCollection callRequests, JobQueues jobQueues, EmployeeCollection employees) {
        this.callRequests = callRequests;
        this.jobQueues = jobQueues;
        this.employees = employees;
    }

    /** 
     * Dispatch all unprocessed CallRequest into level 1 queue
     */
    @Override
    public void run() {
        synchronized(this) {
            CallHandler ch = new CallHandler(jobQueues, employees);
            for(CallRequest callRequest: callRequests.getUnprocessCallRequests()) {
                ch.dispatchCallRequestToQueueLevel(callRequest, 1);
            }
        }
    }
}