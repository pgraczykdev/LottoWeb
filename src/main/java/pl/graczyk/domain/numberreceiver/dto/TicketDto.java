package pl.graczyk.domain.numberreceiver.dto;

import java.time.LocalDateTime;
import java.util.Set;

public record TicketDto(String hash, LocalDateTime drawDate, Set<Integer> numbersFromUser) {
}
