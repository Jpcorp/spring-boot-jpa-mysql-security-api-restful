package cl.espacio.ws.api.repository;

import cl.espacio.ws.api.repository.entity.Usuarios;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UsuariosRepository extends CrudRepository<Usuarios, Long> {

    @Query(value="SELECT ID, NAME, USERNAME, EMAIL FROM USUARIOS ",nativeQuery=true)
    List<Usuarios> getListUserRegister();
    @Query(value="SELECT * FROM USUARIOS WHERE EMAIL = :email",nativeQuery=true)
    Usuarios getUserRegisterByEmail(String email);
    @Query(value="DELETE FROM USUARIOS WHERE ID = :id ",nativeQuery=true)
    boolean rmById(Long id);

    @Query(value="SELECT ID, CREATED, EMAIL, IS_ACTIVE, MODIFIED, NAME, LAST_LOGIN TOKEN, USERNAME, PASSWORD "
            + "FROM USUARIOS  WHERE USERNAME = :username",nativeQuery=true)
    Usuarios findByUsername(String username);

}
