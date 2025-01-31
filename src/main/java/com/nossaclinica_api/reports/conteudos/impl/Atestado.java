package com.nossaclinica_api.reports.conteudos.impl;


import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.nossaclinica_api.models.dtos.DadosParaProntuario;
import com.nossaclinica_api.reports.utils.ConstantesValues;
import com.nossaclinica_api.reports.utils.Formatar;
import com.nossaclinica_api.reports.conteudos.IConteudo;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class Atestado implements IConteudo {

    private static final float[] COLUNAS_TIPO_DE_ATENDIMENTO = {0.5f, 10f};
    private static final float[] COLUNAS_IGUAIS = {50f, 50f};
    private static final int FONT_DEFAULT = Font.TIMES_ROMAN;
    private static final int FONT_SIZE = Font.DEFAULTSIZE;
    private static final Color COLOR_BLACK = Color.BLACK;
    private static final Color COLOR_CUSTOM = new Color(46, 38, 70);
    private static final int FONT_NORMAL = Font.NORMAL;
    private static final int ALIGN_JUSTIFIED = Element.ALIGN_JUSTIFIED;

    public PdfPTable montar(DadosParaProntuario inserir, Optional<Object> observacao) {
        var fontNormal = new Font(FONT_DEFAULT, FONT_SIZE, FONT_NORMAL, COLOR_BLACK);
        var fontBold = new Font(FONT_DEFAULT, FONT_SIZE, Font.BOLD, COLOR_BLACK);
        String atestadoDefault = ConstantesValues
                .ATESTADO_MEDICO.formatted("Sra",
                        "-",
                        "atendida",
                        Formatar.date(LocalDate.now()),
                        Formatar.hora(LocalDateTime.now()));

        var atestado = new Paragraph();
        atestado.add(new Chunk(atestadoDefault.split("-")[0], fontNormal));
        atestado.add(new Chunk(inserir.getPaciente(), fontBold));
        atestado.add(new Chunk(atestadoDefault.split("-")[1], fontNormal));
        atestado.setAlignment(ALIGN_JUSTIFIED);
        atestado.setSpacingBefore(10f);
        atestado.setSpacingAfter(30f);

        var table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setTotalWidth(100);
        var cell = new PdfPCell();
        cell.addElement(atestado);
        cell.setBorder(com.lowagie.text.Rectangle.NO_BORDER);
        cell.setPadding(0);
        table.addCell(cell);

        cell = new PdfPCell();
        cell.setPadding(0);
        cell.setBorder(com.lowagie.text.Rectangle.NO_BORDER);
        table.addCell(cell);

        var tableTipoDeAtendimento = new PdfPTable(2);
        tableTipoDeAtendimento.setWidthPercentage(100);
        tableTipoDeAtendimento.setTotalWidth(COLUNAS_TIPO_DE_ATENDIMENTO);
        for(var tipoAtendimento : ConstantesValues.TIPO_ATENDIMENTOS) {
            var cellMarcador = new PdfPCell(new Phrase(checkedOrNoCheked(tipoAtendimento, ConstantesValues.CONSULTA), fontNormal));
            cellMarcador.setPadding(0);
            cellMarcador.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableTipoDeAtendimento.addCell(cellMarcador);
            cellMarcador = new PdfPCell(new Phrase(ConstantesValues.ESPACO.concat(tipoAtendimento), fontNormal));
            cellMarcador.setPadding(0);
            cellMarcador.setBorder(com.lowagie.text.Rectangle.NO_BORDER);
            tableTipoDeAtendimento.addCell(cellMarcador);
        }
        tableTipoDeAtendimento.setSpacingBefore(20f);
        tableTipoDeAtendimento.setSpacingAfter(20f);
        cell.addElement(tableTipoDeAtendimento);
        cell.setBorder(com.lowagie.text.Rectangle.NO_BORDER);
        cell.setPadding(0);
        table.addCell(cell);

        var cid = new Paragraph();
        cid.add(new Chunk(ConstantesValues.CID, fontNormal));
        cid.add(new Chunk(ConstantesValues.ESPACO.concat("54214"), fontBold));
        cid.setSpacingBefore(40f);
        cid.setSpacingAfter(30f);

        cell = new PdfPCell();
        cell.addElement(cid);
        cell.setBorder(com.lowagie.text.Rectangle.NO_BORDER);
        cell.setPadding(0);
        table.addCell(cell);

        var obs = new Paragraph();
        obs.add(new Chunk(ConstantesValues.OBSERVACAO, fontBold));
        cell = new PdfPCell();
        cell.addElement(obs);
        cell.setBorder(com.lowagie.text.Rectangle.NO_BORDER);
        cell.setPadding(0);
        table.addCell(cell);
        //TODO: ADICIONAR A OBSERVAÇÃO
        obs = new Paragraph();
        obs.add(new Chunk("Testando a inserção de Observação para preenchimento do profissional em atendimento", fontNormal));
        obs.setSpacingAfter(50f);
        cell = new PdfPCell();
        cell.addElement(obs);
        cell.setBorder(com.lowagie.text.Rectangle.NO_BORDER);
        cell.setPadding(0);
        table.addCell(cell);

        var tableElement = new PdfPTable(2);
        tableElement.setWidthPercentage(100);
        tableElement.setTotalWidth(COLUNAS_IGUAIS);
        tableElement.setSpacingBefore(30f);

        var nota = new Phrase();
        nota.add(new Chunk(ConstantesValues.NOTA, new Font(FONT_DEFAULT, 9, Font.BOLD, COLOR_CUSTOM)));
        nota.add(new Chunk(ConstantesValues.NOTA_ATESTADO, new Font(FONT_DEFAULT, 9, Font.NORMAL, COLOR_CUSTOM)));
        cell = new PdfPCell(nota);
        cell.setBorderColor(COLOR_CUSTOM);
        cell.getColumn().setSpaceCharRatio(5f);
        tableElement.addCell(cell);

        cell = new PdfPCell(new Phrase(ConstantesValues.CIDADE_E_DATA.formatted(Formatar.date(LocalDate.now())), fontNormal));
        cell.setBorder(com.lowagie.text.Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        tableElement.addCell(cell);

        cell = new PdfPCell();
        cell.addElement(tableElement);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        return table;
    }

    private static String checkedOrNoCheked(String value1, String value2) {
        return value1.equalsIgnoreCase(value2) ? "X" : "";
    }

}
