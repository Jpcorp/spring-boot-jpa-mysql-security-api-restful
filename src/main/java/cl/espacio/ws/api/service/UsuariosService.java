package cl.espacio.ws.api.service;

import cl.espacio.ws.api.exceptions.ResponseException;
import cl.espacio.ws.api.repository.UsuariosRepository;
import cl.espacio.ws.api.repository.entity.Usuarios;
import cl.espacio.ws.api.util.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class UsuariosService {
    Logger log = LoggerFactory.getLogger(this.getClass());

    final UsuariosRepository usuariosRepository;

    public UsuariosService(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    public ArrayList<Usuarios> getListUserRegister() throws ResponseException {
        ArrayList<Usuarios> response = new ArrayList<>();
        try {
            response = (ArrayList<Usuarios>) usuariosRepository.getListUserRegister();
        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage(), e);
            throw new ResponseException(ResponseUtils.MSG_ERROR);
        }
        return response;
    }

    public boolean createdUser(Usuarios usuario) throws ResponseException {
        boolean result = Boolean.FALSE;
        try {
             usuariosRepository.save(usuario);
             result = true;

        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage(), e);
            throw new ResponseException(ResponseUtils.MSG_OK_CREATED);
        }
        return result;
    }
}

