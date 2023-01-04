package cl.espacio.ws.api.controller;

import cl.espacio.ws.api.domain.Response;
import cl.espacio.ws.api.service.MockArooService;
import cl.espacio.ws.api.util.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("service")
public class ConsumeApiController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MockArooService service;

    @Value("${cl.espacio.call.service.uri}")
    private String uri;

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/{param}")
    public ResponseEntity<Object> callBackService(@PathVariable String param) throws Exception {
        log.debug("Entrando endpoint llamar servicio ");
        try {
            long startTime = System.currentTimeMillis();
            return new ResponseEntity<>(
                    service.getDataForService(param, this.uri, startTime), HttpStatus.OK);

        } catch (Exception e) {
            log.error("problemas al guardar usuario, message: {}", e.getMessage(), e);
            Response response = new Response(ResponseUtils.MSG_ERROR_CLIENT_SERVICE);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }



}
