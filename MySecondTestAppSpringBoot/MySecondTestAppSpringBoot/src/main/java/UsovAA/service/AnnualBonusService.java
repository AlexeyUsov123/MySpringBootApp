package UsovAA.service;

import org.springframework.stereotype.Service;
import UsovAA.model.Positions;

@Service
public interface AnnualBonusService {

    double calculate(Positions positions, double salary, double bonus, int workDays);
}
