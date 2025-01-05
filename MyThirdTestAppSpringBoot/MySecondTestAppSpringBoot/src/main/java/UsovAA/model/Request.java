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
    private String uid;

    @NotBlank(message = "Operation UID не может быть пустым")
    @Size(max = 32, message = "Operation UID должен быть длиной до 32 символов")
    private String operationUid;

    private Systems systemName;

    @NotBlank(message = "System time не может быть пустым")
    private String systemTime;

    private String source;

    @Min(value = 1, message = "Communication ID должен быть не меньше 1")
    @Max(value = 100000, message = "Communication ID должен быть не больше 100000")
    private int communicationId;

    private int templateId;

    private int productCode;

    private int smsCode;

    @Override
    public String toString() {
        return "{" +
                "uid='" + uid + '\'' +
                ", operationUid='" + operationUid + '\'' +
                ", systemName='" + (systemName != null ? systemName.getDescription() : null) + '\'' +
                ", systemTime='" + systemTime + '\'' +
                ", source='" + source + '\'' +
                ", communicationId=" + communicationId +
                ", templateId=" + templateId +
                ", productCode=" + productCode +
                ", smsCode=" + smsCode +
                '}';
    }
}

