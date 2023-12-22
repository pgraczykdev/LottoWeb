package pl.graczyk.domain.numberreceiver;

import java.util.List;
import java.time.LocalDateTime;

public interface NumberReceiverRepository {

    Ticket save(Ticket ticket);

    List<Ticket> findAllTicketsByDrawDate(LocalDateTime date);
}
