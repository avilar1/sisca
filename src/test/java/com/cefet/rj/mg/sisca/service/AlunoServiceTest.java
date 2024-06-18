package com.cefet.rj.mg.sisca.service;

import com.cefet.rj.mg.sisca.domain.aluno.Aluno;
import com.cefet.rj.mg.sisca.domain.aluno.AlunoRepository;
import com.cefet.rj.mg.sisca.domain.aluno.DadosAtualizaAluno;
import com.cefet.rj.mg.sisca.domain.aluno.DadosCadastroAluno;
import com.cefet.rj.mg.sisca.domain.usuario.DadosCadastroUsuario;
import com.cefet.rj.mg.sisca.domain.usuario.RoleEnum;
import com.cefet.rj.mg.sisca.domain.usuario.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest
@DataJpaTest
//@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@AutoConfigureMockMvc
@ActiveProfiles("test")
class AlunoServiceTest {


    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TestEntityManager em;


    @Test
    @DisplayName("Deveria trazer algum aluno")
    void detalharaluno() throws Exception {

        //given
        var usuario = cadastrarUsuario("nome", "123", "17335182743", "aa.bb@cc.dd", "21975428466");
        var aluno = cadastrarAluno(usuario, 1, 1, "2023.1");

        //when
        var alunoProcurado = alunoRepository.getReferenceById(aluno.getId_aluno());

        //then
        assertThat(alunoProcurado).isNotNull();

    }

    @Test
    @DisplayName("O número de alunos trazido deve ser o mesmo de alunos cadastrados")
    void trazerTodosAlunos() throws Exception {

        //given
        var usuario1 = cadastrarUsuario("nome1", "1234", "17335182741", "aa.bb@cc.dz", "21975428461");
        var aluno1 = cadastrarAluno(usuario1, 1, 1, "2023.1");
        var usuario2 = cadastrarUsuario("nome2", "1235", "17335182742", "aa.bb@cc.dq", "21975428462");
        var aluno2 = cadastrarAluno(usuario2, 2, 1, "2023.1");
        var usuario3 = cadastrarUsuario("nome3", "1236", "17335182743", "aa.bb@cc.dw", "21975428463");
        var aluno3 = cadastrarAluno(usuario3, 3, 1, "2023.1");

        List<Object> list = new ArrayList<>();
        list.add(aluno2);
        list.add(aluno1);
        list.add(aluno3);
        //when
        var todosAlunos = alunoRepository.findAll();

        //then
        assertThat(list.size()).isEqualTo(todosAlunos.size());

    }

    @Test
    @DisplayName("Deveria salvar um aluno com sucesso")
    void salvarAluno() throws Exception {
        // given
        var usuario = cadastrarUsuario("nome", "123", "17335182743", "aa.bb@cc.dd", "21975428466");
        var aluno = new Aluno(dadosCadastroAluno(usuario, 1, 1, "2023.1"), usuario);

        // when
        var alunoSalvo = alunoRepository.save(aluno);

        // then
        var alunoProcurado = alunoRepository.findById(alunoSalvo.getId_aluno());
        assertThat(alunoProcurado).isPresent();
        assertThat(alunoProcurado.get()).isEqualTo(alunoSalvo);
    }

    @Test
    @DisplayName("Deveria atualizar um aluno com sucesso")
    void atualizarAluno() throws Exception {
        // given
        var usuario = cadastrarUsuario("nome", "123", "17335182743", "aa.bb@cc.dd", "21975428466");
        var aluno = cadastrarAluno(usuario, 1, 1, "2023.1");
        var dadosAtualizaAluno = new DadosAtualizaAluno(aluno.getId_aluno(), 5, 2, "2023.2", 1L);

        // when

        aluno.atualizarAluno(dadosAtualizaAluno);
        var alunoAtualizado = alunoRepository.save(aluno);

        // then
        assertThat(alunoAtualizado.getMatricula_aluno()).isEqualTo(5);
        assertThat(alunoAtualizado.getStatus()).isEqualTo(2);
        assertThat(alunoAtualizado.getAno_matricula()).isEqualTo("2023.2");
    }

    private Usuario cadastrarUsuario(String nome, String senha, String cpf, String email, String telefone){
        var usuarioAluno = new Usuario(dadosCadastroUsuario(nome, senha, cpf, email, telefone));
        em.persist(usuarioAluno);
        return usuarioAluno;
    }

    private Aluno cadastrarAluno(Usuario usuario, int matricula_aluno, int status, String ano_matricula){
        var aluno = new Aluno(dadosCadastroAluno(usuario, matricula_aluno, status, ano_matricula), usuario);
        em.persist(aluno);
        return aluno;
    }

 private DadosCadastroUsuario dadosCadastroUsuario(String nome, String senha, String cpf, String email, String telefone){
    return new DadosCadastroUsuario(
            nome,
            senha,
            cpf,
            email,
            telefone,
            LocalDateTime.now(),
            RoleEnum.ALUNO
        );
    }

private DadosCadastroAluno dadosCadastroAluno(Usuario usuario, int matricula_aluno, int status, String ano_matricula){
        return new DadosCadastroAluno(
                usuario,
                matricula_aluno,
                status,
                ano_matricula
        );
}
}