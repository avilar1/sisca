package com.cefet.rj.mg.sisca.service;

import com.cefet.rj.mg.sisca.domain.aluno.Aluno;
import com.cefet.rj.mg.sisca.domain.aluno.DadosCadastroAluno;
import com.cefet.rj.mg.sisca.domain.materia.DadosCadastroMateria;
import com.cefet.rj.mg.sisca.domain.materia.Materia;
import com.cefet.rj.mg.sisca.domain.professor.Professor;
import com.cefet.rj.mg.sisca.domain.turma.DadosCadastroTurma;
import com.cefet.rj.mg.sisca.domain.turma.Turma;
import com.cefet.rj.mg.sisca.domain.turmaAlunoFrequencia.TurmaAlunoFrequencia;
import com.cefet.rj.mg.sisca.domain.turmaAlunoFrequencia.TurmaAlunoFrequenciaRepository;
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

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
//@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class TurmaAlunoFrequenciaServiceTest {

    @Autowired
    private TurmaAlunoFrequenciaRepository turmaAlunoFrequenciaRepository;

    @Autowired
    private TestEntityManager em;

    private Aluno aluno;
    private Turma turma;

    @BeforeEach
    void setUp() {
        var usuario = cadastrarUsuario("nome", "123", "17335182743", "aa.bb@cc.dd", "21975428466");
        aluno = cadastrarAluno(usuario, 1, 1, "2023.1");

        var professor = cadastrarProfessor(usuario, 1);
        var materia = cadastrarMateria("Matematica");

        turma = cadastrarTurma(new DadosCadastroTurma(professor.getId_funcionario(), materia.getId_materia(), "2023.1"), professor, materia);
    }

    @Test
    @DisplayName("Deveria cadastrar a frequência com sucesso")
    void cadastrarFrequencia() {
        // given
        java.sql.Date agora = new java.sql.Date(System.currentTimeMillis());

        var turmaAlunoFrequencia = new TurmaAlunoFrequencia(aluno, turma, agora );

        // when
        var frequenciaCadastrada = turmaAlunoFrequenciaRepository.save(turmaAlunoFrequencia);

        // then
        assertThat(frequenciaCadastrada).isNotNull();
        assertThat(frequenciaCadastrada.getId()).isNotNull();
        assertThat(turmaAlunoFrequenciaRepository.getReferenceById(frequenciaCadastrada.getId())).isNotNull();
    }

    @Test
    @DisplayName("Deveria deletar a frequência com sucesso")
    void deletarFrequencia() {
        // given

        java.sql.Date agora = new java.sql.Date(System.currentTimeMillis());
        var turmaAlunoFrequencia = new TurmaAlunoFrequencia(aluno, turma, agora);

        // when
        turmaAlunoFrequenciaRepository.delete(turmaAlunoFrequencia);

        // then
        assertThat(turmaAlunoFrequenciaRepository.findById(turmaAlunoFrequencia.getId())).isEmpty();
    }

    private Usuario cadastrarUsuario(String nome, String senha, String cpf, String email, String telefone) {
        var usuarioAluno = new Usuario(dadosCadastroUsuario(nome, senha, cpf, email, telefone));
        em.persist(usuarioAluno);
        return usuarioAluno;
    }

    private Aluno cadastrarAluno(Usuario usuario, int matricula_aluno, int status, String ano_matricula) {
        var aluno = new Aluno(dadosCadastroAluno(usuario, matricula_aluno, status, ano_matricula), usuario);
        em.persist(aluno);
        return aluno;
    }
    private Professor cadastrarProfessor(Usuario usuario, int matricula_funcionario) {
        var professor = new Professor(usuario, matricula_funcionario);
        em.persist(professor);
        return professor;
    }

    private Materia cadastrarMateria(String nome) {
        var materia = new Materia(new DadosCadastroMateria(nome));
        em.persist(materia);
        return materia;
    }

    private Turma cadastrarTurma(DadosCadastroTurma dadosCadastroTurma, Professor professor, Materia materia) {
        var turma = new Turma(dadosCadastroTurma, professor, materia);
        em.persist(turma);
        return turma;
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

    private DadosCadastroAluno dadosCadastroAluno(Usuario usuario, int matricula_aluno, int status, String ano_matricula) {
        return new DadosCadastroAluno(
                usuario,
                matricula_aluno,
                status,
                ano_matricula
        );
    }
    private DadosCadastroMateria dadosCadastroMateria(String nome) {
        return new DadosCadastroMateria(nome);
    }
}
