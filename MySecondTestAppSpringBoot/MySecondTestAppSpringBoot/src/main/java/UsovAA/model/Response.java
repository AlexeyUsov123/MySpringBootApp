package UsovAA.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    private String uid;//Уникальный идентификатор сообщения
    private String operationUid;//Уникальный идентификатор операции
    private String systemTime;//Имя системы отправителя
    private Codes code;//Время создания сообщения
    private Double annualBonus;//Ежегодный бонус
    private ErrorCodes errorCode;//Наименование ресурса
    private ErrorMessages errorMessage;//Сообщение об ошибке
}
