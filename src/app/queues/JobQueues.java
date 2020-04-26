package app.queues;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import app.models.CallRequest;

public class JobQueues {
    // customer service queue
    private ArrayBlockingQueue<CallRequest> csQueue;
    // technical lead queue
    private ArrayBlockingQueue<CallRequest> tlQueue;
    // product manager queue
    private ArrayBlockingQueue<CallRequest> pmQueue;

    private HashMap<Integer, ArrayBlockingQueue<CallRequest>> queueMap;

    public JobQueues() {
        this.csQueue = new ArrayBlockingQueue<CallRequest>(15);
        this.tlQueue = new ArrayBlockingQueue<CallRequest>(15);
        this.pmQueue = new ArrayBlockingQueue<CallRequest>(15);
        this.queueMap = new HashMap<Integer, ArrayBlockingQueue<CallRequest>>();
    }

    public void initialize() {
        this.queueMap.put(1, csQueue);
        this.queueMap.put(2, tlQueue);
        this.queueMap.put(3, pmQueue);
    }

    public ArrayBlockingQueue<CallRequest> customerServiceQueue() {
        return this.csQueue;
    }

    public ArrayBlockingQueue<CallRequest> technicalLeadQueue() {
        return this.tlQueue;
    }

    public ArrayBlockingQueue<CallRequest> productManagerQueue() {
        return this.pmQueue;
    }

    public ArrayBlockingQueue<CallRequest> getQueueByLevel(int level) {
        return this.queueMap.get(level);
    }

    public boolean isAllQueuesEmpty() {
        return (this.csQueue.size() < 1 && this.tlQueue.size() < 1 && this.pmQueue.size() < 1);
    }
}