package pl.graczyk.domain.numbergenerator;

import pl.graczyk.domain.numbergenerator.dto.SixRandomNumbersDto;
import pl.graczyk.domain.numbergenerator.dto.WinningNumbersDto;
import pl.graczyk.domain.numberreceiver.NumberReceiverFacade;

import java.time.LocalDateTime;
import java.util.Set;

public class WinningNumbersGeneratorFacade {

    private final RandomNumberGenerable winningNumberGenerator;
    private final WinningNumberValidator winningNumberValidator;
    private final WinningNumbersRepository winningNumbersRepository;
    private final NumberReceiverFacade numberReceiverFacade;

    public WinningNumbersGeneratorFacade(RandomNumberGenerable winningNumberGenerator, WinningNumberValidator winningNumberValidator, WinningNumbersRepository winningNumbersRepository, NumberReceiverFacade numberReceiverFacade) {
        this.winningNumberGenerator = winningNumberGenerator;
        this.winningNumberValidator = winningNumberValidator;
        this.winningNumbersRepository = winningNumbersRepository;
        this.numberReceiverFacade = numberReceiverFacade;
    }

    public WinningNumbersDto generateWinningNumbers() {
        LocalDateTime nextDrawDate = numberReceiverFacade.retrieveNextDrawDate();
        SixRandomNumbersDto sixRandomNumbersDto = winningNumberGenerator.generateSixRandomNumbers(6, 1, 99);
        Set<Integer> winningNumbers = sixRandomNumbersDto.numbers();
        winningNumberValidator.validate(winningNumbers);
        WinningNumbers winningNumbersDocument = new WinningNumbers(winningNumbers, nextDrawDate);
        WinningNumbers savedNumbers = winningNumbersRepository.save(winningNumbersDocument);
        return new WinningNumbersDto(savedNumbers.getWinningNumbers(),
                savedNumbers.getLocalDateTime());

    }

    public WinningNumbersDto retrieveWinningNumberByDate(LocalDateTime date) {
        WinningNumbers numbersByDate = winningNumbersRepository.findNumbersByDate(date)
                .orElseThrow(() -> new WinningNumbersNotFoundException("Not Found"));
        return new WinningNumbersDto(numbersByDate.getWinningNumbers(), date);

    }

    public boolean areWinningNumbersGeneratedByDate() {
        LocalDateTime nextDrawDate = numberReceiverFacade.retrieveNextDrawDate();
        return winningNumbersRepository.existsByDate(nextDrawDate);
    }

}
