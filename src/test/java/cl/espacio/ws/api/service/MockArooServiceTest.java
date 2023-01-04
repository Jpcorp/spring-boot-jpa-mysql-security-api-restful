package cl.espacio.ws.api.service;

import cl.espacio.ws.api.domain.ResponseMockaroo;
import cl.espacio.ws.api.repository.ws.MockArooRespositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class MockArooServiceTest {

    @Autowired
    private MockArooService Service;

    //@MockBean
    private MockArooRespositoryImpl MockArooRepository;

    @Value("${cl.espacio.call.service.uri}")
    private String uri;

    @Test
    void testGetDataForService() throws UnsupportedEncodingException, NoSuchPaddingException,
            IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException,
            InvalidKeySpecException, InvalidKeyException {
        //(String url, HttpMethod method, HttpEntity entityReq, Object ReqRespItems
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-API-Key", "f2f719e0");

        HttpEntity entityReq = new HttpEntity<>(null, headers);

       // given(MockArooRepository.execute(uri, HttpMethod.GET, entityReq, ReqRespItems.class))
        //        .willReturn(new ResponseEntity<>(new ReqRespItems(), HttpStatus.OK));

        long startTime = System.currentTimeMillis();
        String param = "1-9";

        ResponseMockaroo actual = Service.getDataForService(param, uri, startTime);

        assertEquals(200, actual.getResponseCode());
    }


}
