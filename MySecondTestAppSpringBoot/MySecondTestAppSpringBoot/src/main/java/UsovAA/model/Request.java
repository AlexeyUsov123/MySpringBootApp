package UsovAA.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    @NotBlank(message = "UID не может быть пустым")
    @Size(max = 32, message = "UID должен быть длиной до 32 символов")
    private String uid;//Уникальный идентификатор сообщение

    @NotBlank(message = "Operation UID не может быть пустым")
    @Size(max = 32, message = "Operation UID должен быть длиной до 32 символов")
    private String operationUid;//Уникальный идентификатор операции

    private String systemName;//Имя системы отправителя

    @NotBlank(message = "System time не может быть пустым")
    private String systemTime;//Время создания сообщения

    private String source;//Наименование ресурса

    @NotBlank(message = "Position не может быть пустой")
    private String position;//позиция(должность), которую занимает человек

    @Min(value = 0, message = "Salary должен быть не меньше 0")
    private int salary;//заработная плата

    @Min(value = 0, message = "Bonus должен быть не меньше 0")
    private double bonus;//бонус, который получает человек к заработной плате

    @Min(value = 0, message = "Work days должны быть не меньше 0")
    private int workDays;//количество рабочих дней

    @Min(value = 1, message = "Communication ID должен быть не меньше 1")
    @Max(value = 100000, message = "Communication ID должен быть не больше 100000")
    private int communicationId;//Уникальный идентификатор коммуникации

    private int templateId;//Уникальный идентификатор шаблона

    private int productCode;//Код продукта

    private int smsCode;//Смс код

    @Override
    public String toString() {
        return "{" +
                "uid='" + uid + '\'' +
                ", operationUid='" + operationUid + '\'' +
                ", systemName='" + systemName + '\'' +
                ", systemTime='" + systemTime + '\'' +
                ", source='" + source + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", bonus=" + bonus +
                ", workDays=" + workDays +
                ", communicationId=" + communicationId +
                ", templateId=" + templateId +
                ", productCode=" + productCode +
                ", smsCode=" + smsCode +
                '}';
    }

    /**
     * Метод для расчета заработной платы.
     * Формула: salary * (bonus / 100) * workDays
     *
     * @return Рассчитанная заработная плата
     */
    public double calculateSalary() {
        return salary * (bonus / 100) * workDays;
    }
}