package pl.graczyk.domain.numbergenerator;

import pl.graczyk.domain.numbergenerator.dto.SixRandomNumbersDto;

public interface RandomNumberGenerable {

    SixRandomNumbersDto generateSixRandomNumbers(int count, int lowerBand, int upperBand);
}