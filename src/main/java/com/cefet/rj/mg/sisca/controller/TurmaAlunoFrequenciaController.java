package com.cefet.rj.mg.sisca.controller;

import com.cefet.rj.mg.sisca.domain.alunoTurma.AlunoTurma;
import com.cefet.rj.mg.sisca.domain.turmaAlunoFrequencia.TurmaAlunoFrequencia;
import com.cefet.rj.mg.sisca.service.AlunoTurmaService;
import com.cefet.rj.mg.sisca.service.TurmaAlunoFrequenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@RestController
@RequestMapping("/nota")
public class TurmaAlunoFrequenciaController {


    @Autowired
    private TurmaAlunoFrequenciaService turmaAlunoFrequenciaService;
    @Autowired
    private AlunoTurmaService alunoTurmaService;

    public static class NotaRequest {
        public String falta;
    }

    @PostMapping
    public TurmaAlunoFrequencia cadastrarFrequencia(@RequestParam Long idTurma, @RequestParam Long idAluno, @RequestBody NotaRequest faltaRequest) {
        AlunoTurma turmaAluno = alunoTurmaService.buscarAlunoPorTurma(idAluno, idTurma);

        Date faltaDate = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(faltaRequest.falta, formatter);
            faltaDate = Date.valueOf(localDate);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de data inválido. Use o formato yyyy-MM-dd.");
        }

        TurmaAlunoFrequencia turmaAlunoFrequencia = new TurmaAlunoFrequencia(turmaAluno.getAluno(), turmaAluno.getTurma(), faltaDate);

        turmaAlunoFrequenciaService.cadastrarFrequencia(turmaAlunoFrequencia);

        return turmaAlunoFrequencia;
    }

    @DeleteMapping
    public void deletarFalta(@RequestParam Long idTurma, @RequestParam Long idAluno, @RequestParam String falta) {
        Date faltaDate = pegarData(falta);

        TurmaAlunoFrequencia turmaAlunoFrequencia = turmaAlunoFrequenciaService.pegarFalta(idTurma, idAluno, faltaDate);

        turmaAlunoFrequenciaService.deletarFrequencia(turmaAlunoFrequencia);
    }

    private Date pegarData(String faltaRequest) {
        Date faltaDate = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(faltaRequest, formatter);
            return Date.valueOf(localDate);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de data inválido. Use o formato yyyy-MM-dd.");
        }
    }
}
