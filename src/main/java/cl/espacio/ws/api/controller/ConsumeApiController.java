package cl.espacio.ws.api.controller;

import cl.espacio.ws.api.domain.Response;
import cl.espacio.ws.api.domain.ResponseMockaroo;
import cl.espacio.ws.api.domain.ResultMockaroo;
import cl.espacio.ws.api.domain.rest.ReqRespItems;
import cl.espacio.ws.api.exceptions.ResponseException;
import cl.espacio.ws.api.service.UsuariosService;
import cl.espacio.ws.api.util.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;

@RestController
@RequestMapping("service")
public class ConsumeApiController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UsuariosService service;

    @Value("${cl.espacio.call.service.uri}")
    private String uri;

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/{param}")
    public ResponseEntity<Object> callBackService(@PathVariable String param) throws Exception {
        log.debug("Entrando endpoint llamar servicio ");

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("X-API-Key", "f2f719e0");

            HttpEntity entityReq = new HttpEntity<>(null, headers);

            RestTemplate template = new RestTemplate();

            String encrypt = encryptWithDES(param);

            String url = this.uri.concat(encrypt);
            System.out.println(url);

            long startTime = System.currentTimeMillis();

            ResponseEntity<ReqRespItems> respEntity = template
                    .exchange(this.uri.concat(encrypt), HttpMethod.GET, entityReq, ReqRespItems.class);

            int registerCount = respEntity.getBody().getResult().getItems().size();
            ResponseMockaroo resServ = new ResponseMockaroo();
            resServ.setResponseCode((int) respEntity.getBody().getResponseCode());
            resServ.setDescription(respEntity.getBody().getDescription());
            resServ.setElapsedTime(String.valueOf(elapsedTime(startTime)));
            resServ.setResult(new ResultMockaroo(registerCount));

            return new ResponseEntity<>(resServ, HttpStatus.OK);

        } catch (Exception e) {
            log.error("problemas al guardar usuario, message: {}", e.getMessage(), e);
            Response response = new Response(ResponseUtils.MSG_ERROR_CLIENT_SERVICE);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    private long  elapsedTime(long startTime) {
        return System.currentTimeMillis() - startTime;
    }

    private String encryptWithDES(String param)
            throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException {
        DESKeySpec keySpec = new DESKeySpec("ionix123456".getBytes("UTF8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(keySpec);

        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] cleartext = param.getBytes("UTF8");
        byte[] encrypted = cipher.doFinal(cleartext);

        String encryptedRut = Base64.getEncoder().encodeToString(encrypted);

        return encryptedRut;
    }
}
