package cl.espacio.ws.api.controller;

import cl.espacio.ws.api.exceptions.ResponseException;
import cl.espacio.ws.api.repository.entity.Usuarios;
import cl.espacio.ws.api.service.UsuariosService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuariosControllerTest {

    @Autowired
    private UsuariosController controller;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuariosService service;

    private Usuarios usuario;

    private ArrayList<Usuarios> usuarios;

    @BeforeAll
    void createdData() {
        Usuarios users1 = new Usuarios(1L, "jose andres",
                "jo.an", "jo.an@m.cl");

        Usuarios users2 = new Usuarios(1L, "Juan Pablo",
                "j.pablo", "j.apblo@live.cl");
        usuario = users1;
        usuarios.add(users1);
        usuarios.add(users2);

    }
/**
    @Test
    void testCreateUsuario() throws Exception {

        given(service.createdUser(usuario)).willReturn(Boolean.TRUE);
        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(usuario))
                .andExpect(status().isOk())
                .andExpect(contentType().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(nullValue())))
                .andExpect(jsonPath("$.name", is("Charlie")))
                .andExpect(jsonPath("$.role", is("Tester")));
    }

    private Object asJsonString(Usuarios usuario) {
        
    }**/

}
