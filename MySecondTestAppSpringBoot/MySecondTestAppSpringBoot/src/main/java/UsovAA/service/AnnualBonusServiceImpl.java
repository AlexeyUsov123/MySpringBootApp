package UsovAA.service;

import org.springframework.stereotype.Service;
import UsovAA.model.Positions;

@Service
public class AnnualBonusServiceImpl implements AnnualBonusService {

    @Override
    public double calculate(Positions positions, double salary, double bonus, int workDays) {
        int daysInYear = isLeapYear() ? 366 : 365; // Определяем количество дней в году
        return salary * bonus * daysInYear * positions.getPositionCoefficient() / workDays;
    }

    /**
     * Метод для определения, является ли текущий год високосным.
     * @return true, если год високосный, иначе false
     */
    private boolean isLeapYear() {
        int year = java.time.Year.now().getValue(); // Получаем текущий год
        return java.time.Year.isLeap(year);
    }
}
