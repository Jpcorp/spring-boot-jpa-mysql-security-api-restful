package cl.espacio.ws.api.repository.ws;

import cl.espacio.ws.api.domain.rest.ReqRespItems;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public interface MockArooRespository {
    ResponseEntity<ReqRespItems> execute(String url, HttpMethod method, HttpEntity entityReq, Object ReqRespItems);
}
