package pl.graczyk.domain.numberreceiver;

import java.util.Set;

class NumberValidator {

    private static final int MAX_NUMBERS_FROM_USER = 6;
    private static final int MINIMAL_NUMBER_VALUE = 1;
    private static final int MAXIMAL_NUMBER_VALUE = 99;

    public boolean hasSixNumbersInRange(Set<Integer> numbersFromUser) {
        return numbersFromUser.stream()
                .filter(number -> number >= MINIMAL_NUMBER_VALUE)
                .filter(number -> number <= MAXIMAL_NUMBER_VALUE)
                .count() == MAX_NUMBERS_FROM_USER;
    }
}
