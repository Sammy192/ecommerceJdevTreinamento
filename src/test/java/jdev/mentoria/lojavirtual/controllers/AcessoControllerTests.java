package jdev.mentoria.lojavirtual.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import jdev.mentoria.lojavirtual.dtos.AcessoDTO;
import jdev.mentoria.lojavirtual.services.AcessoService;
import jdev.mentoria.lojavirtual.testsUtils.Factory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class AcessoControllerTests {


    private MockMvc mockMvc;

    @Mock
    private AcessoService acessoService;

    @InjectMocks
    private AcessoController acessoController;

    private Long existingId;
    private Long nonExistingId;
    private Long dependentId;
    private ObjectMapper objectMapper;

    private AcessoDTO acessoDTO;

    @Before
    public void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(acessoController)
                .alwaysDo(print())
                .build();

        existingId = 1L;
        nonExistingId = 2L;
        dependentId = 3L;
        acessoDTO = Factory.createAcessoDTOAdmin();
        objectMapper = new ObjectMapper();

        when(acessoService.findAll()).thenReturn(List.of(acessoDTO));

        when(acessoService.findById(existingId)).thenReturn(acessoDTO);
        when(acessoService.findById(nonExistingId)).thenThrow(ResourceNotFoundException.class);

        when(acessoService.insert(any(AcessoDTO.class))).thenReturn(acessoDTO);

        when(acessoService.update(eq(existingId), any())).thenReturn(acessoDTO);
        when(acessoService.update(eq(nonExistingId), any())).thenThrow(ResourceNotFoundException.class);

        doNothing().when(acessoService).delete(existingId);
        doThrow(ResourceNotFoundException.class).when(acessoService).delete(nonExistingId);
        //doThrow(DataIntegrityViolationException.class).when(acessoService).delete(dependentId);
    }

    @Test
    public void insertShouldReturnAcessoDTOWhenValidData() throws Exception {
        String jsonBody = objectMapper.writeValueAsString(acessoDTO);

        ResultActions result =
                mockMvc.perform(post("/acessos")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.perfil").exists());
        result.andExpect(jsonPath("$.perfil").value(acessoDTO.getPerfil()));

        /* abaixo verifica mesmo campo Perfil antes ele deserializa o objeto para depois verificar
        Acesso objetoRetorno = objectMapper.
                readValue(result.andReturn().getResponse().getContentAsString(),
                        Acesso.class);

        assertEquals(acessoDTO.getPerfil(), objetoRetorno.getPerfil());*/
    }

    @Test
    public void insertShouldReturnBadRequestWhenInvalidData() throws Exception {
        AcessoDTO invalidAcessoDTO = new AcessoDTO(null, null);
        String jsonBody = objectMapper.writeValueAsString(invalidAcessoDTO);

        ResultActions result =
                mockMvc.perform(post("/acessos")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isBadRequest());
    }

    @Test
    public void findByIdShouldReturnAcessoDTOWhenIdExists() throws Exception {
        ResultActions result =
                mockMvc.perform(get("/acessos/{id}", existingId)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.perfil").exists());
        result.andExpect(jsonPath("$.perfil").value(acessoDTO.getPerfil()));
    }

    @Test
    public void findByIdShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
        ResultActions result =
                mockMvc.perform(get("/acessos/{id}", nonExistingId)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }

    @Test
    public void updateShouldReturnAcessoDTOWhenIdExists() throws Exception {
        String jsonBody = objectMapper.writeValueAsString(acessoDTO);

        ResultActions result =
                mockMvc.perform(put("/acessos/{id}", existingId)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.perfil").exists());
        result.andExpect(jsonPath("$.perfil").value(acessoDTO.getPerfil()));
    }

    @Test
    public void updateShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
        String jsonBody = objectMapper.writeValueAsString(acessoDTO);

        ResultActions result =
                mockMvc.perform(put("/acessos/{id}", nonExistingId)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }

    @Test
    public void updateShouldReturnBadRequestWhenInvalidData() throws Exception {
        AcessoDTO invalidAcessoDTO = new AcessoDTO(null, null);
        String jsonBody = objectMapper.writeValueAsString(invalidAcessoDTO);

        ResultActions result =
                mockMvc.perform(put("/acessos/{id}", existingId)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isBadRequest());
    }

    @Test
    public void findAllShouldReturnListOfAcessoDTO() throws Exception {
        ResultActions result =
                mockMvc.perform(get("/acessos")
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$").isArray());
        result.andExpect(jsonPath("$[0].id").exists());
        result.andExpect(jsonPath("$[0].perfil").exists());
        result.andExpect(jsonPath("$[0].perfil").value(acessoDTO.getPerfil()));
    }

    @Test
    public void findAllShouldReturnEmptyListWhenNoData() throws Exception {
        when(acessoService.findAll()).thenReturn(List.of());

        ResultActions result =
                mockMvc.perform(get("/acessos")
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$").isArray());
        result.andExpect(jsonPath("$").isEmpty());
    }

    @Test
    public void deleteShouldReturnNoContentWhenIdExists() throws Exception {
        ResultActions result =
                mockMvc.perform(delete("/acessos/{id}", existingId)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNoContent());
    }

    @Test
    public void deleteShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
        ResultActions result =
                mockMvc.perform(delete("/acessos/{id}", nonExistingId)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }

/*    @Test
    public void deleteShouldReturnBadRequestWhenIdIsDependent() throws Exception {
        ResultActions result =
                mockMvc.perform(delete("/acessos/{id}", dependentId)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isBadRequest());
    }*/
}
