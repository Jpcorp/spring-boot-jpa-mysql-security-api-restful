package cl.espacio.ws.api.controller;

import cl.espacio.ws.api.service.MockArooService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ConsumeApiControllerTest {

    @Autowired
    private ConsumeApiController controller;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MockArooService service;

    @Value("${cl.espacio.call.service.uri}")
    private String uri;

    @Test
    void testcallBackService() throws Exception {
        long startTime = System.currentTimeMillis();
        String param = "1-9";
        MockHttpServletRequestBuilder servletRequest =
                UsuariosControllerTest.myFactoryRequest("/service/".concat(param))
                        .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(servletRequest)
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(200, result.getResponse().getStatus());
    }

}
