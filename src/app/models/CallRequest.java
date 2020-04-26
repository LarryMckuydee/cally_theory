package app.models;

import java.util.UUID;

public class CallRequest {
    private UUID uuid;
    private Employee resolvedBy;
    private boolean isResolved;
    private Employee assignedTo;
    private boolean isAssigned;
    private boolean cantBeSolve;
    private boolean isProcessed;

    public CallRequest() {
        this.isResolved = false;
        this.resolvedBy = null;
        this.assignedTo = null;
        this.isAssigned = false;
        this.cantBeSolve = false;
        this.isProcessed = false;
        this.uuid = UUID.randomUUID();
    }

    
    /** 
     * Return uuid
     * 
     * @return UUID
     */
    public UUID getUUID() {
        return this.uuid;
    }

    
    /** 
     * Return Employee that resolve this CallRequest
     * 
     * @return Employee
     */
    public Employee getResolveBy() {
        return this.resolvedBy;
    }


    
    /** 
     * Return Employee that have been assigned to engage with this CallRequest
     * 
     * @return Employee
     */
    public Employee getAssignedTo() {
        return this.assignedTo;
    }

    
    /** 
     * Return isAssigned
     * 
     * @return boolean
     */
    public boolean isAssigned() {
        return this.isAssigned;
    }
    
    
    /** 
     * Return isResolved
     * 
     * @return boolean
     */
    public boolean isResolved() {
        return this.isResolved;
    }

    
    /** 
     * Return cantBeSolve
     * 
     * @return boolean
     */
    public boolean cantBeSolve() {
        return this.cantBeSolve;
    }

    
    /** 
     * Return isProcessed
     * 
     * @return boolean
     */
    public boolean isProcessed() {
        return this.isProcessed;
    }

    /** 
     * Set cantBeSolve boolean to true
     */
    public void setAsCantBeSolve() {
        this.cantBeSolve = true;
        System.out.println("CallRequest: " + getUUID() + " is unsolvable.");
    }

    /** 
     * Set isProcessed boolean to true
     */
    public void setAsIsProcessed() {
        this.isProcessed = true;
    }

    
    /** 
     * Set assignedTo to a given Employee and set Employee isEngaging to true
     * 
     * @param employee
     * @return Employee
     */
    public Employee assignedTo(Employee employee) {
        this.assignedTo = employee;
        employee.engaging();
        return employee;
    }

    
    /** 
     * Set isResolved to true and resolvedBy to a given Employee and Employee isEngaging to false
     * 
     * @param employee
     * @return Employee
     */
    public Employee resolved(Employee employee) {
        this.isResolved = true;
        this.resolvedBy = employee;
        employee.disengaged();
        System.out.println("CallRequest: " + getUUID() + " resolved by: " + employee.getName() + "#" + employee.getLevel());
        return employee;
    }

    
    /** 
     * Set isResolved to false and Employee isEngaging to false
     * 
     * @param employee
     * @return Employee
     */
    public Employee unresolved(Employee employee) {
        this.isResolved = false;
        employee.disengaged();
        System.out.println("CallRequest: " + getUUID() + " unable to resolved by: " + employee.getName() + "#" + employee.getLevel());
        return employee;
    }
}