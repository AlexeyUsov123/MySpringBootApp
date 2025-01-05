package UsovAA.controller;

import UsovAA.exception.UnsupportedCodeException;
import UsovAA.exception.ValidationFailedException;
import UsovAA.model.*;
import UsovAA.service.*;
import UsovAA.util.DateTimeUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping
public class MyController {
    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;
    private final ModifyRequestService modifySystemNameRequestService;
    private final ModifyRequestService modifySourceRequestService;

    @Autowired
    public MyController(ValidationService validationService,
                        @Qualifier("ModifyOperationUidResponseService") ModifyResponseService modifyResponseService,
                        @Qualifier("ModifySystemNameRequestService") ModifyRequestService modifySystemNameRequestService,
                        @Qualifier("ModifySourceRequestService") ModifyRequestService modifySourceRequestService) {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.modifySystemNameRequestService = modifySystemNameRequestService;
        this.modifySourceRequestService = modifySourceRequestService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
                                             BindingResult bindingResult) {

        log.info("Received request: {}", request);

        // Устанавливаем текущее время в поле systemTime
        request.setSystemTime(DateTimeUtil.getCustomFormat().format(new Date()));

        // Создаем начальный ответ
        Response response = createResponse(request, Codes.SUCCESS, ErrorCodes.EMPTY, ErrorMessages.EMPTY);
        log.info("Initialized response: {}", response);

        try {
            validationService.isValid(bindingResult);
        } catch (ValidationFailedException e) {
            return handleValidationException(response, e);
        } catch (Exception e) {
            return handleUnexpectedException(response, e);
        }

        // Модифицируем source и systemName
        modifySourceRequestService.modify(request);
        modifySystemNameRequestService.modify(request);

        // Модифицируем ответ
        modifyResponseService.modify(response);
        log.info("Modified response: {}", response);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Создает объект Response на основе запроса и параметров.
     */
    private Response createResponse(Request request, Codes code, ErrorCodes errorCode, ErrorMessages errorMessage) {
        return Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(request.getSystemTime())
                .code(code)
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .build();
    }

    /**
     * Обрабатывает исключение ValidationFailedException.
     */
    private ResponseEntity<Response> handleValidationException(Response response, ValidationFailedException e) {
        log.error("Validation failed: {}", e.getMessage());
        response.setCode(Codes.FAILED);
        response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
        response.setErrorMessage(ErrorMessages.VALIDATION);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Обрабатывает непредвиденные исключения.
     */
    private ResponseEntity<Response> handleUnexpectedException(Response response, Exception e) {
        log.error("Unexpected error: {}", e.getMessage(), e);
        response.setCode(Codes.FAILED);
        response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
        response.setErrorMessage(ErrorMessages.UNKNOWN);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}