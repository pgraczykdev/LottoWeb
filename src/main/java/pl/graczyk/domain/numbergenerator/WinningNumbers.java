package pl.graczyk.domain.numbergenerator;

import java.time.LocalDateTime;
import java.util.Set;

class WinningNumbers {
    private String id;
    private Set<Integer> winningNumbers;
    private LocalDateTime localDateTime;

    public WinningNumbers(String id, Set<Integer> winningNumbers, LocalDateTime localDateTime) {
        this.id = id;
        this.winningNumbers = winningNumbers;
        this.localDateTime = localDateTime;
    }

    public WinningNumbers(Set<Integer> winningNumbers, LocalDateTime localDateTime) {
        this.winningNumbers = winningNumbers;
        this.localDateTime = localDateTime;
    }

    public String getId() {
        return id;
    }

    public Set<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
