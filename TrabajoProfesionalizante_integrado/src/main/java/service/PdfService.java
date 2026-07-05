package service;

import java.awt.Desktop;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;

import config.CoordenadasActualizacion;
import config.CoordenadasMinisteriales;
import config.CoordenadasTrayectoria;
import model.Certificado;
import model.Ministeriales;
import model.TipoCertificado;
import util.PdfWriterUtil;

public class PdfService {

    public void generar(
            Certificado certificado,
            TipoCertificado tipo)
            throws IOException {

        generar(certificado, null, tipo);
    }

    public void generar(
            Certificado certificado,
            Ministeriales ministeriales,
            TipoCertificado tipo)
            throws IOException {

        if (tipo == null) {
            throw new IllegalArgumentException("No se seleccionó un tipo de certificado.");
        }

        try (InputStream plantillaStream = getClass().getResourceAsStream(
                "/pdf/" + tipo.getArchivoPdf())) {

            if (plantillaStream == null) {
                throw new IOException("No se encontró la plantilla PDF: " + tipo.getArchivoPdf());
            }

            try (PDDocument document = PDDocument.load(plantillaStream)) {
            PDPage page = document.getPage(0);

            try (PDPageContentStream content = new PDPageContentStream(
                    document,
                    page,
                    AppendMode.APPEND,
                    true,
                    true)) {

                switch (tipo) {
                    case CAPACITACION:
                        validarCertificado(certificado);
                        escribirCapacitacion(content, certificado);
                        break;

                    case ACTUALIZACION:
                    case TRAYECTORIA:
                        validarCertificado(certificado);
                        escribirTrayectoriaActualizacion(content, certificado);
                        break;

                    case MINISTERIALES:
                        validarMinisteriales(ministeriales);
                        escribirMinisteriales(content, ministeriales);
                        break;
                }
            }

                Path archivoSalida = crearRutaSalida(tipo, certificado, ministeriales);
                document.save(archivoSalida.toString());
                abrirCarpetaSalida(archivoSalida.getParent());
            }
        }
    }

    private void escribirCapacitacion(
            PDPageContentStream content,
            Certificado certificado)
            throws IOException {

        PdfWriterUtil.escribir(content, certificado.getSerie(),
                CoordenadasActualizacion.SERIE_X,
                CoordenadasActualizacion.SERIE_Y,
                12);

        PdfWriterUtil.escribir(content, certificado.getIdentificador_1(),
                CoordenadasActualizacion.IDENTIFICADOR_1_X,
                CoordenadasActualizacion.IDENTIFICADOR_1_Y,
                12);

        PdfWriterUtil.escribir(content, certificado.getIdentificador_2(),
                CoordenadasActualizacion.IDENTIFICADOR_2_X,
                CoordenadasActualizacion.IDENTIFICADOR_2_Y,
                12);

        PdfWriterUtil.escribir(content, certificado.getIdentificador_3(),
                CoordenadasActualizacion.IDENTIFICADOR_3_X,
                CoordenadasActualizacion.IDENTIFICADOR_3_Y,
                12);

        PdfWriterUtil.escribir(content, certificado.getCfp_numero(),
                CoordenadasActualizacion.CFP_NUMERO_X,
                CoordenadasActualizacion.CFP_NUMERO_Y,
                12);

        PdfWriterUtil.escribir(content, certificado.getNombre(),
                CoordenadasActualizacion.NOMBRE_X,
                CoordenadasActualizacion.NOMBRE_Y,
                12);

        PdfWriterUtil.escribir(content, certificado.getDni(),
                CoordenadasActualizacion.DNI_X,
                CoordenadasActualizacion.DNI_Y,
                12);

        PdfWriterUtil.escribir(content, certificado.getProvincia(),
                CoordenadasActualizacion.PROVINCIA_X,
                CoordenadasActualizacion.PROVINCIA_Y,
                12);

        PdfWriterUtil.escribir(content, certificado.getFecha_nacimiento(),
                CoordenadasActualizacion.FECHA_NACIMIENTO_X,
                CoordenadasActualizacion.FECHA_NACIMIENTO_Y,
                12);

        PdfWriterUtil.escribir(content, certificado.getCurso(),
                CoordenadasActualizacion.CURSO_X,
                CoordenadasActualizacion.CURSO_Y,
                12);

        PdfWriterUtil.escribir(content, certificado.getArea(),
                CoordenadasActualizacion.AREA_X,
                CoordenadasActualizacion.AREA_Y,
                12);

        PdfWriterUtil.escribir(content, certificado.getAnexo(),
                CoordenadasActualizacion.ANEXO_X,
                CoordenadasActualizacion.ANEXO_Y,
                12);

        PdfWriterUtil.escribir(content, certificado.getDuracion_hs(),
                CoordenadasActualizacion.DURACION_HS_X,
                CoordenadasActualizacion.DURACION_HS_Y,
                12);

        PdfWriterUtil.escribir(content, certificado.getFecha_egreso(),
                CoordenadasActualizacion.FECHA_EGRESO_X,
                CoordenadasActualizacion.FECHA_EGRESO_Y,
                12);

        PdfWriterUtil.escribir(content, certificado.getLocalidad(),
                CoordenadasActualizacion.LOCALIDAD_X,
                CoordenadasActualizacion.LOCALIDAD_Y,
                12);

        PdfWriterUtil.escribir(content, certificado.getDia_emision(),
                CoordenadasActualizacion.DIA_EMISION_X,
                CoordenadasActualizacion.DIA_EMISION_Y,
                12);

        PdfWriterUtil.escribir(content, certificado.getMes_emision(),
                CoordenadasActualizacion.MES_EMISION_X,
                CoordenadasActualizacion.MES_EMISION_Y,
                12);

        PdfWriterUtil.escribir(content, certificado.getAnio_emision(),
                CoordenadasActualizacion.ANIO_EMISION_X,
                CoordenadasActualizacion.ANIO_EMISION_Y,
                12);
    }

