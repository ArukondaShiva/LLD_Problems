package org.example._08_elevator_system.repository.impl;

import org.example._08_elevator_system.domain.InternalRequest;
import org.example._08_elevator_system.domain.RequestStatus;
import org.example._08_elevator_system.repository.InternalRequestRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InternalRequestRepositoryImpl implements InternalRequestRepository {

    private final Map<String,InternalRequest> requests = new ConcurrentHashMap<>();


    @Override
    public InternalRequest save(InternalRequest request) {
        requests.put(request.getId(),request);
        return request;
    }

    @Override
    public List<InternalRequest> findByElevator(String elevatorId) {
        return requests.values().stream()
                .filter(r->r.getElevatorId().equals(elevatorId))
                .collect(Collectors.toList());
    }

    @Override
    public List<InternalRequest> findPendingByElevator(String elevatorId) {
        return requests.values().stream()
                .filter(r->r.getElevatorId().equals(elevatorId))
                .filter(r->r.getStatus()==RequestStatus.PENDING)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<InternalRequest> findById(String requestId) {
        return Optional.ofNullable(requests.get(requestId));
    }

    @Override
    public List<InternalRequest> findAll() {
        return new ArrayList<>(requests.values());
    }

    @Override
    public void updateRequestStatus(String requestId, RequestStatus status) {
        InternalRequest request = requests.get(requestId);
        if(request!=null){
            request.setStatus(status);
        }
    }
}
