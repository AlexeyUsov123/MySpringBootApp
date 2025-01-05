package UsovAA.service;

import UsovAA.model.Positions;

public interface QuarterlyBonusService {
    double calculate(Positions positions, double salary, double bonus);
}
