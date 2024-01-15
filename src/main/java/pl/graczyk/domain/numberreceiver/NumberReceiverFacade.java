package pl.graczyk.domain.numberreceiver;


import pl.graczyk.domain.numberreceiver.dto.NumberReceiverResponseDto;
import pl.graczyk.domain.numberreceiver.dto.TicketDto;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static pl.graczyk.domain.numberreceiver.ValidationResult.INPUT_SUCCESS;


public class NumberReceiverFacade {

    private final NumberValidator numberValidator;
    private final DrawDateGenerator drawDateGenerator;
    private final HashGenerable hashGenerator;
    private final TicketRepository ticketRepository;


    public NumberReceiverFacade(NumberValidator numberValidator, DrawDateGenerator drawDateGenerator, HashGenerable hashGenerator, TicketRepository ticketRepository) {
        this.numberValidator = numberValidator;
        this.drawDateGenerator = drawDateGenerator;
        this.hashGenerator = hashGenerator;
        this.ticketRepository = ticketRepository;
    }

    public NumberReceiverResponseDto inputNumbers(Set<Integer> numbersFromUser) {
        List<ValidationResult> validationResultList = numberValidator.validate(numbersFromUser);
        if (!validationResultList.isEmpty()) {
            String resultMessage = numberValidator.createResultMessage();
            return new NumberReceiverResponseDto(null, resultMessage);
        }
        LocalDateTime drawDate = drawDateGenerator.getNextDrawDate();

        String hash = hashGenerator.getHash();

        TicketDto generatedTicket = new TicketDto(hash, drawDate, numbersFromUser);

        Ticket savedTicket = new Ticket(hash, generatedTicket.drawDate(), generatedTicket.numbersFromUser());


        ticketRepository.save(savedTicket);

        return new NumberReceiverResponseDto(generatedTicket, INPUT_SUCCESS.info);
    }

    public List<TicketDto> retrieveAllTicketsByNextDrawDate() {
        LocalDateTime nextDrawDate = drawDateGenerator.getNextDrawDate();
        return retrieveAllTicketsByNextDrawDate(nextDrawDate);
    }

    public List<TicketDto> retrieveAllTicketsByNextDrawDate(LocalDateTime date) {
        LocalDateTime nextDrawDate = drawDateGenerator.getNextDrawDate();
        if (date.isAfter(nextDrawDate)) {
            return Collections.emptyList();
        }
        return ticketRepository.findAllTicketsByDrawDate(date)
                .stream()
                .filter(ticket -> ticket.getDrawDate().isEqual(date))
                .map(TicketMapper::mapFromTicket)
                .collect(Collectors.toList());
    }

    public LocalDateTime retrieveNextDrawDate() {
        return drawDateGenerator.getNextDrawDate();
    }


    public TicketDto findByHash(String hash) {
        Ticket ticket = ticketRepository.findByHash(hash);
        return new TicketDto(ticket.getHash(), ticket.getDrawDate(), ticket.getNumbersFromUser());
    }


}
