package cl.espacio.ws.api.service;

import cl.espacio.ws.api.domain.JwtRequest;
import cl.espacio.ws.api.exceptions.ResponseException;
import cl.espacio.ws.api.repository.UsuariosRepository;
import cl.espacio.ws.api.repository.entity.Usuarios;
import cl.espacio.ws.api.security.jwt.JwtTokenUtil;
import cl.espacio.ws.api.util.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import cl.espacio.ws.api.domain.Response;

import java.util.ArrayList;
@Service
public class UsuariosService {
    Logger log = LoggerFactory.getLogger(this.getClass());

    final UsuariosRepository usuariosRepository;

    final JwtTokenUtil jwtTokenUtil;

    public UsuariosService(UsuariosRepository usuariosRepository, JwtTokenUtil jwtTokenUtil) {
        this.usuariosRepository = usuariosRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public ArrayList<Usuarios> getListUserRegister() throws ResponseException {
        ArrayList<Usuarios> response = new ArrayList<>();
        try {
            response = (ArrayList<Usuarios>) usuariosRepository.findAll();
        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage(), e);
            throw new ResponseException(ResponseUtils.MSG_ERROR);
        }
        return response;
    }

    public Usuarios createdUser(Usuarios usuario) throws ResponseException {
        Usuarios result = null;
        try {
            JwtRequest jwtRequest = new JwtRequest(usuario.getUsername(), usuario.getPassword());
            final UserDetails userDetails = loadUserByUsername(jwtRequest.getUsername(), usuario.getPassword());
            final String token = jwtTokenUtil.generateToken(userDetails);
            usuario.setToken(token);
            usuario.setUsername(jwtRequest.getUsername());
            usuario.setIsActive(true);
            result = usuariosRepository.save(usuario);

        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage(), e);
            throw new ResponseException(ResponseUtils.MSG_OK_CREATED);
        }
        return result;
    }

    private UserDetails loadUserByUsername(String username, String password) {
        return new User(username, password, new ArrayList<>());
    }

    public Usuarios getUsuarioByEmail(String email) throws ResponseException {

        Usuarios response = new Usuarios();
        try {
            response = usuariosRepository.getUserRegisterByEmail(email);
        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage(), e);
            throw new ResponseException(ResponseUtils.MSG_ERROR);
        }
        return response;
    }
    public boolean deleteById(Long id) {
        Boolean result = false;
        try {
            usuariosRepository.deleteById(id);
            result = true;

        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage(), e);
            return result;
        }
        return result;
    }
}

