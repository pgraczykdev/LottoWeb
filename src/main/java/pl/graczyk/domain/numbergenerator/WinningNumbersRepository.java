package pl.graczyk.domain.numbergenerator;

import java.time.LocalDateTime;
import java.util.Optional;

public interface WinningNumbersRepository {
    WinningNumbers save(WinningNumbers winningNumbers);
    Optional<WinningNumbers> findNumbersByDate(LocalDateTime date);

    boolean existsByDate(LocalDateTime nextDrawDate);
}
