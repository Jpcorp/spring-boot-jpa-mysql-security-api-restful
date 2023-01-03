package cl.espacio.ws.api.mapper;

import cl.espacio.ws.api.repository.entity.Usuarios;

public interface IMapper<I, O> {
    O map(I in, Usuarios user);
}
