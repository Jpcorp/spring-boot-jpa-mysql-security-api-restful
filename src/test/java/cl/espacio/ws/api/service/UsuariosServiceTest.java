package cl.espacio.ws.api.service;

import cl.espacio.ws.api.repository.UsuariosRepository;
import cl.espacio.ws.api.repository.entity.Usuarios;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuariosServiceTest {

    @Autowired
    private UsuariosService usuariosService;

    @MockBean
    private UsuariosRepository usuariosRepository;

    private Usuarios usuario;

    private ArrayList<Usuarios> usuarios = new ArrayList<>();

    @BeforeEach
    void createdData() {
        usuarios = new ArrayList<>();
        Usuarios users1 = new Usuarios(1L, "jose andres",
                "jo.an", "jo.an@m.cl");

        Usuarios users2 = new Usuarios(1L, "Juan Pablo",
                "j.pablo", "j.apblo@live.cl");
        usuario = users1;
        usuarios.add(users1);
        usuarios.add(users2);

    }

    @Test
    void testGetUsersRegisterByEmail() {
        List<Usuarios> expectedUsers = Arrays.asList(
                new Usuarios(1L, "jose andres",
                        "jo.an", "jo.an@m.cl"),
                new Usuarios(1L, "Juan Pablo",
                        "j.pablo", "j.apblo@live.cl")
        );
        given(usuariosRepository.getUserRegisterByEmail(usuario.getEmail()))
                .willReturn(expectedUsers.get(0));
        usuariosRepository.save(expectedUsers.get(0));// Act
        Usuarios actualUsers = usuariosRepository.getUserRegisterByEmail(expectedUsers.get(0).getEmail());
        // Assert
        assertEquals(expectedUsers.get(0), actualUsers);
    }

    @Test
    void testGetListUserRegister() {
        List<Usuarios> expectedUsers = Arrays.asList(
                new Usuarios(1L, "jose andres",
                        "jo.an", "jo.an@m.cl"),
                new Usuarios(1L, "Juan Pablo",
                        "j.pablo", "j.apblo@live.cl")
        );
        given(usuariosRepository.getListUserRegister())
                .willReturn(expectedUsers);
        // Act
        List<Usuarios> actualUsers = usuariosRepository.getListUserRegister();
        // Assert
        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    void testCreateUser() {
        Usuarios users = new Usuarios(1L, "jose andres",
                "jo.an", "jo.an@m.cl");

        given(usuariosRepository.save(users))
                .willReturn(users);

        assertNotNull(users);
    }

    @Test
    void testRemoveUser() {
        Usuarios users = new Usuarios(1L, "jose andres",
                "jo.an", "jo.an@m.cl");

        given(usuariosRepository.rmById(users.getID()))
                .willReturn(Boolean.TRUE);

        boolean actual = usuariosRepository.rmById(users.getID());

        assertEquals(Boolean.TRUE, actual);

    }

}