    private void escribirTrayectoriaActualizacion(
            PDPageContentStream content,
            Certificado certificado)
            throws IOException {

        PdfWriterUtil.escribirExacto(content, certificado.getSerie(),
                CoordenadasTrayectoria.SERIE_X,
                CoordenadasTrayectoria.SERIE_Y,
                12);

        PdfWriterUtil.escribirExacto(content, certificado.getNumero(),
                CoordenadasTrayectoria.NUMERO_X,
                CoordenadasTrayectoria.NUMERO_Y,
                12);

        PdfWriterUtil.escribirExacto(content, certificado.getNumero2(),
                CoordenadasTrayectoria.NUMERO2_X,
                CoordenadasTrayectoria.NUMERO2_Y,
                12);

        PdfWriterUtil.escribirExacto(content, certificado.getNumero3(),
                CoordenadasTrayectoria.NUMERO3_X,
                CoordenadasTrayectoria.NUMERO3_Y,
                12);

        PdfWriterUtil.escribirExacto(content, certificado.getNombre(),
                CoordenadasTrayectoria.NOMBRE_X,
                CoordenadasTrayectoria.NOMBRE_Y,
                14);

        PdfWriterUtil.escribirExacto(content, certificado.getDni(),
                CoordenadasTrayectoria.DNI_X,
                CoordenadasTrayectoria.DNI_Y,
                12);

        PdfWriterUtil.escribirExacto(content, certificado.getProvinciaNacimiento(),
                CoordenadasTrayectoria.PROVINCIA_X,
                CoordenadasTrayectoria.PROVINCIA_Y,
                12);

        PdfWriterUtil.escribirExacto(content, certificado.getFechaNacimiento(),
                CoordenadasTrayectoria.FECHA_NAC_X,
                CoordenadasTrayectoria.FECHA_NAC_Y,
                12);

        PdfWriterUtil.escribirExacto(content, certificado.getCapacitacionCursada(),
                CoordenadasTrayectoria.CAPACITACION_X,
                CoordenadasTrayectoria.CAPACITACION_Y,
                12);

        PdfWriterUtil.escribirExacto(content, certificado.getCfpnumero(),
                CoordenadasTrayectoria.CFPNUMERO_X,
                CoordenadasTrayectoria.CFPNUMERO_Y,
                12);

        PdfWriterUtil.escribirExacto(content, certificado.getCantidadHoras(),
                CoordenadasTrayectoria.HORAS_X,
                CoordenadasTrayectoria.HORAS_Y,
                12);

        PdfWriterUtil.escribirExacto(content, certificado.getTrayectoFormativo(),
                CoordenadasTrayectoria.TRAYECTO_X,
                CoordenadasTrayectoria.TRAYECTO_Y,
                12);

        PdfWriterUtil.escribirExacto(content, certificado.getFechaEgreso(),
                CoordenadasTrayectoria.FECHA_EGRESO_X,
                CoordenadasTrayectoria.FECHA_EGRESO_Y,
                12);

        PdfWriterUtil.escribirExacto(content, certificado.getCiudadEgreso(),
                CoordenadasTrayectoria.CIUDAD_EGRESO_X,
                CoordenadasTrayectoria.CIUDAD_EGRESO_Y,
                12);

        PdfWriterUtil.escribirExacto(content, certificado.getDiaCertificado(),
                CoordenadasTrayectoria.DIA_X,
                CoordenadasTrayectoria.DIA_Y,
                12);

        PdfWriterUtil.escribirExacto(content, certificado.getMesCertificado(),
                CoordenadasTrayectoria.MES_X,
                CoordenadasTrayectoria.MES_Y,
                12);

        PdfWriterUtil.escribirExacto(content, certificado.getAnioCertificado(),
                CoordenadasTrayectoria.ANIO_X,
                CoordenadasTrayectoria.ANIO_Y,
                12);

        PdfWriterUtil.escribirExacto(content, certificado.getIdComponente1(),
                CoordenadasTrayectoria.ID1_X,
                CoordenadasTrayectoria.ID1_Y,
                10);

        PdfWriterUtil.escribirExacto(content, certificado.getCapacitacion1(),
                CoordenadasTrayectoria.CAPACITACION1_X,
                CoordenadasTrayectoria.CAPACITACION1_Y,
                10);

        PdfWriterUtil.escribirExacto(content, certificado.getHorasReloj1(),
                CoordenadasTrayectoria.HORAS1_X,
                CoordenadasTrayectoria.HORAS1_Y,
                10);

        PdfWriterUtil.escribirExacto(content, certificado.getNumeroCarton1(),
                CoordenadasTrayectoria.NUMERO_CARTON1_X,
                CoordenadasTrayectoria.NUMERO_CARTON1_Y,
                10);

        PdfWriterUtil.escribirExacto(content, certificado.getIdComponente2(),
                CoordenadasTrayectoria.ID2_X,
                CoordenadasTrayectoria.ID2_Y,
                10);

        PdfWriterUtil.escribirExacto(content, certificado.getCapacitacion2(),
                CoordenadasTrayectoria.CAPACITACION2_X,
                CoordenadasTrayectoria.CAPACITACION2_Y,
                10);

        PdfWriterUtil.escribirExacto(content, certificado.getHorasReloj2(),
                CoordenadasTrayectoria.HORAS2_X,
                CoordenadasTrayectoria.HORAS2_Y,
                10);

        PdfWriterUtil.escribirExacto(content, certificado.getNumeroCarton2(),
                CoordenadasTrayectoria.NUMERO_CARTON2_X,
                CoordenadasTrayectoria.NUMERO_CARTON2_Y,
                10);

        PdfWriterUtil.escribirExacto(content, certificado.getCargaHorariaAcumulada(),
                CoordenadasTrayectoria.CARGA_HORARIA_ACUMULADA_X,
                CoordenadasTrayectoria.CARGA_HORARIA_ACUMULADA_Y,
                10);
    }

