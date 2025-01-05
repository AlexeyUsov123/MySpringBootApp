package UsovAA.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import UsovAA.model.Positions;

class QuarterlyBonusServiceImplTest {

    @Test
    void calculateQuarterlyBonusForManager() {
        // given
        Positions position = Positions.PM; // Project Manager (управленец)
        double bonus = 2.0;
        double salary = 100000.00;

        QuarterlyBonusServiceImpl quarterlyBonusService = new QuarterlyBonusServiceImpl();

        // when
        double result = quarterlyBonusService.calculate(position, salary, bonus);

        // then
        double expected = 150000.0; // 100000 * 2.0 * 3.0 / 4
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void calculateQuarterlyBonusForNonManager() {
        // given
        Positions position = Positions.DEV; // Developer (не управленец)
        double bonus = 2.0;
        double salary = 100000.00;

        QuarterlyBonusServiceImpl quarterlyBonusService = new QuarterlyBonusServiceImpl();

        // when / then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> quarterlyBonusService.calculate(position, salary, bonus)
        );
        assertThat(exception.getMessage()).isEqualTo("Quarterly bonus is available only for managers.");
    }
}