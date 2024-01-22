package pl.graczyk.domain.numbergenerator;

import pl.graczyk.domain.numbergenerator.dto.SixRandomNumbersDto;

import java.util.Set;

public class WinningNumberGeneratorTestImplementation implements RandomNumberGenerable {

    private final Set<Integer> generatedNumbers;

    WinningNumberGeneratorTestImplementation() {
        generatedNumbers = Set.of(1, 2, 3, 4, 5, 6);
    }

    WinningNumberGeneratorTestImplementation(Set<Integer> generatedNumbers) {
        this.generatedNumbers = generatedNumbers;
    }

    @Override
    public SixRandomNumbersDto generateSixRandomNumbers(int count, int lowerBand, int upperBand) {
        return new SixRandomNumbersDto(generatedNumbers);
    }
}