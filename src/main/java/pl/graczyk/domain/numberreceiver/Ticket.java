package pl.graczyk.domain.numberreceiver;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;


@Getter
class Ticket {

    private String ticketId;
    private LocalDateTime drawDate;
    private Set<Integer> numbersFromUser;

    public Ticket() {
    }

    public Ticket(String ticketId, LocalDateTime drawDate, Set<Integer> numbersFromUser) {
        this.ticketId = ticketId;
        this.drawDate = drawDate;
        this.numbersFromUser = numbersFromUser;
    }

}
