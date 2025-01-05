package UsovAA.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import UsovAA.exception.ValidationFailedException;

public interface ValidationService {

    void isValid(BindingResult bindingResult) throws ValidationFailedException;
}
