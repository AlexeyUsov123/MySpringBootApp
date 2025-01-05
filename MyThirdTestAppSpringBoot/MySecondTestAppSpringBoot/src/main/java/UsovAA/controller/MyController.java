package UsovAA.controller;
import UsovAA.exception.UnsupportedCodeException;
import UsovAA.model.*;
import UsovAA.service.ModifyResponseService;
import UsovAA.service.ValidationService;
import UsovAA.util.DateTimeUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import UsovAA.service.ValidationService;
import UsovAA.service.RequestValidationService;
import UsovAA.exception.ValidationFailedException;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping
public class MyController {
    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;

    @Autowired
    public MyController(ValidationService validationService,
                        @Qualifier("ModifyOperationUidResponseService") ModifyResponseService modifyResponseService) {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
                                             BindingResult bindingResult) {

        log.info("Received request in Service 2: {}", request);

        if (bindingResult.hasErrors()) {
            log.error("Validation errors: {}", bindingResult.getAllErrors());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Получаем текущее время
        String currentTime = DateTimeUtil.getCustomFormat().format(new Date());

        // Вычисляем разницу времени
        SimpleDateFormat format = DateTimeUtil.getCustomFormat();
        try {
            Date requestTime = format.parse(request.getSystemTime()); // Время из Сервиса 1
            Date currentTimeDate = format.parse(currentTime); // Текущее время в Сервисе 2
            long timeDifference = currentTimeDate.getTime() - requestTime.getTime(); // Разница в миллисекундах

            log.info("Time difference between Service 1 and Service 2: {} ms", timeDifference);
        } catch (Exception e) {
            log.error("Error while calculating time difference: {}", e.getMessage());
        }

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();

        log.info("Initialized response in Service 2: {}", response);

        try {
            validationService.isValid(bindingResult);
            response = modifyResponseService.modify(response);
            log.info("Modified response in Service 2: {}", response);
        } catch (ValidationFailedException e) {
            log.error("Validation failed: {}", e.getMessage());
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage(), e);
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
