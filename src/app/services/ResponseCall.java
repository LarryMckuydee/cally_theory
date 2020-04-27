package app.services;

import app.models.Employee;
import app.queues.JobQueues;
import app.models.collections.EmployeeCollection;

public class ResponseCall implements Runnable {
    private JobQueues jobQueues;
    private EmployeeCollection employees;
    
    public ResponseCall(JobQueues jobQueues, EmployeeCollection employees) {
        this.jobQueues = jobQueues;
        this.employees = employees;
    }

    /** 
     * ProcessCall while the job queues is not empty
     */
    @Override
    public void run() {
        CallHandler ch = new CallHandler(jobQueues, employees);
        // while all there are still pending call in queues
        while(!jobQueues.isAllQueuesEmpty()) {
            // get available employee to attend call
            for(Employee employee: employees.getAvailableEmployees()) {
                ch.processCall(employee);
            }
        }
    }
}