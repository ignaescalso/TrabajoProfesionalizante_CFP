package service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import model.Certificado;
import model.TipoCertificado;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelService {

	    public List<Certificado> leerExcel(
	            File archivo,
	            TipoCertificado tipo) throws Exception {

	        switch (tipo) {

	            case CAPACITACION:
	                return leerCapacitacion(archivo);

	            case ACTUALIZACION:
	                return leerActualizacion(archivo);

	            case MINISTERIALES:
	                return leerMinisteriales(archivo);

	            case TRAYECTORIA:
	                return leerTrayectoria(archivo);

	            default:
	                throw new IllegalArgumentException("Tipo de certificado no soportado.");
	        }
	    }

	    // =====================================================
	    // CAPACITACION
	    // =====================================================

	    private List<Certificado> leerCapacitacion(File archivo) throws Exception {

	        List<Certificado> certificados = new ArrayList<>();

	        FileInputStream fis = new FileInputStream(archivo);
	        XSSFWorkbook libro = new XSSFWorkbook(fis);

	        Sheet hoja = libro.getSheetAt(0);

	        for (int i = 1; i <= hoja.getLastRowNum(); i++) {

	            Row fila = hoja.getRow(i);

	            if (fila == null) {
	                continue;
	            }

	            Certificado certificado = new Certificado();

	            certificado.setSerie(obtenerTexto(fila.getCell(0)));
	            certificado.setIdentificador_1(obtenerTexto(fila.getCell(1)));
	            certificado.setIdentificador_2(obtenerTexto(fila.getCell(2)));
	            certificado.setIdentificador_3(obtenerTexto(fila.getCell(3)));
	            certificado.setCfp_numero(obtenerTexto(fila.getCell(4)));
	            certificado.setNombre(obtenerTexto(fila.getCell(5)));
	            certificado.setDni(obtenerTexto(fila.getCell(6)));
	            certificado.setProvincia(obtenerTexto(fila.getCell(7)));
	            certificado.setFecha_nacimiento(obtenerTexto(fila.getCell(8)));
	            certificado.setCurso(obtenerTexto(fila.getCell(9)));
	            certificado.setArea(obtenerTexto(fila.getCell(10)));
	            certificado.setAnexo(obtenerTexto(fila.getCell(11)));
	            certificado.setDuracion_hs(obtenerTexto(fila.getCell(12)));
	            certificado.setFecha_egreso(obtenerTexto(fila.getCell(13)));
	            certificado.setLocalidad(obtenerTexto(fila.getCell(14)));
	            certificado.setDia_emision(obtenerTexto(fila.getCell(15)));
	            certificado.setMes_emision(obtenerTexto(fila.getCell(16)));
	            certificado.setAnio_emision(obtenerTexto(fila.getCell(17)));

	            certificados.add(certificado);
	        }

	        libro.close();
	        fis.close();

	        return certificados;
	    }

	    // =====================================================
	    // ACTUALIZACION
	    // =====================================================

	    private List<Certificado> leerActualizacion(File archivo) throws Exception {

	        List<Certificado> certificados = new ArrayList<>();

	        // Implementar cuando se conozcan
	        // las columnas del Excel.

	        return certificados;
	    }

	    // =====================================================
	    // MINISTERIALES
	    // =====================================================

	    private List<Certificado> leerMinisteriales(File archivo) throws Exception {

	        List<Certificado> certificados = new ArrayList<>();

	        // Implementar cuando corresponda.

	        return certificados;
	    }

	    // =====================================================
	    // TRAYECTORIA
	    // =====================================================

	    private List<Certificado> leerTrayectoria(File archivo) throws Exception {

	        List<Certificado> certificados = new ArrayList<>();

	        FileInputStream fis = new FileInputStream(archivo);
	        XSSFWorkbook libro = new XSSFWorkbook(fis);

	        Sheet hoja = libro.getSheetAt(0);

	        for (int i = 1; i <= hoja.getLastRowNum(); i++) {

	            Row fila = hoja.getRow(i);

	            if (fila == null) {
	                continue;
	            }

	            Certificado certificado = new Certificado();

	            certificado.setSerie(obtenerTexto(fila.getCell(0)));
	            certificado.setNumero(obtenerTexto(fila.getCell(1)));
	            certificado.setNumero2(obtenerTexto(fila.getCell(2)));
	            certificado.setNumero3(obtenerTexto(fila.getCell(3)));
	            certificado.setCfpnumero(obtenerTexto(fila.getCell(4)));
	            certificado.setNombre(obtenerTexto(fila.getCell(5)));
	            certificado.setDni(obtenerTexto(fila.getCell(6)));
	            certificado.setProvinciaNacimiento(obtenerTexto(fila.getCell(7)));
	            certificado.setFechaNacimiento(obtenerTexto(fila.getCell(8)));
	            certificado.setCapacitacionCursada(obtenerTexto(fila.getCell(9)));
	            certificado.setTrayectoFormativo(obtenerTexto(fila.getCell(10)));
	            certificado.setCantidadHoras(obtenerTexto(fila.getCell(11)));
	            certificado.setCargaHorariaAcumulada(obtenerTexto(fila.getCell(12)));
	            certificado.setFechaEgreso(obtenerTexto(fila.getCell(13)));
	            certificado.setCiudadEgreso(obtenerTexto(fila.getCell(14)));
	            certificado.setDiaCertificado(obtenerTexto(fila.getCell(15)));
	            certificado.setMesCertificado(obtenerTexto(fila.getCell(16)));
	            certificado.setAnioCertificado(obtenerTexto(fila.getCell(17)));

	            certificados.add(certificado);
	        }

	        libro.close();
	        fis.close();

	        return certificados;
	    }

	    // =====================================================
	    // MÉTODO AUXILIAR
	    // =====================================================

	    private String obtenerTexto(Cell celda) {

	        if (celda == null) {
	            return "";
	        }

	        DataFormatter formatter = new DataFormatter();

	        return formatter.formatCellValue(celda).trim();
	    }

	}

	
