package UsovAA.service;

import UsovAA.model.Positions;
import org.springframework.stereotype.Service;

@Service
public class QuarterlyBonusServiceImpl implements QuarterlyBonusService {

    @Override
    public double calculate(Positions positions, double salary, double bonus) {
        // Проверяем, является ли должность управленческой
        if (!positions.isManager()) {
            throw new IllegalArgumentException("Quarterly bonus is available only for managers.");
        }

        // Рассчитываем квартальную премию
        return salary * bonus * positions.getPositionCoefficient() / 4;
    }
}