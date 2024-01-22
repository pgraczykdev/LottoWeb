package pl.graczyk.domain.numbergenerator;

import pl.graczyk.domain.numberreceiver.NumberReceiverFacade;

public class NumberGeneratorConfiguration {
    WinningNumbersGeneratorFacade createForTest(RandomNumberGenerable generator, WinningNumbersRepository winningNumbersRepository, NumberReceiverFacade numberReceiverFacade) {
        WinningNumberValidator validator = new WinningNumberValidator();
        return new WinningNumbersGeneratorFacade(generator, validator, winningNumbersRepository, numberReceiverFacade);
    }
}

