package pl.graczyk.domain.numberreceiver;

import java.util.List;
import java.time.LocalDateTime;

public interface TicketRepository {

    Ticket save(Ticket ticket);

    Ticket findByHash(String hash);

    List<Ticket> findAllTicketsByDrawDate(LocalDateTime date);
}
