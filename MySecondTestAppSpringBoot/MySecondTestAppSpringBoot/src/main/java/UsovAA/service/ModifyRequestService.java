package UsovAA.service;

import org.springframework.stereotype.Service;
import UsovAA.model.Request;

@Service
public interface ModifyRequestService {

    void modify(Request request);
}