    private void escribirMinisteriales(
            PDPageContentStream content,
            Ministeriales ministeriales)
            throws IOException {

        PdfWriterUtil.escribir(content, ministeriales.getSerie(),
                CoordenadasMinisteriales.SERIE_X,
                CoordenadasMinisteriales.SERIE_Y,
                14);

        PdfWriterUtil.escribir(content, ministeriales.getNumeroCertificado(),
                CoordenadasMinisteriales.NCERTIFICADO_X,
                CoordenadasMinisteriales.NCERTIFICADO_Y,
                12);

        PdfWriterUtil.escribir(content, ministeriales.getCodigoCertificado(),
                CoordenadasMinisteriales.CCODIGO_X,
                CoordenadasMinisteriales.CCODIGO_Y,
                12);

        PdfWriterUtil.escribir(content, ministeriales.getAñoCertificado(),
                CoordenadasMinisteriales.AÑOCERT_X,
                CoordenadasMinisteriales.AÑOCERT_Y,
                12);

        PdfWriterUtil.escribir(content, ministeriales.getAnexo(),
                CoordenadasMinisteriales.ANEXO_X,
                CoordenadasMinisteriales.ANEXO_Y,
                12);

        PdfWriterUtil.escribir(content, ministeriales.getNumeroCentroFormacion(),
                CoordenadasMinisteriales.NUMEROCENTRO_X,
                CoordenadasMinisteriales.NUMEROCENTRO_Y,
                14);

        PdfWriterUtil.escribir(content, ministeriales.getNombre(),
                CoordenadasMinisteriales.NOMBRE_X,
                CoordenadasMinisteriales.NOMBRE_Y,
                12);

        PdfWriterUtil.escribir(content, ministeriales.getDni(),
                CoordenadasMinisteriales.DNI_X,
                CoordenadasMinisteriales.DNI_Y,
                12);

        PdfWriterUtil.escribir(content, ministeriales.getFechaNacimiento(),
                CoordenadasMinisteriales.FNACIMIENTO_X,
                CoordenadasMinisteriales.FNACIMIENTO_Y,
                12);

        PdfWriterUtil.escribir(content, ministeriales.getProvinciaNacimiento(),
                CoordenadasMinisteriales.PNACIMIENTO_X,
                CoordenadasMinisteriales.PNACIMIENTO_Y,
                12);

        PdfWriterUtil.escribir(content, ministeriales.getCiudad(),
                CoordenadasMinisteriales.CIUDAD_X,
                CoordenadasMinisteriales.CIUDAD_Y,
                10);

        PdfWriterUtil.escribir(content, ministeriales.getDia(),
                CoordenadasMinisteriales.DIA_X,
                CoordenadasMinisteriales.DIA_Y,
                12);

        PdfWriterUtil.escribir(content, ministeriales.getMes(),
                CoordenadasMinisteriales.MES_X,
                CoordenadasMinisteriales.MES_Y,
                10);

        PdfWriterUtil.escribir(content, ministeriales.getAño(),
                CoordenadasMinisteriales.AÑO_X,
                CoordenadasMinisteriales.AÑO_Y,
                12);

        PdfWriterUtil.escribir(content, ministeriales.getArea(),
                CoordenadasMinisteriales.AREA_X,
                CoordenadasMinisteriales.AREA_Y,
                12);

        PdfWriterUtil.escribir(content, ministeriales.getTipoCertificado(),
                CoordenadasMinisteriales.TCERTIFICADO_X,
                CoordenadasMinisteriales.TCERTIFICADO_Y,
                14);

        PdfWriterUtil.escribir(content, ministeriales.getHorasReloj(),
                CoordenadasMinisteriales.HRELOJ_X,
                CoordenadasMinisteriales.HRELOJ_Y,
                12);

        PdfWriterUtil.escribir(content, ministeriales.getFechaEgreso(),
                CoordenadasMinisteriales.FEGRESO_X,
                CoordenadasMinisteriales.FEGRESO_Y,
                12);
    }

