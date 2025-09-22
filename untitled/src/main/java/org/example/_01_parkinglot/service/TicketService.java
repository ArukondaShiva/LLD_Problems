package org.example._01_parkinglot.service;

import org.example._01_parkinglot.domain.Ticket;
import org.example._01_parkinglot.domain.Vehicle;
import org.example._01_parkinglot.repository.TicketRepository;

import java.util.Optional;
import java.util.UUID;

public class TicketService {

    private TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }


    public Ticket generateTicket(Vehicle vehicle, UUID slotId){
        System.out.println("[SERVICE] Generating ticket for vehicle: " + vehicle.getLicensePlate());

        Ticket ticket = new Ticket(vehicle.getId(), slotId);
        ticketRepository.save(ticket);

        System.out.println("[SERVICE] Ticket generated successfully: " + ticket.getId());
        return ticket;
    }


    public Optional<Ticket> getTicket(UUID ticketId) {
        System.out.println("[SERVICE] Retrieving ticket: " + ticketId);
        return ticketRepository.findById(ticketId);
    }

    public void deactivateTicket(UUID ticketId) {
        System.out.println("[SERVICE] Deactivating ticket: " + ticketId);
        ticketRepository.deactivateTicket(ticketId);
    }


}
