package com.cefet.rj.mg.sisca.service;

import com.cefet.rj.mg.sisca.domain.materia.DadosCadastroMateria;
import com.cefet.rj.mg.sisca.domain.materia.Materia;
import com.cefet.rj.mg.sisca.domain.professor.Professor;
import com.cefet.rj.mg.sisca.domain.professor.ProfessorService;
import com.cefet.rj.mg.sisca.domain.turma.DadosCadastroTurma;
import com.cefet.rj.mg.sisca.domain.turma.DadosDetalhamentoTurma;
import com.cefet.rj.mg.sisca.domain.turma.Turma;
import com.cefet.rj.mg.sisca.domain.turma.TurmaRepository;
import com.cefet.rj.mg.sisca.domain.usuario.DadosCadastroUsuario;
import com.cefet.rj.mg.sisca.domain.usuario.RoleEnum;
import com.cefet.rj.mg.sisca.domain.usuario.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class TurmaServiceTest {

    @Autowired
    TurmaRepository turmaRepository;

    @Autowired
    private TestEntityManager em;

    Turma turma;
    Professor professor;
    Materia materia;

    @BeforeEach
    void setUp() {
        var usuario = cadastrarUsuario("nome", "123", "17335182743", "aa.bb@cc.dd", "21975428466");
        professor = new Professor(usuario, 1);
        em.persist(professor);
        DadosCadastroMateria dadosCadastroMateria = new DadosCadastroMateria("Algebra Linear");
        materia = new Materia(dadosCadastroMateria);
        em.persist(materia);
        DadosCadastroTurma dados = new DadosCadastroTurma(professor.getId_funcionario(), materia.getId_materia(), "2022.2");

        turma = new Turma(dados, professor, materia);
        em.persist(turma);
        em.flush();
    }

    @Test
    @DisplayName("Turma encontrada com sucesso")
    void encontrarTurma() {
        var turmaOptional = turmaRepository.findById(turma.getId_turma());

        assertThat(turmaOptional).isNotEmpty();
    }

    @Test
    @DisplayName("Turma salvada com sucesso")
    void salvarTurma() {
        if (!professor.isProfessor(professor.getId_funcionario())) {
            throw new IllegalArgumentException("Somente funcionários com a role de PROFESSOR podem ser usados para cadastro de uma turma.");
        }

        var turmaSalva = turmaRepository.save(turma);

        var turmaEncontrada = turmaRepository.findById(turma.getId_turma());

        assertThat(turmaEncontrada).isPresent();
        assertThat(turmaEncontrada.get()).isEqualTo(turmaSalva);
    }

    @Test
    @DisplayName("Resgatou turma com sucesso")
    void pegarUmaTurma() {
        Optional<Turma> turmaExiste = turmaRepository.findById(turma.getId_turma());

        assertThat(turmaExiste).isNotEmpty();
    }

    @Test
    @DisplayName("Resgatou turma pelo professor com sucesso")
    void turmaProProfessor() {
        var turmaProfessor = turmaRepository.findByProfessor(professor.getId_funcionario());

        assertThat(turmaProfessor).isNotEmpty();
    }

    @Test
    @DisplayName("Resgatou turma por periodo com sucesso")
    void turmaPorPeriodo() {
        List<Turma> turmasPeriodo = turmaRepository.findAllBySemestreTurma(turma.getSemestre_turma());

        if (turmasPeriodo.isEmpty()) {
            throw new RuntimeException("Não existem turmas nesse período");
        }

        assertThat(turmasPeriodo).isNotEmpty();
    }

    @Test
    @DisplayName("Listar todas as turmas com sucesso")
    void listarTodas() {
        var turmaTodas =  turmaRepository.findAll().stream()
                .map(DadosDetalhamentoTurma::new)
                .collect(Collectors.toList());

        assertThat(turmaTodas).isNotEmpty();
    }

    private Usuario cadastrarUsuario(String nome, String senha, String cpf, String email, String telefone) {
        var usuarioAluno = new Usuario(dadosCadastroUsuario(nome, senha, cpf, email, telefone));
        em.persist(usuarioAluno);
        return usuarioAluno;
    }

    private DadosCadastroUsuario dadosCadastroUsuario(String nome, String senha, String cpf, String email, String telefone) {
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
}
