package com.cefet.rj.mg.sisca.service;


import com.cefet.rj.mg.sisca.domain.aluno.Aluno;
import com.cefet.rj.mg.sisca.domain.aluno.DadosCadastroAluno;
import com.cefet.rj.mg.sisca.domain.alunoTurma.*;
import com.cefet.rj.mg.sisca.domain.materia.DadosCadastroMateria;
import com.cefet.rj.mg.sisca.domain.materia.Materia;
import com.cefet.rj.mg.sisca.domain.professor.Professor;
import com.cefet.rj.mg.sisca.domain.turma.DadosCadastroTurma;
import com.cefet.rj.mg.sisca.domain.turma.Turma;
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
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
//@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class AlunoTurmaServiceTest {

    @Autowired
    private AlunoTurmaRepository alunoTurmaRepository;

    @Autowired
    private TestEntityManager em;

    //@Autowired
    //private AlunoTurmaService alunoTurmaService;

    Aluno aluno;
    Turma turma;


    @BeforeEach
    void setUp() {


        var usuario = cadastrarUsuario("nome", "123", "17335182743", "aa.bb@cc.dd", "21975428466");
        aluno = cadastrarAluno(usuario, 1, 1, "2023.1");
        //turma = cadastrarTurma("Turma 1", "Descrição da Turma");

        var professor = cadastrarProfessor(usuario, 1);
        var materia = cadastrarMateria("Matematica");



        turma = cadastrarTurma(new DadosCadastroTurma(professor.getId_funcionario(), materia.getId_materia(), "2023.1"), professor, materia);
    }

    @Test
    @DisplayName("Deveria salvar um AlunoTurma com sucesso")
    void salvarAlunoTurma() {

        // given
        var alunoTurma = new AlunoTurma(turma, aluno, StatusAlunoTurma.CURSANDO);

        // when
        var alunoTurmaSalvo = alunoTurmaRepository.save(alunoTurma);

        // then
        var alunoTurmaProcurado = alunoTurmaRepository.findById(new TurmaAlunoId(aluno.getId_aluno(), turma.getId_turma()));
        assertThat(alunoTurmaProcurado).isPresent();
        assertThat(alunoTurmaProcurado.get()).isEqualTo(alunoTurmaSalvo);
    }

    @Test
    @DisplayName("Deveria buscar alunos por turma com sucesso")
    void buscarAlunosPorTurma() {
        // given
        var alunoTurma = new AlunoTurma(turma, aluno, StatusAlunoTurma.CURSANDO);
        em.persist(alunoTurma);

        // when
        var alunosPorTurma = alunoTurmaRepository.findByIdTurma(turma.getId_turma()).stream()
                .map(DadosDetalhamentoAlunoTurma::new)
                .collect(Collectors.toList());


        // then
        assertThat(alunosPorTurma).isNotEmpty();
    }

    @Test
    @DisplayName("Deveria buscar um aluno por turma com sucesso")
    void buscarAlunoPorTurma() {
        // given
        var alunoTurma = new AlunoTurma(turma, aluno, StatusAlunoTurma.CURSANDO);
        em.persist(alunoTurma);

        TurmaAlunoId turmaAlunoId = new TurmaAlunoId(aluno.getId_aluno(), turma.getId_turma());

        // when
        var alunoPorTurma = alunoTurmaRepository.findById(turmaAlunoId);

        // then
        assertThat(alunoPorTurma).isNotNull();
    }

    @Test
    @DisplayName("Deveria trancar matrícula com sucesso")
    void trancarMatricula() {
        // given
        var alunoTurma = new AlunoTurma(turma, aluno, StatusAlunoTurma.CURSANDO);
        em.persist(alunoTurma);

        alunoTurma.trancarMatricula();

        // when
        alunoTurmaRepository.save(alunoTurma);

        // then
        var alunoTurmaProcurado = alunoTurmaRepository.findById(new TurmaAlunoId(aluno.getId_aluno(), turma.getId_turma()));
        assertThat(alunoTurmaProcurado).isPresent();
        assertThat(alunoTurmaProcurado.get().isMatriculaTrancada()).isTrue();
    }

    @Test
    @DisplayName("Deveria deletar um AlunoTurma com sucesso")
    void deletarAlunoTurma() {
        // given
        var alunoTurma = new AlunoTurma(turma, aluno, StatusAlunoTurma.CURSANDO);
        em.persist(alunoTurma);

        // when
        alunoTurmaRepository.delete(alunoTurma);

        // then
        var alunoTurmaProcurado = alunoTurmaRepository.findById(new TurmaAlunoId(aluno.getId_aluno(), turma.getId_turma()));
        assertThat(alunoTurmaProcurado).isNotPresent();
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
