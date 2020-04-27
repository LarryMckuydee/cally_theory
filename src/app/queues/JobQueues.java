package app.queues;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import app.models.CallRequest;

public class JobQueues {
    private int capacity;
    // customer service queue
    private ArrayBlockingQueue<CallRequest> csQueue;
    // technical lead queue
    private ArrayBlockingQueue<CallRequest> tlQueue;
    // product manager queue
    private ArrayBlockingQueue<CallRequest> pmQueue;

    private HashMap<Integer, ArrayBlockingQueue<CallRequest>> queueMap;

    public JobQueues(int capacity) {
        this.capacity = capacity;
        this.csQueue = new ArrayBlockingQueue<CallRequest>(capacity);
        this.tlQueue = new ArrayBlockingQueue<CallRequest>(capacity);
        this.pmQueue = new ArrayBlockingQueue<CallRequest>(capacity);
        this.queueMap = new HashMap<Integer, ArrayBlockingQueue<CallRequest>>();
    }

    /** 
     * Initialize queue based on level where:
     * 1 => Customer Service Queue
     * 2 => Technical Lead Queue
     * 3 => Product Manager Queue
     */
    public void initialize() {
        this.queueMap.put(1, csQueue);
        this.queueMap.put(2, tlQueue);
        this.queueMap.put(3, pmQueue);
    }

    
    /** 
     * Return Customer Service Queue
     * 
     * @return ArrayBlockingQueue<CallRequest>
     */
    public ArrayBlockingQueue<CallRequest> customerServiceQueue() {
        return this.csQueue;
    }

    
    /** 
     * Return Technical Lead Queue
     * 
     * @return ArrayBlockingQueue<CallRequest>
     */
    public ArrayBlockingQueue<CallRequest> technicalLeadQueue() {
        return this.tlQueue;
    }

    
    /** 
     * Return Product Manager Queue
     * 
     * @return ArrayBlockingQueue<CallRequest>
     */
    public ArrayBlockingQueue<CallRequest> productManagerQueue() {
        return this.pmQueue;
    }

    
    /** 
     * Return queue based on level given
     * 
     * @param level
     * @return ArrayBlockingQueue<CallRequest>
     */
    public ArrayBlockingQueue<CallRequest> getQueueByLevel(int level) {
        return this.queueMap.get(level);
    }

    
    /** 
     * Return true if all the queues is empty (Customer Service Queue, Technical Lead Queue, Product Manager Queue)
     * 
     * @return boolean
     */
    public boolean isAllQueuesEmpty() {
        return (this.csQueue.size() < 1 && this.tlQueue.size() < 1 && this.pmQueue.size() < 1);
    }
}