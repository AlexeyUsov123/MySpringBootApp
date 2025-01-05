package UsovAA.model;

import lombok.Getter;

@Getter
public enum Positions {
    DEV(2.2, false),
    HR(1.2, false),
    TL(2.6, true),
    PM(3.0, true), // Project Manager
    QA(1.8, false), // Quality Assurance
    CEO(5.0, true); // CEO

    private final double positionCoefficient;
    private final boolean isManager;

    Positions(double positionCoefficient, boolean isManager) {
        this.positionCoefficient = positionCoefficient;
        this.isManager = isManager;
    }

    public double getPositionCoefficient() {
        return positionCoefficient;
    }

    public boolean isManager() {
        return isManager;
    }
}
