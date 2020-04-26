package app;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import app.models.CallRequest;
import app.models.Employee;
import app.models.CustomerService;
import app.models.TechnicalLead;
import app.queues.JobQueues;
import app.models.ProductManager;
import app.services.CallRequestCollection;
import app.services.EmployeeCollection;
import app.services.ReceiveCall;
import app.services.ResponseCall;

public class App {
    public static void main(String[] args) throws Exception {
        CustomerService kelly = new CustomerService("Kelly", "kelly@gmail.com");
        CustomerService sam = new CustomerService("Sam", "sam@gmail.com");
        CustomerService sebastian = new CustomerService("Sebastian", "sebastian@gmail.com");
        CustomerService nina = new CustomerService("Nina", "nina@gmail.com");
        TechnicalLead larry = new TechnicalLead("Larry", "larry@gmail.com");
        ProductManager ashley = new ProductManager("Ashley", "ashley@gmail.com");
        
        Employee[] employees = {
            kelly,
            sam,
            sebastian,
            nina,
            larry,
            ashley
        };

        // kelly.engaging();
        // larry.engaging();

        EmployeeCollection employeeCollection = new EmployeeCollection(Arrays.asList(employees));
        List<Employee> employeeList = employeeCollection.getAvailableEmployees();

        employeeList.forEach(employee -> System.out.println("Available employee : " + employee.getName()));

        Queue<CallRequest> csQueue = new LinkedList<CallRequest>();
        Queue<CallRequest> tlQueue = new LinkedList<CallRequest>();
        Queue<CallRequest> pmQueue = new LinkedList<CallRequest>();

        HashMap<Integer, Queue<CallRequest>> queueMap = new HashMap<Integer, Queue<CallRequest>>();
        queueMap.put(1, csQueue);
        queueMap.put(2, tlQueue);
        queueMap.put(3, pmQueue);

        System.out.println(queueMap);

        JobQueues jq = new JobQueues();
        jq.initialize();
        System.out.println(jq.customerServiceQueue());


        CallRequest callRequest1 = new CallRequest();
        CallRequest callRequest2 = new CallRequest();
        CallRequest callRequest3 = new CallRequest();
        CallRequest callRequest4 = new CallRequest();
        CallRequest callRequest5 = new CallRequest();
        CallRequest callRequest6 = new CallRequest();
        CallRequest callRequest7 = new CallRequest();
        CallRequest callRequest8 = new CallRequest();
        CallRequest callRequest9 = new CallRequest();
        CallRequest callRequest10 = new CallRequest();
        CallRequest callRequest11 = new CallRequest();

        CallRequest[] callRequests = {
            callRequest1,
            callRequest2,
            callRequest3,
            callRequest4,
            callRequest5,
            callRequest6,
            callRequest7,
            callRequest8,
            callRequest9,
            callRequest10,
            callRequest11
        };

        CallRequestCollection callRequestCollection = new CallRequestCollection(Arrays.asList(callRequests));
        callRequestCollection.getAllCallRequests().forEach(cr -> System.out.println(cr.getUUID()));


        for(int i = 0; i < 3; i ++) {
            Thread receiveThread = new Thread(new ReceiveCall(callRequestCollection, jq, employeeCollection));
            receiveThread.start();
        };

        TimeUnit.SECONDS.sleep(5);

        for(int i = 0; i < 3; i ++) {
            Thread responseThread = new Thread(new ResponseCall(jq, employeeCollection));
            responseThread.start();
        }

        // CallRequest cr = csQueue.poll();
        // System.out.println("crpol " + cr.getUUID());
        // cr = csQueue.poll();
        // System.out.println("crpol " + cr.getUUID());


        System.out.println("Hello Java");

        TimeUnit.SECONDS.sleep(5);
        callRequestCollection.getAllCallRequests().forEach(cr -> System.out.println("cr: " + cr.getUUID() + " process:" + cr.isProcessed()));
    }
}