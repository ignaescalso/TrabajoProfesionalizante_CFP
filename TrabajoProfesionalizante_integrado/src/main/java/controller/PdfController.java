package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Certificado;
import model.TipoCertificado;
import service.ExcelService;
import service.PdfService;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

import javafx.application.Platform;

public class PdfController {
	
	@FXML
    private TextField txtSerie;
	
	@FXML
    private TextField txtIdentificador_1;
	
	@FXML
    private TextField txtIdentificador_2;
	
	@FXML
    private TextField txtIdentificador_3;
	
	@FXML
    private TextField txtCfp_numero;
	
	@FXML
    private TextField txtNombre;

    @FXML
    private TextField txtDni;
    
    @FXML
    private TextField txtProvincia;
    
    @FXML
    private TextField txtFecha_nacimiento;

    @FXML
    private TextField txtCurso;
    
    @FXML
    private TextField txtArea;
    
    @FXML
    private TextField txtAnexo;

    @FXML
    private TextField txtDuracion_hs;
    
    @FXML
    private TextField txtFecha_egreso;
    
    @FXML
    private TextField txtLocalidad;
    
    @FXML
    private TextField txtDia_emision;
    
    @FXML
    private TextField txtMes_emision;
    
    @FXML
    private TextField txtAnio_emision;

    @FXML
    private Label lblArchivo;

    @FXML
    private Label lblMensaje;
    
    @FXML
    private Label lblTipo;

    private TipoCertificado tipo;

    public void setTipoCertificado(
            TipoCertificado tipo) {

        this.tipo = tipo;

        lblTipo.setText(
        		"Certificado: " + tipo.name());
    }

