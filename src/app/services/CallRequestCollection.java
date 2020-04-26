package app.services;

import java.util.List;
import java.util.stream.Collectors;

import app.models.CallRequest;

public class CallRequestCollection {
    private List<CallRequest> callRequests;

    public CallRequestCollection(List<CallRequest> callRequests) {
        this.callRequests = callRequests;
    }

    public List<CallRequest> getUnprocessCallRequests() {
        return callRequests.stream().map(callRequest -> callRequest)
        .filter(callRequest -> !callRequest.isProcessed())
        .collect(Collectors.toList());
    }

    public List<CallRequest> getUnresolveCallRequests() {
        return callRequests.stream().map(callRequest -> callRequest)
        .filter(callRequest -> !callRequest.isResolved() && !callRequest.cantBeSolve())
        .collect(Collectors.toList());
    }

    public List<CallRequest> getResolveCallRequests() {
        return callRequests.stream().map(callRequest -> callRequest)
        .filter(callRequest -> callRequest.isResolved())
        .collect(Collectors.toList());
    }

    public List<CallRequest> getAllCallRequests() {
        return callRequests;
    }
}