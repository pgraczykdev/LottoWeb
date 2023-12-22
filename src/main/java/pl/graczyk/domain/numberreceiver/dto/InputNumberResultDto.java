package pl.graczyk.domain.numberreceiver.dto;

import java.time.LocalDateTime;
import java.util.Set;

public record InputNumberResultDto(String message, LocalDateTime drawDate, String ticketId, Set<Integer> numbersFromUser) {

}
