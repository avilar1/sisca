//package com.cefet.rj.mg.sisca.controller;
//
//import com.cefet.rj.mg.sisca.domain.aluno.Aluno;
//import com.cefet.rj.mg.sisca.domain.aluno.DadosCadastroAluno;
//import com.cefet.rj.mg.sisca.domain.aluno.DadosDetalhamentoAluno;
//import com.cefet.rj.mg.sisca.domain.usuario.DadosCadastroUsuario;
//import com.cefet.rj.mg.sisca.domain.usuario.RoleEnum;
//import com.cefet.rj.mg.sisca.domain.usuario.Usuario;
//import com.cefet.rj.mg.sisca.service.AlunoService;
//import com.cefet.rj.mg.sisca.service.UsuarioService;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.json.JacksonTester;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//
//@WebMvcTest(AlunoController.class)
//@AutoConfigureMockMvc
//@AutoConfigureJsonTesters
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ActiveProfiles("test")
//public class AlunoControllerTest {
//
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private JacksonTester<DadosCadastroAluno> dadosCadastroAlunoJacksonTester;
//
//    @Autowired
//    private JacksonTester<DadosDetalhamentoAluno> jsonDetalhamentoAluno;
//
//    @MockBean
//    private AlunoService alunoService;
//
//    @MockBean
//    private UsuarioService usuarioService;
//
//    @Test
//    @DisplayName("Deveria cadastrar um aluno com sucesso")
//    void cadastrarAluno() throws Exception {
//
////        var usuario = cadastrarUsuario("nome", "123", "17335182743", "aa.bb@cc.dd", "21975428466");
////        var aluno = cadastrarAluno(usuario, 1, 1, "2023.1");
//
//        var usuarioAluno = new Usuario(dadosCadastroUsuario("nome", "123", "17335182743", "aa.bb@cc.dd", "21975428466"));
//        var aluno = new Aluno(dadosCadastroAluno(usuarioAluno, 1, 1, "2023.1"),usuarioAluno);
//
//        Mockito.when(usuarioService.encontrarUsuario(usuarioAluno.getId_usuario())).thenReturn(Optional.of(usuarioAluno));
//        Mockito.when(alunoService.salvarAluno(any(Aluno.class))).thenReturn(aluno);
//
//        // Quando e Então
//        MvcResult mvcResult = mockMvc.perform(post("/cadastrar")
//                        .param("idUsuario", usuarioAluno.getId_usuario().toString())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(dadosCadastroAlunoJacksonTester.write(
//                                new DadosCadastroAluno(usuarioAluno, 1, 1, "2023.1")
//                                                ).getJson())
//                )
//                .andReturn();
//
//        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);
//        assertThat(mvcResult.getResponse().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_VALUE);
//
////        var dadosDetalhamento = new DadosDetalhamentoAluno(aluno);
////        var jsonEsperado = jsonDetalhamentoAluno.write(
////                        dadosDetalhamento)
////                .getJson();
////
////        assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo(jsonEsperado);
//    }
//
//
//
//    private DadosCadastroUsuario dadosCadastroUsuario(String nome, String senha, String cpf, String email, String telefone){
//        return new DadosCadastroUsuario(
//                nome,
//                senha,
//                cpf,
//                email,
//                telefone,
//                LocalDateTime.now(),
//                RoleEnum.ALUNO
//        );
//    }
//    private DadosCadastroAluno dadosCadastroAluno(Usuario usuario, int matricula_aluno, int status, String ano_matricula){
//        return new DadosCadastroAluno(
//                usuario,
//                matricula_aluno,
//                status,
//                ano_matricula
//        );
//    }
//
//
//}
