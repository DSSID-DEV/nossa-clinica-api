package com.nossaclinica_api.reports.utils;

import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import java.io.IOException;
import java.util.Map;

public class Cabecalho {

   private static final String WIDTH = "width";
   private static final String HEIGHT = "height";


   public static PdfPTable montar(float width, String pathRoot, String pathLogomarca) throws IOException {
      String urlCabecalho = montarUrlDoCabecalho(pathRoot, pathLogomarca);
      var img = Image.getInstance(urlCabecalho);
      img.setAlt("cabeçalho");
      img.setAlignment(Element.ALIGN_CENTER);
      var cabecalho = new PdfPTable(1);
      cabecalho.setWidthPercentage(width);
      var cellCabecalho = new PdfPCell();
      cellCabecalho.setBorder(Rectangle.NO_BORDER);
      cellCabecalho.addElement(img);
      cabecalho.addCell(cellCabecalho);
      return cabecalho;
   }

   public static Image montar(String pathRoot, String pathLogomarca, Map<String, Float> mapTo) throws IOException {
      String urlCabecalho = montarUrlDoCabecalho(pathRoot, pathLogomarca);
      var img = Image.getInstance(urlCabecalho);
      img.setAlt("cabeçalho");
      img.scaleToFit(mapTo.get(WIDTH), mapTo.get(HEIGHT));

      return img;
   }

   private static String montarUrlDoCabecalho(String pathRoot, String pathLogomarca) {
      return pathRoot.concat(pathLogomarca).concat(Documento.CABECALHO);
   }
}
