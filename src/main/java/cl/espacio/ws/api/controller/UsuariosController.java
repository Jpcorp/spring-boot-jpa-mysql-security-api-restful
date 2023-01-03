package cl.espacio.ws.api.controller;

import cl.espacio.ws.api.repository.entity.Usuarios;
import cl.espacio.ws.api.service.UsuariosService;
import cl.espacio.ws.api.util.ResponseUtils;
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

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/email/{email}")
    public ResponseEntity<Object> searchUsuarioByEmail(@PathVariable String email) {
        log.debug("Entrando endpoint buscar usuario by email ");
        try {
            Usuarios usuario = service.getUsuarioByEmail(email);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (ResponseException e) {
            log.error("problemas al encontrar usuario, message: {}", e.getMensaje(), e);
            Response response = new Response(e.getMensaje());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable Long id) {
        log.debug("Entrando endpoint eliminar usuarios ");
        boolean result = service.deleteById(id);
        Response response = new Response();
        if (!result) {
            response.setMensaje(ResponseUtils.MSG_NOT_DELETE);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        log.info("Usuario ID {} fue eliminado", id);
        response.setMensaje(ResponseUtils.MSG_OK_DELETE);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }






}
