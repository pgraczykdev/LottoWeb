package pl.graczyk.domain.numberreceiver;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryNumberReceiverRepositoryTestImplementation implements NumberReceiverRepository {

    private final Map<String, Ticket> inMemoryDatabase = new ConcurrentHashMap<>();
    @Override
    public Ticket save(Ticket ticket) {
        inMemoryDatabase.put(ticket.getTicketId(), ticket);
        return ticket;
    }

    @Override
    public List<Ticket> findAllTicketsByDrawDate(LocalDateTime date) {
        return inMemoryDatabase.values()
                .stream()
                .filter(ticket -> ticket.getDrawDate().equals(date))
                .toList();
    }
}
