package app.services;

import app.models.Employee;
import app.queues.JobQueues;

public class ResponseCall implements Runnable {
    private JobQueues jobQueues;
    private EmployeeCollection employees;
    
    public ResponseCall(JobQueues jobQueues, EmployeeCollection employees) {
        this.jobQueues = jobQueues;
        this.employees = employees;
    }

    @Override
    public void run() {
        CallHandler ch = new CallHandler(jobQueues, employees);
        while(!jobQueues.isAllQueuesEmpty()) {
            for(Employee employee: employees.getAvailableEmployees()) {
                ch.processCall(employee);
            }
        }
    }
}