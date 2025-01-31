package com.nossaclinica_api.reports;


import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.nossaclinica_api.models.dtos.DadosColposcopio;
import com.nossaclinica_api.models.dtos.LaudoColposcopio;
import com.nossaclinica_api.reports.helper.HeaderFooterHelper;
import com.nossaclinica_api.reports.utils.Documento;
import com.nossaclinica_api.reports.utils.Formatar;
import com.nossaclinica_api.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;

import static com.nossaclinica_api.reports.utils.Documento.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class TemplateProntuarioColposcopioDefaultPdfA4 {

    private static final int TOTAL_LINHAS = 45;
    private static final int TOTAL_COLUNA = 14;
    private static final String STRING_VAZIA = "";
    private static final float[] DOIS_POR_OITO = {0.7f, 9.3f};
    private static final float CENTRO_X = 300f;
    private static final float CENTRO_Y = 372f;
    private static final float RADIUS = 65f;
    private final HeaderFooterHelper headerFooterHelper;
    private static final float WIDTH = 100;
    private static final int ALIGN_CENTER = Element.ALIGN_CENTER;
    private static final int ALIGN_RIGHT = Element.ALIGN_RIGHT;
    private static final int ALIGN_LEFT = Element.ALIGN_LEFT;
    private static final int NO_BORDER = Rectangle.NO_BORDER;
    private static final Font FONT_BOLD = new Font(Font.TIMES_ROMAN, 10, Font.BOLD);
    private static final int FONT_TIMES = Font.TIMES_ROMAN;
    private static final Font FONT_NORMAL = new Font(Font.TIMES_ROMAN, 10, Font.NORMAL);
    private static final float MAX_HEIGHT_PAGE = 825f;

    public ByteArrayOutputStream gerarPdf_A4(LaudoColposcopio inserir, String tipoDocumento) throws IOException {
        headerFooterHelper.setInserir(null);
        headerFooterHelper.setTipoDeDocumento(tipoDocumento);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document prontuario = new Document(PageSize.A4, 24f, 24f, 100f, 30f);
        try {
            var write =  PdfWriter.getInstance(prontuario, baos);
            write.setPageEvent(headerFooterHelper);
            prontuario.open();

//            //MONTAR FORMULARIO DO PACIENTE
            var formulario = montarFormularioDoPaciente(inserir);
            formulario.setSpacingBefore(0f);
            formulario.setSpacingAfter(15f);
            prontuario.add(formulario);

            var conteudo = montarEstruturaBase();

            var cellMesclar2 = new PdfPCell();
            cellMesclar2.setColspan(2);
            cellMesclar2.setBorder(NO_BORDER);


            var analisados = MontarTabelas(inserir);
            var tableColumn1 = new PdfPTable(1);
            tableColumn1.setWidthPercentage(90);
            var  column1 = new PdfPCell();

            var tableColumn2 = new PdfPTable(1);
            tableColumn2.setWidthPercentage(90);
            var  column2 = new PdfPCell();

            var tableColumn3 = new PdfPTable(1);
            tableColumn3.setWidthPercentage(90);
            var  column3 = new PdfPCell();

            var contaColuna = 0;

            while(contaColuna < 3) {
               if(contaColuna == 0) {
                    processarCelula1(tableColumn1, analisados);
                }
                if(contaColuna == 1) {
                    processarCelula2(tableColumn2, analisados, inserir);
                }
                if(contaColuna == 2) {
                    processarCelula3(tableColumn3, analisados, inserir);
                }

                contaColuna ++;
            }

            column1.addElement(tableColumn1);
            column1.setBorder(NO_BORDER);
            conteudo.addCell(column1);

            column2.addElement(tableColumn2);
            column2.setBorder(NO_BORDER);
            conteudo.addCell(column2);

            column3.addElement(tableColumn3);
            column3.setBorder(NO_BORDER);
            conteudo.addCell(column3);

            prontuario.add(conteudo);

            adocionarRetangulo(write, 37f, 567f, 339f,10f);
            adocionarRetangulo(write, 37f, 557f, 339f,10f);

            criarCirculo(write);


            prontuario.close();

        } catch (DocumentException e) {
            log.error(Documento.ERROR, e.getMessage());
            new RuntimeException(Documento.ERROR + e.getCause());
        }
        return baos;
    }

    private void processarCelula1(PdfPTable table, LinkedHashMap<String, PdfPCell> analisados) {
        if(analisados.containsKey(COLO_UTERINO.replace(":", ""))){
                table.addCell(analisados.get(COLO_UTERINO.replace(":", "")));
            adicionarColumMesclada(table);
        }
        if(analisados.containsKey(ZONA_DE_TRANSFORMACAO_NORMAL.replace(":", ""))){
            table.addCell(analisados.get(ZONA_DE_TRANSFORMACAO_NORMAL.replace(":", "")));
            addicionarEspaco(table,11.3f);
            adicionarCelula(table, "Última Glândula:", true);
            addicionarEspaco(table,11.3f);
        }
        if(analisados.containsKey(VASCULARIZACAO.replace(":", ""))){
            table.addCell(analisados.get(VASCULARIZACAO.replace(":", "")));
            addicionarEspaco(table,11.3f);
        }

        if(analisados.containsKey(TESTE_DE_SHILLER.replace(":", ""))){
            table.addCell(analisados.get(TESTE_DE_SHILLER.replace(":", "")));
            addicionarEspaco(table,11.3f);
        }
        addicionarEspaco(table,11.3f);
    }

    private void processarCelula2(PdfPTable table, LinkedHashMap<String, PdfPCell> analisados, LaudoColposcopio inserir) {
        if(analisados.containsKey(SECRECAO.replace(":", ""))){
            table.addCell(analisados.get(SECRECAO.replace(":", "")));
            adicionarColumMesclada(table);
        }
        if(analisados.containsKey(ECTOPIA.replace(":", ""))){
            table.addCell(analisados.get(ECTOPIA.replace(":", "")));
            addicionarEspaco(table,33.9f);
            adicionarCelula(table, inserir.getUltimaGladula(), false);
            addicionarEspaco(table,143f);
        }
        if(analisados.containsKey(ZONA_DE_TRANSFORMACAO_ANORMAL.replace(":", ""))){
            table.addCell(analisados.get(ZONA_DE_TRANSFORMACAO_ANORMAL.replace(":", "")));
            addicionarEspaco(table,11.3f);
        }
        addicionarEspaco(table,11.3f);
    }

    private void processarCelula3(PdfPTable table, LinkedHashMap<String, PdfPCell> analisados, LaudoColposcopio inserir) {
        if(analisados.containsKey(ORIFICIO_CERVICAL.replace(":", ""))){
            table.addCell(analisados.get(ORIFICIO_CERVICAL.replace(":", "")));
            addicionarEspaco(table,11.3f);
        }
        if(analisados.containsKey(MUCOSA.replace(":", ""))){
            table.addCell(analisados.get(MUCOSA.replace(":", "")));
            addicionarEspaco(table,11.3f);
        }
        if(analisados.containsKey(JUNCAO_ESCAMO_COLUNAR.replace(":", ""))){
            table.addCell(analisados.get(JUNCAO_ESCAMO_COLUNAR.replace(":", "")));
            adicionarColumMesclada(table);
            adicionarCelula(table, "", false);
            adicionarCelula(table, "", false);
            adicionarCelula(table, "", false);
            adicionarCelula(table, "", false);
            adicionarCelula(table, "", false);
            adicionarCelula(table, "", false);
            addicionarEspaco(table,11.3f);
        }
        if(analisados.containsKey(OUTROS_ACHADOS.replace(":", ""))){
            table.addCell(analisados.get(OUTROS_ACHADOS.replace(":", "")));
            addicionarEspaco(table,11.3f);
        }
        addicionarEspaco(table,11.3f);
    }

    private void adicionarCelula(PdfPTable table, String value, boolean left) {
        var tableCelula = new PdfPTable(1);
        tableCelula.setWidthPercentage(100);
        var cell = new PdfPCell();
        var align = left ? Element.ALIGN_LEFT : ALIGN_RIGHT;
        cell.addElement(new Phrase(value, new Font(Font.TIMES_ROMAN, 9, Font.ITALIC)));
        cell.setHorizontalAlignment(align);
        cell.setBorder(NO_BORDER);
        if(Utils.isNull(value)) {
            cell.setFixedHeight(10f);
        }
        tableCelula.addCell(cell);
        cell = new PdfPCell();
        if(left) {
        cell.setBorder(NO_BORDER);
        }
        cell.addElement(tableCelula);
        table.addCell(cell);
    }

    private void addicionarEspaco(PdfPTable table, float altura) {
        var cell = new PdfPCell();
        cell.setBorder(NO_BORDER);
        cell.setFixedHeight(altura);
        table.addCell(cell);
    }
    private void adocionarRetangulo(PdfWriter write, float posicaoX,
                                    float posicaoY, float width, float height) {
        PdfContentByte canvas = write.getDirectContent();

        canvas.setRGBColorFill(0,0,0);
        canvas.setLineWidth(0.3f);
        canvas.rectangle(posicaoX, posicaoY, width, height);
        canvas.stroke();
    }

    private void adicionarColumMesclada(PdfPTable table) {
       var cell = new PdfPCell();
       cell.setBorder(NO_BORDER);
       cell.setFixedHeight(40f);
       cell.setColspan(2);
       table.addCell(cell);
    }


    private void criarColuna(PdfPTable tableColumn, int coluna) {
        var cell = new PdfPCell();
        cell.setBorder(NO_BORDER);
        cell.setFixedHeight(9f);
        tableColumn.addCell(cell);

        if(coluna == 1) {
            cell = celulaFormatada(coluna);
            tableColumn.addCell(cell);
            cell = celulaFormatada(coluna);
            tableColumn.addCell(cell);
        } else if(coluna == 2) {
            cell = celulaFormatada(coluna);
            tableColumn.addCell(cell);
            cell = celulaFormatada(coluna);
            tableColumn.addCell(cell);
        }

        cell = new PdfPCell();
        cell.setBorder(NO_BORDER);
        cell.setFixedHeight(9f);
        tableColumn.addCell(cell);
    }

    private PdfPCell celulaFormatada(int coluna) {
        var cell = new PdfPCell();
        if(coluna == 1) {
            cell.setFixedHeight(9f);
            cell.setBorderColorLeft(Color.BLACK);
            cell.setBorderColorBottom(Color.BLACK);
            cell.setBorderColorRight(Color.WHITE);
            cell.setBorderColorTop(Color.BLACK);
        }
        if(coluna == 2) {
            cell.setFixedHeight(9f);
            cell.setBorderColorLeft(Color.WHITE);
            cell.setBorderColorRight(Color.BLACK);
            cell.setBorderColorBottom(Color.BLACK);
            cell.setBorderColorTop(Color.BLACK);
        }
        return cell;
    }

    private void criarCirculo(PdfWriter write) {
        var cell = new PdfPCell();
        PdfContentByte canvas = write.getDirectContent();

        canvas.setRGBColorFill(0,0,0);
        canvas.setLineWidth(0.5f);
        canvas.circle(CENTRO_X, CENTRO_Y, RADIUS);
        canvas.stroke();

    }


    private PdfPCell mesclar2Colunas(String value, boolean checado, Font fontStyles, boolean titulo) {
        PdfPCell cell = null;
        if(titulo) {
            cell = new PdfPCell(new Phrase(value, fontStyles));
            cell.setBorderColor(Color.BLACK);
            cell.setHorizontalAlignment(ALIGN_LEFT);
            cell.setVerticalAlignment(ALIGN_CENTER);
        } else {
            cell = preencherDuasColunas(value, checado);
        }
        return cell;
    }

    private PdfPCell preencherDuasColunas(String value, boolean checado) {
        value = Utils.isNull(value) ? "" : value;
        var font = value.length() > 24  ?
                new Font(Font.TIMES_ROMAN, 8,  Font.ITALIC)
                : new Font(Font.TIMES_ROMAN, 9,  Font.ITALIC);
        var achados = new PdfPTable(2);
        achados.setWidthPercentage(WIDTH);
        achados.setTotalWidth(DOIS_POR_OITO);
        var cellValues = new PdfPCell(new Phrase(checado ? "X" : "", font));
        cellValues.setBorderColor(Color.BLACK);
        cellValues.setVerticalAlignment(ALIGN_CENTER);
        cellValues.setHorizontalAlignment(ALIGN_CENTER);
        cellValues.setFixedHeight(9f);
        cellValues.setPadding(0);
        if(Utils.isNull(value)) {
            cellValues.setPadding(7f);
        }
        achados.addCell(cellValues);

        cellValues = new PdfPCell(new Phrase(value, font));
        cellValues.setBorder(NO_BORDER);
        cellValues.setHorizontalAlignment(ALIGN_LEFT);
        cellValues.setVerticalAlignment(ALIGN_CENTER);
        cellValues.setPadding(0);
        cellValues.setPaddingLeft(2f);
        cellValues.setFixedHeight(9f);
        achados.addCell(cellValues);
        var cellAchados = new PdfPCell();
        cellAchados.addElement(achados);
        cellAchados.setPadding(0);
        cellAchados.setBorder(NO_BORDER);
        return cellAchados;
    }

    private PdfPCell mesclar4Colunas(String value, Font fontStyles, boolean border) {
        var linhaMesclarColuna4 = new PdfPCell(new Phrase(value, fontStyles));
        linhaMesclarColuna4.setColspan(4);
        if(border) {
            linhaMesclarColuna4.setBorderColor(Color.BLACK);
            return linhaMesclarColuna4;
        }
        linhaMesclarColuna4.setBorder(NO_BORDER);
        return linhaMesclarColuna4;
    }

    private PdfPCell mesclarTodasColunas() {
        var linhafullMesclada = new PdfPCell();
        linhafullMesclada.setColspan(14);
        linhafullMesclada.setBorder(NO_BORDER);
        return linhafullMesclada;
    }

    private void adicionarLinha(int contaLinha, LaudoColposcopio inserir, PdfPTable conteudo) {



        var contaColuna = 0;

        while(contaColuna < 5) {
            boolean mesclarTodasASLinhas = contaLinha == 1;
            boolean naoMesclar = contaColuna %2 == 0;
            adicionarColuna(inserir, conteudo, mesclarTodasASLinhas, naoMesclar);
            contaColuna ++;
        }
    }

//    private List<PdfPCell> MontarTabelas(LaudoColposcopio inserir) {
    private LinkedHashMap<String, PdfPCell> MontarTabelas(LaudoColposcopio inserir) {
        List<PdfPCell> tabelas = new ArrayList<>();
        LinkedHashMap<String, PdfPCell> linkedHashMap = new LinkedHashMap<>();
        for(var analisado : inserir.getLaudo().entrySet()) {
            var cell = montarTabela(analisado);
            cell.setBorder(NO_BORDER);
            linkedHashMap.put(analisado.getKey(), cell);
            tabelas.add(cell);
        }
//        return tabelas;
        return linkedHashMap;
    }

    private PdfPCell montarTabela(Map.Entry<String, List<DadosColposcopio>> analisado) {
        var pdfCell = new PdfPCell();

        var tabela = new PdfPTable(1);
        tabela.setWidthPercentage(WIDTH);
        var cellTitulo = mesclar2Colunas(analisado.getKey(), false, FONT_BOLD, true);
        tabela.addCell(cellTitulo);

        var cellWhite = new PdfPCell();
        cellWhite.setColspan(2);
        cellWhite.setPadding(3f);
        cellWhite.setBorder(NO_BORDER);
        tabela.addCell(cellWhite);

        var tabelaDeAchados = new PdfPTable(1);
        tabelaDeAchados.setWidthPercentage(WIDTH);
       // var cell = 0;
        for (var achados : analisado.getValue()) {
            var cell = mesclar2Colunas(achados.getDescricao(), achados.isAchado(), FONT_NORMAL, false);
            tabelaDeAchados.addCell(cell);
        }
        var cellTable = new PdfPCell();
        cellTable.addElement(tabelaDeAchados);
        cellTable.setBorderColor(Color.BLACK);
        tabela.addCell(cellTable);
        pdfCell.addElement(tabela);
        pdfCell.setBorderColor(Color.BLACK);

        return pdfCell;
    }

    private void adicionarColuna(LaudoColposcopio inserir, PdfPTable conteudo, boolean mesclarTodasASLinhas, boolean naoMesclar) {
        PdfPCell cell = null;

        if(mesclarTodasASLinhas) {
            cell = mesclarTodasColunas();
            cell.setBorder(NO_BORDER);
            conteudo.addCell(cell);
        }

    }

    private PdfPCell montarColunaEsquerda(Set<Map.Entry<String, List<DadosColposcopio>>> entry) {
        var pdfCell = new PdfPCell();
        var cell = new Cell();

        var tabela = montarTabelaDaColuna(1);
        tabela.setWidthPercentage(WIDTH);
        var cellTitulo = montarCellTitulo(COLO_UTERINO);
        tabela.addCell(cellTitulo);
        cell.add(tabela);

        tabela = montarTabelaDaColuna(1);
        tabela.setWidthPercentage(WIDTH);
        cellTitulo = montarCellTitulo("");
        tabela.addCell(cellTitulo);
        cell.add(tabela);

        tabela = montarTabelaDaColuna(2);
        tabela.setWidthPercentage(WIDTH);
        float[] columnsWidth = {2f, 10};
        tabela.setTotalWidth(columnsWidth);
//        carregarTabela(tabela, Documento.COLO_UTERINO, entry);
        cell.add(tabela);
        pdfCell.addElement(cell);
        return pdfCell;
    }

//    private void carregarTabela(PdfPTable tabela, String conteudoPara Set<Map.Entry<String, Object>> entry) {
//
//    }

    private PdfPCell montarCellTitulo(String titulo) {
        var cell = new PdfPCell(new Phrase(titulo, FONT_BOLD));
        cell.setHorizontalAlignment(ALIGN_LEFT);
        cell.setBorderColor(Color.BLACK);
        return cell;
    }

    private PdfPTable montarTabelaDaColuna(int numColumns) {
        var coluna = new PdfPTable(numColumns);
        coluna.setWidthPercentage(WIDTH);
        return coluna;
    }

    private PdfPTable montarEstruturaBase() {
        var estruturaBase = new PdfPTable(3);
        estruturaBase.setWidthPercentage(WIDTH);
        estruturaBase.setSpacingBefore(5f);
        return estruturaBase;
    }

//    private PdfPTable montarAssinatura(DadosParaProntuario inserir) {
//        float espacamento = 555f;
//        var crm = String.format("%s/%s %s",  inserir.getOrgaoRegulador(),
//                        inserir.getUf(),
//                        inserir.getNumeroDoRegistro());
//        var rqe = String.format("RQE: %s", inserir.getRqes().replace(" ", " - "));
//
//        var assinatura = new PdfPTable(1);
//
//        assinatura.setWidthPercentage(45f);
//        assinatura.setHorizontalAlignment(Element.ALIGN_CENTER);
//        var cell = new PdfPCell();
//        cell.setBorder(Rectangle.NO_BORDER);
//        cell.setBorder(Rectangle.TOP);
//        cell.setBorderColorTop(Color.BLACK);
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        cell.setPhrase(new Phrase(inserir.getMedico(), new Font(Font.TIMES_ROMAN, 10)));
//        assinatura.addCell(cell);
//        cell = new PdfPCell();
//        cell.setBorder(Rectangle.NO_BORDER);
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        cell.setPhrase(new Phrase(crm, new Font(Font.TIMES_ROMAN, 8)));
//        assinatura.addCell(cell);
//        if(StringUtils.isNotBlank(inserir.getRqes().trim())) {
//            cell = new PdfPCell();
//            cell.setBorder(Rectangle.NO_BORDER);
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell.setPhrase(new Phrase(rqe, new Font(Font.TIMES_ROMAN, 8)));
//            assinatura.addCell(cell);
//            espacamento = 543f;
//        }
//        assinatura.setSpacingBefore(espacamento);
//        return assinatura;
//    }



    private PdfPTable montarFormularioDoPaciente(LaudoColposcopio inserir) throws DocumentException {
        var formulario = new PdfPTable(2);
        formulario.setWidthPercentage(100);
        formulario.setSpacingAfter(7f);

        var larguraDasColunas = Documento.LARGURA_DAS_COLUNAS;
        formulario.setWidths(larguraDasColunas);

        adicionarCelulas(formulario, Documento.FIELD_PACIENTE, Formatar.string(inserir.getPaciente()), true);
        adicionarCelulas(formulario, Documento.FIELD_NUMERO_DE_ATENDIMENTO, inserir.getNumeroAtendimento(), false);

        adicionarCelulas(formulario, Documento.FIELD_IDADE, String.valueOf(inserir.getIdade()) + " Anos", true);
        adicionarCelulas(formulario, Documento.FIELD_ULTIMA_MESTRUACAO, Formatar.date(inserir.getUltimaMestruacao()), false);

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

        var entrada = new PdfPCell(new Phrase(valor, normal));
        entrada.setBorder(Rectangle.NO_BORDER);
        entrada.setPadding(0);
        entrada.setHorizontalAlignment(alinhamento);

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
}