    @FXML
    private void cargarDesdeExcel() {
        try {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Seleccionar archivo Excel/CSV");
            chooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("CSV files", "*.csv"),
                    new FileChooser.ExtensionFilter("Excel XLSX", "*.xlsx"),
                    new FileChooser.ExtensionFilter("Excel XLS", "*.xls")
            );
            Stage stage = (Stage) lblTipo.getScene().getWindow();
            File file = chooser.showOpenDialog(stage);
            if (file == null) {
                lblMensaje.setText("Selección cancelada.");
                return;
            }

            ExcelService excel = new ExcelService();
            List<Certificado> list = excel.leerExcel(file, tipo);
            if (list == null || list.isEmpty()) {
                lblMensaje.setText("No se encontraron registros en el archivo.");
                return;
            }

            // Tomar el primer registro y rellenar el formulario
            Certificado c = list.get(0);
            populateFieldsFromCertificado(c);
            lblMensaje.setText("Cargado desde: " + file.getName());

        } catch (Exception e) {
            lblMensaje.setText("Error leyendo archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void populateFieldsFromCertificado(Certificado c) {
        if (c == null) return;

        txtSerie.setText(safe(c.getSerie()));
        // identificadores: los getters camelCase fueron añadidos
        try { txtIdentificador_1.setText(safe(c.getIdentificador1())); } catch (Exception ex) { txtIdentificador_1.setText(safe(c.getIdentificador_1())); }
        try { txtIdentificador_2.setText(safe(c.getIdentificador2())); } catch (Exception ex) { txtIdentificador_2.setText(safe(c.getIdentificador_2())); }
        try { txtIdentificador_3.setText(safe(c.getIdentificador3())); } catch (Exception ex) { txtIdentificador_3.setText(safe(c.getIdentificador_3())); }

        // CFP número
        try { txtCfp_numero.setText(safe(c.getCfpNumero())); } catch (Exception ex) { txtCfp_numero.setText(safe(c.getCfp_numero())); }

        txtNombre.setText(safe(c.getNombre()));
        txtDni.setText(safe(c.getDni()));
        txtProvincia.setText(safe(c.getProvincia()));

        // Fecha de nacimiento: preferir camelCase
        String fn = (c.getFechaNacimiento() != null && !c.getFechaNacimiento().isBlank()) ? c.getFechaNacimiento() : c.getFecha_nacimiento();
        txtFecha_nacimiento.setText(safe(fn));

        txtCurso.setText(safe(c.getCurso()));
        txtArea.setText(safe(c.getArea()));
        txtAnexo.setText(safe(c.getAnexo()));
        txtDuracion_hs.setText(safe(c.getDuracionHs()!=null?c.getDuracionHs():c.getDuracion_hs()));
        txtFecha_egreso.setText(safe(c.getFechaEgreso()!=null?c.getFechaEgreso():c.getFecha_egreso()));

        // localidad / ciudad egreso
        txtLocalidad.setText(safe(c.getCiudadEgreso()!=null?c.getCiudadEgreso():c.getLocalidad()));

        // fecha emisión: si hay fechaEmision completa la ponemos en dia/mes/anio campos si posible, else llenar dia/mes/anio
        String fechaEm = (c.getFechaEmision()!=null && !c.getFechaEmision().isBlank()) ? c.getFechaEmision() : "";
        if (!fechaEm.isBlank()) {
            // intentar parsear formato d/m/y simple
            String[] parts = fechaEm.split("[/\\-]");
            if (parts.length >= 3) {
                txtDia_emision.setText(parts[0]);
                txtMes_emision.setText(parts[1]);
                txtAnio_emision.setText(parts[2]);
            } else {
                // si no se parsea, poner todo en localidad campo de fecha (usar txtDia_emision)
                txtDia_emision.setText(fechaEm);
            }
        } else {
            txtDia_emision.setText(safe(c.getDia_emision()));
            txtMes_emision.setText(safe(c.getMes_emision()));
            txtAnio_emision.setText(safe(c.getAnio_emision()));
        }
    }

    private String safe(String s) { return s == null ? "" : s; }

    @FXML
    private void generarExcelEjemplo() {
        try {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Guardar Excel de ejemplo");
            chooser.setInitialFileName("certificados_ejemplo.xlsx");
            Stage stage = (Stage) lblTipo.getScene().getWindow();
            File file = chooser.showSaveDialog(stage);
            if (file == null) {
                lblMensaje.setText("Guardado cancelado.");
                return;
            }
            ExcelService excel = new ExcelService();
            excel.generateSampleXlsx(file);
            lblMensaje.setText("Excel generado: " + file.getAbsolutePath());
        } catch (Exception e) {
            lblMensaje.setText("Error generando Excel: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void procesarExcel() {
        try {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Seleccionar archivo Excel/CSV para procesar");
            chooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("CSV files", "*.csv"),
                    new FileChooser.ExtensionFilter("Excel XLSX", "*.xlsx"),
                    new FileChooser.ExtensionFilter("Excel XLS", "*.xls")
            );
            Stage stage = (Stage) lblTipo.getScene().getWindow();
            File file = chooser.showOpenDialog(stage);
            if (file == null) {
                lblMensaje.setText("Selección cancelada.");
                return;
            }

            ExcelService excel = new ExcelService();
            List<Certificado> list = excel.leerExcel(file, tipo);
            if (list == null || list.isEmpty()) {
                lblMensaje.setText("No se encontraron registros en el archivo.");
                return;
            }

            lblMensaje.setText("Procesando " + list.size() + " registros...");

            // Ejecutar en hilo de fondo
            new Thread(() -> {
                PdfService pdfService = new PdfService();
                int success = 0;
                List<String> errors = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    Certificado c = list.get(i);
                    try {
                        // Generar sin abrir carpetas para no interrumpir el flujo
                        pdfService.generarSinAbrir(c, tipo);
                        success++;
                    } catch (Exception ex) {
                        errors.add("Fila " + (i+1) + ": " + ex.getMessage());
                    }
                }

                final int ok = success;
                final int total = list.size();
                final List<String> errs = errors;
                Platform.runLater(() -> {
                    String msg = "Procesados: " + ok + " / " + total;
                    if (!errs.isEmpty()) msg += ". Errores: " + errs.size();
                    lblMensaje.setText(msg);
                });
            }).start();

        } catch (Exception e) {
            lblMensaje.setText("Error leyendo archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void generarCertificado() {

        try {

            Certificado c =
                    new Certificado();
            
            c.setSerie(
                    txtSerie.getText());
            
            c.setIdentificador_1(
                    txtIdentificador_1.getText());
            
            c.setIdentificador_2(
                    txtIdentificador_2.getText());
            
            c.setIdentificador_3(
                    txtIdentificador_3.getText());
            
            c.setCfp_numero(
                    txtCfp_numero.getText());
            
            c.setNombre(
                    txtNombre.getText());

            c.setDni(
                    txtDni.getText());

            c.setProvincia(
                    txtProvincia.getText());
            
            c.setFecha_nacimiento(
                    txtFecha_nacimiento.getText());

            c.setCurso(
                    txtCurso.getText());
            
            c.setArea(
                    txtArea.getText());
            
            c.setAnexo(
                    txtAnexo.getText());

            c.setDuracion_hs(
                    txtDuracion_hs.getText());
            
            c.setFecha_egreso(
                    txtFecha_egreso.getText());
            
            c.setLocalidad(
                    txtLocalidad.getText());
            
            c.setDia_emision(
                    txtDia_emision.getText());
            
            c.setMes_emision(
                    txtMes_emision.getText());
            
            c.setAnio_emision(
                    txtAnio_emision.getText());
            
            PdfService service =
                    new PdfService();

            service.generar(
                    c,null,
                    tipo);

            lblMensaje.setText(
                    "PDF generado");

        } catch (Exception e) {

            lblMensaje.setText(
                    e.getMessage());
        }
    }
    
    @FXML
    private void volverAlMenu() throws Exception {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/view/seleccion-view.fxml"));
        Parent root = loader.load();
        
        Stage stage = (Stage) lblTipo.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}