package cl.espacio.ws.api.repository.ws;

import cl.espacio.ws.api.domain.rest.ReqRespItems;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MockArooRespositoryImpl implements MockArooRespository {
    @Override
    public ResponseEntity<ReqRespItems> execute(String url, HttpMethod method,
                                                HttpEntity entityReq, Object ReqRespItems) {
        RestTemplate template = new RestTemplate();

        ResponseEntity<ReqRespItems> respEntity = template
                .exchange(url, method, entityReq, ReqRespItems.class);

        return respEntity;
    }
}
