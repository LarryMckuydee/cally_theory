package app.queues;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import app.models.CallRequest;

public class JobQueues {
    // customer service queue
    private Queue<CallRequest> csQueue;
    // technical lead queue
    private Queue<CallRequest> tlQueue;
    // product manager queue
    private Queue<CallRequest> pmQueue;

    private HashMap<Integer, Queue<CallRequest>> queueMap;

    public JobQueues() {
        this.csQueue = new LinkedList<CallRequest>();
        this.tlQueue = new LinkedList<CallRequest>();
        this.pmQueue = new LinkedList<CallRequest>();
        this.queueMap = new HashMap<Integer, Queue<CallRequest>>();
    }

    public void initialize() {
        this.queueMap.put(1, csQueue);
        this.queueMap.put(2, tlQueue);
        this.queueMap.put(3, pmQueue);
    }

    public Queue<CallRequest> customerServiceQueue() {
        return this.csQueue;
    }

    public Queue<CallRequest> technicalLeadQueue() {
        return this.tlQueue;
    }

    public Queue<CallRequest> productManagerQueue() {
        return this.pmQueue;
    }

    public Queue<CallRequest> getQueueByLevel(int level) {
        return this.queueMap.get(level);
    }

    public boolean isAllQueuesEmpty() {
        return (this.csQueue.size() < 1 && this.tlQueue.size() < 1 && this.pmQueue.size() < 1);
    }
}