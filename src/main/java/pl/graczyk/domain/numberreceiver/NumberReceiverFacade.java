package pl.graczyk.domain.numberreceiver;


import pl.graczyk.domain.numberreceiver.dto.InputNumberResultDto;
import pl.graczyk.domain.numberreceiver.dto.TicketDto;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;


public class NumberReceiverFacade {

    private final NumberValidator numberValidator;
    private final NumberReceiverRepository repository;
    private final Clock clock;

    public NumberReceiverFacade(NumberValidator numberValidator, NumberReceiverRepository repository, Clock clock) {
        this.numberValidator = numberValidator;
        this.repository = repository;
        this.clock = clock;
    }

    public InputNumberResultDto inputNumbers(Set<Integer> numbersFromUser) {
        boolean areAllNumbersCorrect = numberValidator.hasSixNumbersInRange(numbersFromUser);
        if (areAllNumbersCorrect) {
            String ticketId = UUID.randomUUID().toString();
            LocalDateTime drawDate = LocalDateTime.now(clock);
            Ticket savedTicket = repository.save(new Ticket(ticketId, drawDate, numbersFromUser));
            return new InputNumberResultDto(
                    "success",
                    savedTicket.getDrawDate(),
                    savedTicket.getTicketId(),
                    numbersFromUser);
        }
        return new InputNumberResultDto("failed", null, null, null);
    }

    public List<TicketDto> userNumbers(LocalDateTime date) {
        List<Ticket> allTicketsByDrawDate = repository.findAllTicketsByDrawDate(date);
        return allTicketsByDrawDate
                .stream()
                .map(TicketMapper::mapFromTicket)
                .toList();

    }


}
