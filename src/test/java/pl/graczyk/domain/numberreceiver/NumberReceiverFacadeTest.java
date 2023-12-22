package pl.graczyk.domain.numberreceiver;

import org.junit.jupiter.api.Test;
import pl.graczyk.domain.AdjustableClock;
import pl.graczyk.domain.numberreceiver.dto.InputNumberResultDto;
import pl.graczyk.domain.numberreceiver.dto.TicketDto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class NumberReceiverFacadeTest {

    AdjustableClock clock = new AdjustableClock(LocalDateTime.of(2023, 2, 15, 11, 0, 0).toInstant(ZoneOffset.UTC), ZoneId.systemDefault());

    private final NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
            new NumberValidator(),
            new InMemoryNumberReceiverRepositoryTestImplementation(),
            clock
    );

    @Test
    public void should_return_success_when_user_gave_six_numbers() {
        //given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);
        //when
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        //then
        assertThat(result.message()).isEqualTo("success");
    }

    @Test
    public void should_return_failed_when_user_gave_at_least_one_number_out_of_range_1_to_99() {
        //given
        Set<Integer> numbersFromUser = Set.of(1000, 2, 3, 4, 5, 6);
        //when
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        //then
        assertThat(result.message()).isEqualTo("failed");

    }

    @Test
    public void should_return_failed_when_user_gave_less_than_six_numbers() {
        //given
        Set<Integer> numbersFromUser = Set.of(2, 3, 4, 5, 6);
        //when
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        //then
        assertThat(result.message()).isEqualTo("failed");
    }

    @Test
    public void should_return_failed_when_user_gave_more_than_six_numbers() {
        //given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6, 7);
        //when
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        //then
        assertThat(result.message()).isEqualTo("failed");

    }

    @Test
    public void should_return_failed_when_user_gave_empty_input() {
        //given
        Set<Integer> numbersFromUser = Set.of();
        //when
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        //then
        assertThat(result.message()).isEqualTo("failed");
    }

    @Test
    public void should_return_save_to_database_when_given_correct_numbers() {
        //given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        LocalDateTime drawDate = LocalDateTime.of(2023, 2, 15, 12, 0, 0);
        //when
        List<TicketDto> ticketDtos = numberReceiverFacade.userNumbers(drawDate);
        //then
        assertThat(ticketDtos).contains(
                new TicketDto(result.ticketId(), result.drawDate(), result.numbersFromUser())
        );
    }

}