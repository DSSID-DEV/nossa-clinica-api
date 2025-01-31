package com.nossaclinica_api.reports;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.nossaclinica_api.models.dtos.BaseDeCalculo;
import com.nossaclinica_api.models.dtos.DadosDoFechamento;
import com.nossaclinica_api.models.dtos.DadosParaProntuario;
import com.nossaclinica_api.reports.helper.HeaderFooterHelper;
import com.nossaclinica_api.reports.utils.Documento;
import com.nossaclinica_api.reports.utils.FormularioEntradaSaidaOrcamentoPDF;
import com.nossaclinica_api.reports.utils.TituloPDF;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TemplateEntradaSaidaOrcamentoDefaultA5 {
    private final HeaderFooterHelper headerFooterHelper;

    private static final DadosParaProntuario NULL = null;
    private static final float WIDTH = 100;
    private static final float MAX_HEIGHT_PAGE = 825f;

    public ByteArrayOutputStream gerarPdf_A5(DadosDoFechamento inserir, String tipoDocumento) throws IOException {
        float espacamentoDoRodape = 570f;
        headerFooterHelper.setInserir(NULL);
        headerFooterHelper.setTipoDeDocumento(tipoDocumento);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document financeiro = new Document(PageSize.A5, 28f, 28f, 60f, 30f);

        try {
            var write =  PdfWriter.getInstance(financeiro, baos);
            write.setPageEvent(headerFooterHelper);
            financeiro.open();
            //MONTAR TIPO DE DOCUMENTO
            var titulo = TituloPDF.montar(tipoDocumento, 4f, 20f);
            financeiro.add(titulo);
            log.info("Height da pagina com titulo {}", financeiro.getPageSize());

            var profissional = new Paragraph(inserir.getNomeSocialResponsavel());
            financeiro.add(profissional);

            var tituloDaTabela = new PdfPTable(1);
            var tituloDescricao = inserir.isMedico() ? Documento.DESCRICAO : "Exames:";
            tituloDaTabela.setWidthPercentage(WIDTH);
            tituloDaTabela.setSpacingBefore(10f);
            var descricao = new PdfPCell(new Phrase(tituloDescricao, new Font(Font.TIMES_ROMAN, 12, Font.BOLD)));
            descricao.setBorder(Rectangle.BOTTOM);
            tituloDaTabela.addCell(descricao);
            financeiro.add(tituloDaTabela);

            //          //MONTAR FORMULARIO DO PACIENTE
            PdfPTable descritivo = FormularioEntradaSaidaOrcamentoPDF.montar(inserir, WIDTH, 5f, 1f);
//            descritivo.setSpacingBefore(10f);
            log.info("Height descritivo {}", descritivo.getTotalHeight());
            financeiro.add(descritivo);
            log.info("Height da pagina com descritivo {}", financeiro.getPageSize());
            financeiro.close();
        } catch (DocumentException e) {
            log.error(Documento.ERROR, e.getMessage());
            new RuntimeException(Documento.ERROR + e.getCause());
        }
        return baos;

    }
}
