package cl.espacio.ws.api.service;

import cl.espacio.ws.api.domain.ResponseMockaroo;
import cl.espacio.ws.api.domain.ResultMockaroo;
import cl.espacio.ws.api.domain.rest.ReqRespItems;
import cl.espacio.ws.api.repository.ws.MockArooRespositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;

@Service
public class MockArooService {

    Logger log = LoggerFactory.getLogger(this.getClass());

    final MockArooRespositoryImpl repository;

    public MockArooService(MockArooRespositoryImpl repository) {
        this.repository = repository;
    }

    public ResponseMockaroo getDataForService(String param, String uri, long startTime)
            throws UnsupportedEncodingException, NoSuchPaddingException,
            IllegalBlockSizeException, NoSuchAlgorithmException,
            BadPaddingException, InvalidKeySpecException, InvalidKeyException {

        log.debug("Entrando al servicio");

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-API-Key", "f2f719e0");

        HttpEntity<Object> entityReq = new HttpEntity<>(null, headers);

        String encrypt = encryptWithDES(param);

        String url = uri.concat(encrypt);

        ResponseEntity<ReqRespItems> respEntity = repository.execute(
                url, HttpMethod.GET, entityReq, ReqRespItems.class);

        ResponseMockaroo resServ = new ResponseMockaroo();

        if (respEntity != null) {
            int registerCount = respEntity.getBody().getResult().getItems().size();
            resServ.setResponseCode((int) respEntity.getBody().getResponseCode());
            resServ.setDescription(respEntity.getBody().getDescription());
            resServ.setElapsedTime(String.valueOf(elapsedTime(startTime)));
            resServ.setResult(new ResultMockaroo(registerCount));
        }

        return resServ;
    }

    private String encryptWithDES(String param)
            throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException {
        DESKeySpec keySpec = new DESKeySpec("ionix123456".getBytes(StandardCharsets.UTF_8));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(keySpec);

        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] cleartext = param.getBytes("UTF8");
        byte[] encrypted = cipher.doFinal(cleartext);

        return Base64.getEncoder().encodeToString(encrypted);

    }

    private long  elapsedTime(long startTime) {
        return System.currentTimeMillis() - startTime;
    }


}
