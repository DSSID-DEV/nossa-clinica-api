package com.nossaclinica_api.reports.conteudos;

import com.lowagie.text.pdf.PdfPTable;
import com.nossaclinica_api.models.dtos.DadosParaProntuario;

import java.util.Optional;


public interface IConteudo {
    public PdfPTable montar(DadosParaProntuario inserir, Optional<Object> observacao);
}
