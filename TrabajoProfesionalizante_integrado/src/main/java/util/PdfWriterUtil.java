package util;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.util.Matrix;

public class PdfWriterUtil {

    /**
     * Mantiene el comportamiento del proyecto grande: usa x como centro del texto.
     */
    public static void escribir(
            PDPageContentStream content,
            String texto,
            float x,
            float y,
            int tamanio)
            throws IOException {

        if (texto == null) {
            texto = "";
        }

        content.beginText();
        content.setFont(PDType1Font.HELVETICA, tamanio);

        float anchoTexto =
                PDType1Font.HELVETICA
                        .getStringWidth(texto)
                / 1000 * tamanio;

        float posicionX = x - (anchoTexto / 2);

        content.setTextMatrix(Matrix.getTranslateInstance(posicionX, y));
        content.showText(texto);
        content.endText();
    }

    /**
     * Escribe usando x como posición izquierda exacta.
     * Se agrega para las coordenadas tomadas de ModificadorModeloV1.
     */
    public static void escribirExacto(
            PDPageContentStream content,
            String texto,
            float x,
            float y,
            int tamanio)
            throws IOException {

        if (texto == null) {
            texto = "";
        }

        content.beginText();
        content.setFont(PDType1Font.HELVETICA, tamanio);
        content.newLineAtOffset(x, y);
        content.showText(texto);
        content.endText();
    }

    public static void escribirCentrado(
            PDPageContentStream content,
            String texto,
            float centroX,
            float y,
            int tamanio)
            throws IOException {

        escribir(content, texto, centroX, y, tamanio);
    }
}
