package com.nossaclinica_api.reports.conteudos.impl;

import com.lowagie.text.pdf.PdfPTable;
import com.nossaclinica_api.models.dtos.DadosParaProntuario;
import com.nossaclinica_api.reports.conteudos.IConteudo;

import java.util.Optional;

public class LaudoEcocardiograma implements IConteudo {
    @Override
    public PdfPTable montar(DadosParaProntuario inserir, Optional<Object> observacao) {
        return null;
    }
}