    private Path crearRutaSalida(
            TipoCertificado tipo,
            Certificado certificado,
            Ministeriales ministeriales)
            throws IOException {

        Path carpetaSalida = Paths.get(
                System.getProperty("user.home"),
                "CertificadosCFP");

        Files.createDirectories(carpetaSalida);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

        String dni = obtenerDni(certificado, ministeriales);
        String nombreArchivo =
                "Certificado_" +
                tipo.name() +
                "_" +
                limpiarNombreArchivo(dni) +
                "_" +
                LocalDateTime.now().format(formatter) +
                ".pdf";

        return carpetaSalida.resolve(nombreArchivo);
    }

    private String obtenerDni(
            Certificado certificado,
            Ministeriales ministeriales) {

        if (certificado != null && certificado.getDni() != null && !certificado.getDni().isBlank()) {
            return certificado.getDni();
        }

        if (ministeriales != null && ministeriales.getDni() != null && !ministeriales.getDni().isBlank()) {
            return ministeriales.getDni();
        }

        return "sin_dni";
    }

    private String limpiarNombreArchivo(String texto) {
        if (texto == null || texto.isBlank()) {
            return "sin_dni";
        }

        return texto.trim().replaceAll("[^a-zA-Z0-9_-]", "_");
    }

    private void abrirCarpetaSalida(Path carpetaSalida) {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(carpetaSalida.toFile());
            }
        } catch (Exception e) {
            // El PDF queda generado igual aunque no se pueda abrir la carpeta automáticamente.
            e.printStackTrace();
        }
    }

    private void validarCertificado(Certificado certificado) {
        if (certificado == null) {
            throw new IllegalArgumentException("No se recibieron datos del certificado.");
        }
    }

    private void validarMinisteriales(Ministeriales ministeriales) {
        if (ministeriales == null) {
            throw new IllegalArgumentException("No se recibieron datos ministeriales.");
        }
    }
}
