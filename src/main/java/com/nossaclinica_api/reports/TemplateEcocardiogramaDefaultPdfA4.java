package com.nossaclinica_api.reports;


import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.nossaclinica_api.models.dtos.DadosParaProntuario;
import com.nossaclinica_api.reports.utils.Documento;
import com.nossaclinica_api.reports.utils.Formatar;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class TemplateEcocardiogramaDefaultPdfA4 {

    @Value("${nossaclinica-api.images.path-root}")
    private String pathRoot;

    @Value("${nossaclinica-api.images.logomarca}")
    private String pathLogomarca;

    @Value("${nossaclinica-api.config.documentos}")
    private String diretorio;

    public File gerarPdf_A4(DadosParaProntuario inserir, String tipoDocumento, Optional<Object> laudoMedico) throws IOException {
        float espacamentoDoRodape = 570f;
        var path = new File(diretorio);
        if (!path.exists()) {
            path.mkdir();
        }

        var filePDF = new File(path + "/".concat(tipoDocumento).concat(Documento.EXTENSAO));

        Document prontuario = new Document(PageSize.A4, 36f, 36f, 5f, 0f);
         try {
            PdfWriter.getInstance(prontuario, new FileOutputStream(filePDF));
            prontuario.open();
            //MONTAR CABEÇALHO DO DOCUMENTO
            try {
                var cabecalho = montarCabecalhoDaPagina();
                prontuario.add(cabecalho);
            } catch (Exception e) {
                log.error(Documento.ERRO_AO_CARREGAR_IMAGE_CABECALHO, e.getMessage());
            }

//            //MONTAR FORMULARIO DO PACIENTE
            PdfPTable formulario = montarFormularioDoPaciente(inserir);
            formulario.setSpacingBefore(0f);
            prontuario.add(formulario);
//
////            //MONTAR TIPO DE DOCUMENTO
            var titulo = montarTitulo(tipoDocumento);
            prontuario.add(titulo);

            if(Documento.requerAssinaturaMedica(tipoDocumento)) {
                var assinatura = montarAssinatura(inserir);
                prontuario.add(assinatura);
                espacamentoDoRodape = 5f;
            }
////            //MONTAR O RODAPÉ
            var rodape = montarRodape();
            rodape.setSpacingBefore(espacamentoDoRodape);
            prontuario.add(rodape);

        } catch (IOException | DocumentException e) {
            log.error(Documento.ERROR, e.getMessage());
            new RuntimeException(Documento.ERROR + e.getCause());
        } finally {
            prontuario.close();
//            write.close();
        }
        return filePDF;
    }

    private PdfPTable montarAssinatura(DadosParaProntuario inserir) {
        float espacamento = 555f;
        var crm = String.format("%s/%s %s",  inserir.getConselho(),
                        inserir.getUf(),
                        inserir.getRegistro());
        var rqe = String.format("RQE: %s", inserir.getRqes().replace(" ", " - "));

        var assinatura = new PdfPTable(1);

        assinatura.setWidthPercentage(45f);
        assinatura.setHorizontalAlignment(Element.ALIGN_CENTER);
        var cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setBorder(Rectangle.TOP);
        cell.setBorderColorTop(Color.BLACK);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPhrase(new Phrase(inserir.getEspecialista(), new Font(Font.TIMES_ROMAN, 10)));
        assinatura.addCell(cell);
        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPhrase(new Phrase(crm, new Font(Font.TIMES_ROMAN, 8)));
        assinatura.addCell(cell);
        if(StringUtils.isNotBlank(inserir.getRqes().trim())) {
            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPhrase(new Phrase(rqe, new Font(Font.TIMES_ROMAN, 8)));
            assinatura.addCell(cell);
            espacamento = 543f;
        }
        assinatura.setSpacingBefore(espacamento);
        return assinatura;
    }

    private PdfPTable  montarRodape() {
        var tableRodape = new PdfPTable(1);
        tableRodape.setWidthPercentage(100);
        var cellRodape = new PdfPCell();
        cellRodape.setPadding(0);
        cellRodape.setBorderColorTop(new Color(46, 38, 70));
        cellRodape.setBorder(Rectangle.TOP);
        var rodape = new Paragraph();
        rodape.add(new Chunk(Documento.ENDERECO_CLINICA
                .concat(Documento.CIDADE.concat(Documento.QUEBRA_LINHA)), new Font(Font.TIMES_ROMAN, 10, Font.BOLDITALIC)));
        rodape.add(new Chunk(Documento.CONTATO
                .concat(Documento.CONTATOS)
                .concat(Documento.QUEBRA_LINHA), new Font(Font.TIMES_ROMAN, 9, Font.BOLDITALIC)));
        rodape.add(new Chunk(Documento.CNPJ, new Font(Font.TIMES_ROMAN, 9, Font.BOLDITALIC)));
        rodape.setAlignment(Element.ALIGN_CENTER);
        cellRodape.addElement(rodape);
        tableRodape.addCell(cellRodape);
        log.info(Documento.RODAPE_ADICIONADO_COM_SUCESSO);
        return tableRodape;
    }

    private static Paragraph montarTitulo(String tipoArquivo) {
        var titulo = new Paragraph(tipoArquivo, new Font(Font.TIMES_ROMAN, 18, Font.BOLD, Color.BLACK));
        titulo.setAlignment(Element.ALIGN_CENTER);
        titulo.setSpacingBefore(4);
        titulo.setSpacingAfter(20);
        log.info(Documento.TITULO_ADICIONADO_COM_SUCESSO);
        return titulo;
    }

    private PdfPTable montarFormularioDoPaciente(DadosParaProntuario inserir) throws DocumentException {
        var formulario = new PdfPTable(2);
        formulario.setWidthPercentage(100);
        formulario.setSpacingAfter(10f);

        var larguraDasColunas = Documento.LARGURA_DAS_COLUNAS;
        formulario.setWidths(larguraDasColunas);

        adicionarCelulas(formulario, Documento.FIELD_PACIENTE, Formatar.string(inserir.getPaciente()), true);
        adicionarCelulas(formulario, Documento.FIELD_DATA_NASCIMENTO, Formatar.date(inserir.getNascidoEm()), false);
        adicionarCelulas(formulario, Documento.FIELD_ENDERECO, Formatar.string(inserir.getTipoLogradouro() + " "
                + inserir.getLogradouro() + ", "+ inserir.getNumero()), true);
        adicionarCelulas(formulario, Documento.FIELD_CONTATO, inserir.getContato(), false);
        adicionarCelulas(formulario, Documento.FIELD_ESPECIALISTA, inserir.getEspecialista(), true);
        adicionarCelulas(formulario, Documento.FIELD_DATA_DO_ATENDIMENTO, Formatar.date(LocalDate.now()), false);

        log.info(Documento.FORMULARIO_ADICIONADO_COM_SUCESSO);
        formulario.setSpacingBefore(1f);
        return formulario;
    }

    private void adicionarCelulas(PdfPTable formulario, String field, String valor, boolean esquerda) throws DocumentException {
        var negrito = new Font(Font.TIMES_ROMAN, 12, Font.BOLD);
        var normal = new Font(Font.TIMES_ROMAN, 12);
        var alinhamento = esquerda ? Element.ALIGN_LEFT : Element.ALIGN_RIGHT;

        //NOME DO CAMPO
        var label = new PdfPCell(new Phrase(field, negrito));
        label.setBorder(Rectangle.NO_BORDER);
        label.setPadding(0);
        label.setHorizontalAlignment(alinhamento);
//        formulario.addCell(label);

        //VALOR DO CAMPO
        var entrada = new PdfPCell(new Phrase(valor, normal));
        entrada.setBorder(Rectangle.NO_BORDER);
        entrada.setPadding(0);
        entrada.setHorizontalAlignment(alinhamento);
//        formulario.addCell(entrada);

//        COLUNA FORMATADO
        var coluna = montarColuna(label, entrada, esquerda);
        formulario.addCell(coluna);
    }

    private PdfPCell montarColuna(PdfPCell label, PdfPCell entrada, boolean esquerda) throws DocumentException {
        var alinhamento = esquerda ? Element.ALIGN_LEFT : Element.ALIGN_RIGHT;
        var larguraDaColuna = new float[]{2.2f, 10f};
        var coluna = new PdfPTable(2);
        if(esquerda) {
        coluna.setWidths(larguraDaColuna);
        }
        coluna.setWidthPercentage(100);
        coluna.setSpacingBefore(0f);
        coluna.setSpacingAfter(0f);
        label.setHorizontalAlignment(alinhamento);
        label.setBorder(Rectangle.NO_BORDER);
        label.setPadding(0);
        coluna.addCell(label);
        entrada.setBorder(Rectangle.NO_BORDER);
        coluna.addCell(entrada);
        coluna.setHorizontalAlignment(alinhamento);
        var cell = new PdfPCell();
        cell.addElement(coluna);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setPadding(0);
        return cell;
    }

    private PdfPTable montarCabecalhoDaPagina() throws IOException {
        String urlCabecalho = montarUrlDoCabecalho();
        var img = Image.getInstance(urlCabecalho);
        img.setAlt("cabeçalho");
        img.setAlignment(Element.ALIGN_CENTER);
        var cabecalho = new PdfPTable(1);
        cabecalho.setWidthPercentage(100);
        var cellCabecalho = new PdfPCell();
        cellCabecalho.setBorder(Rectangle.NO_BORDER);
        cellCabecalho.addElement(img);
        cabecalho.addCell(cellCabecalho);
            return cabecalho;
    }

    private String montarUrlDoCabecalho() {
        return pathRoot.concat(pathLogomarca).concat(Documento.CABECALHO);
    }
}
