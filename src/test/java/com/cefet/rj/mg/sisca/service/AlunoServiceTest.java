package com.cefet.rj.mg.sisca.service;

import com.cefet.rj.mg.sisca.domain.aluno.Aluno;
import com.cefet.rj.mg.sisca.domain.usuario.Usuario;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
class AlunoServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlunoService alunoService;

    @MockBean
    private UsuarioService usuarioService;


    @Test
    void detalharaluno() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId_usuario(1L);
        usuario.setNome("Nome do Usuário");

        Aluno aluno = new Aluno();
        aluno.setId_aluno(1L);
        aluno.setMatricula_aluno(1234);
        aluno.setUsuario(usuario);

        Mockito.when(alunoService.detalharaluno(1L)).thenReturn(aluno);

        mockMvc.perform(get("/alunos/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id_aluno").value(1))
                .andExpect(jsonPath("$.matricula_aluno").value(1234))
                .andExpect(jsonPath("$.usuario.id_usuario").value(1))
                .andExpect(jsonPath("$.usuario.nome").value("Nome do Usuário"));
    }
}