package cl.espacio.ws.api.controller;

import cl.espacio.ws.api.repository.entity.Usuarios;
import cl.espacio.ws.api.service.UsuariosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import cl.espacio.ws.api.exceptions.ResponseException;
import cl.espacio.ws.api.domain.Response;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("usuario")
@Validated // class level
@CrossOrigin
public class UsuariosController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UsuariosService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/")
    public ResponseEntity<Object> guardarUsuario(
            @Valid @RequestBody Usuarios usuario, @RequestHeader HttpHeaders headers)
            throws Exception {
        log.debug("Entrando endpoint guardar usuarios ");

        try {

            return new ResponseEntity<>(service.createdUser(usuario), HttpStatus.OK);

        } catch (ResponseException e) {
            log.error("problemas al guardar usuario, message: {}", e.getMensaje(), e);
            Response response = new Response(e.getMensaje());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/all")
    public ResponseEntity<Object> obtenerUsuarios() throws Exception {
        log.debug("Entrando endpoint obtener usuarios ");
        try {

            ArrayList<Usuarios> usuarios = service.getListUserRegister();
            return new ResponseEntity<>(usuarios, HttpStatus.OK);

        } catch (ResponseException e) {
            log.error("problemas al obtener usuarios, message: {}", e.getMensaje());
            return new ResponseEntity<>(e, HttpStatus.CONFLICT);
        }
    }






}
