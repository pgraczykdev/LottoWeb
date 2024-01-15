package pl.graczyk.domain.numberreceiver;

import pl.graczyk.domain.numberreceiver.dto.TicketDto;

public class TicketMapper {

    public static TicketDto mapFromTicket(Ticket ticket){
        return new TicketDto(ticket.getHash(), ticket.getDrawDate(), ticket.getNumbersFromUser());
    }
}
