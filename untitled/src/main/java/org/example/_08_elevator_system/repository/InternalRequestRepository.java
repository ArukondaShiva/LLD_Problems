package org.example._08_elevator_system.repository;

import org.example._08_elevator_system.domain.InternalRequest;
import org.example._08_elevator_system.domain.RequestStatus;

import java.util.List;
import java.util.Optional;

public interface InternalRequestRepository {

    InternalRequest save(InternalRequest request);
    List<InternalRequest> findByElevator(String elevatorId);
    List<InternalRequest> findPendingByElevator(String elevatorId);
    Optional<InternalRequest> findById(String requestId);
    List<InternalRequest> findAll();
    void updateRequestStatus(String requestId, RequestStatus status);


}
