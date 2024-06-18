package com.cefet.rj.mg.sisca.service;

import com.cefet.rj.mg.sisca.domain.aluno.Aluno;
import com.cefet.rj.mg.sisca.domain.aluno.DadosCadastroAluno;
import com.cefet.rj.mg.sisca.domain.materia.DadosCadastroMateria;
import com.cefet.rj.mg.sisca.domain.materia.Materia;
import com.cefet.rj.mg.sisca.domain.professor.Professor;
import com.cefet.rj.mg.sisca.domain.turma.DadosCadastroTurma;
import com.cefet.rj.mg.sisca.domain.turma.Turma;
import com.cefet.rj.mg.sisca.domain.turmaAlunoNota.DadosAtualizaTurmaAlunoNota;
import com.cefet.rj.mg.sisca.domain.turmaAlunoNota.TurmaAlunoNota;
import com.cefet.rj.mg.sisca.domain.turmaAlunoNota.TurmaAlunoNotaId;
import com.cefet.rj.mg.sisca.domain.turmaAlunoNota.TurmaAlunoNotaRepository;
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
class TurmaAlunoNotaServiceTest {

    @Autowired
    private TurmaAlunoNotaRepository turmaAlunoNotaRepository;

    @Autowired
    private TestEntityManager em;

//    @Autowired
//    private TurmaAlunoNotaService turmaAlunoNotaService;

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
    @DisplayName("Deveria salvar a referência de uma nota de aluno relativo a uma turma com sucesso")
    void salvarNotaAluno() {
        // given
        var turmaAlunoNota = new TurmaAlunoNota(aluno, turma);
        em.persist(turmaAlunoNota);

        // when
        var notaSalva = turmaAlunoNotaRepository.save(turmaAlunoNota);

        // then
        assertThat(notaSalva).isNotNull();
    }

    @Test
    @DisplayName("Deveria atualizar a nota do aluno com sucesso")
    void atualizarNotaAluno() {
        // given
        var turmaAlunoNota = new TurmaAlunoNota(aluno, turma);
        em.persist(turmaAlunoNota);

        var dadosAtualizaNota = new DadosAtualizaTurmaAlunoNota(8.5f, 9.0f, 7.5f);

        // when
        var turmaAlunoNotaId = new TurmaAlunoNotaId(aluno.getId_aluno(), turma.getId_turma());
        var turmaAlunoNotaEncontrado = turmaAlunoNotaRepository.getReferenceById(turmaAlunoNotaId);
        turmaAlunoNotaEncontrado.alterarNotas(dadosAtualizaNota);
        var notaAtualizada = turmaAlunoNotaRepository.save(turmaAlunoNota);

        // then
        assertThat(notaAtualizada.getP1()).isEqualTo(8.5f);
        assertThat(notaAtualizada.getP2()).isEqualTo(9.0f);
        assertThat(notaAtualizada.getPf()).isEqualTo(7.5f);
        assertThat(notaAtualizada.getP1()).isEqualTo(turmaAlunoNotaEncontrado.getP1());
        assertThat(notaAtualizada.getP2()).isEqualTo(turmaAlunoNotaEncontrado.getP2());
        assertThat(notaAtualizada.getPf()).isEqualTo(turmaAlunoNotaEncontrado.getPf());
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
