package UsovAA.service;

import UsovAA.model.Systems;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import UsovAA.model.Request;

@Service
@Slf4j
@Qualifier("ModifySystemNameRequestService")
public class ModifySystemNameRequestService implements ModifyRequestService {

    private final RestTemplate restTemplate;

    @Autowired
    public ModifySystemNameRequestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void modify(Request request) {
        // Изменяем systemName на "CRM"
        request.setSystemName("CRM");
        log.info("System name modified to: {}", request.getSystemName());

        // Отправляем запрос в Сервис 2
        HttpEntity<Request> httpEntity = new HttpEntity<>(request);
        try {
            restTemplate.exchange(
                    "http://localhost:8084/feedback", // URL Сервиса 2
                    HttpMethod.POST,
                    httpEntity,
                    new ParameterizedTypeReference<Object>() {}
            );
            log.info("Request sent to Service 2 successfully.");
        } catch (Exception e) {
            log.error("Error while sending request to Service 2: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to send request to Service 2", e);
        }
    }
}

