package app.models;

import java.util.UUID;

public class CallRequest {
    private UUID uuid;
    private Employee resolvedBy;
    private boolean isResolved;
    private Employee assignedTo;
    private boolean isAssigned;

    public CallRequest() {
        this.isResolved = false;
        this.resolvedBy = null;
        this.assignedTo = null;
        this.isAssigned = false;
        this.uuid = UUID.randomUUID();
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public Employee getResolveBy() {
        return this.resolvedBy;
    }


    public Employee getAssignedTo() {
        return this.assignedTo;
    }

    public boolean isAssigned() {
        return this.isAssigned;
    }
    
    public boolean isResolved() {
        return this.isResolved;
    }

    public Employee assignedTo(Employee employee) {
        this.assignedTo = employee;
        employee.engaging();
        return employee;
    }

    public Employee resolved(Employee employee) {
        this.isResolved = true;
        this.resolvedBy = employee;
        employee.disengaged();
        System.out.println("CallRequest: " + getUUID() + " resolved by: " + employee.getName() + "#" + employee.getLevel());
        return employee;
    }

    public Employee unresolved(Employee employee) {
        this.isResolved = false;
        employee.disengaged();
        System.out.println("CallRequest: " + getUUID() + " unable to resolved by: " + employee.getName() + "#" + employee.getLevel());
        return employee;
    }
}