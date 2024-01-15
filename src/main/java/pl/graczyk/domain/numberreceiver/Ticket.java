package pl.graczyk.domain.numberreceiver;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;


@Getter
class Ticket {

    private String hash;
    private LocalDateTime drawDate;
    private Set<Integer> numbersFromUser;

    public Ticket() {
    }

    public Ticket(String hash, LocalDateTime drawDate, Set<Integer> numbersFromUser) {
        this.hash = hash;
        this.drawDate = drawDate;
        this.numbersFromUser = numbersFromUser;
    }

}
