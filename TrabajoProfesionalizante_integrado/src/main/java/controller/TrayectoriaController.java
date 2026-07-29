package controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Certificado;
import model.TipoCertificado;
import service.ExcelService;
import service.PdfService;

public class TrayectoriaController {

    @FXML private Label lblTipo;
    @FXML private Label lblMensaje;

    @FXML private TextField txtNombre;
    @FXML private TextField txtDni;
    @FXML private TextField txtProvinciaNacimiento;
    @FXML private DatePicker dpFechaNacimiento;

    @FXML private TextField txtCapacitacionCursada;
    @FXML private TextField txtCfpnumero;
    @FXML private TextField txtCantidadHoras;
    @FXML private TextField txtTrayectoFormativo;
    @FXML private DatePicker dpFechaEgreso;
    @FXML private TextField txtCiudadEgreso;

    @FXML private TextField txtIdComponente1;
    @FXML private TextField txtCapacitacion1;
    @FXML private TextField txtHorasReloj1;
    @FXML private TextField numeroCarton1;

    @FXML private TextField txtIdComponente2;
    @FXML private TextField txtCapacitacion2;
    @FXML private TextField txtHorasReloj2;
    @FXML private TextField numeroCarton2;

    @FXML private TextField cargaHorariaAcumulada;

    @FXML private TextField txtSerie;
    @FXML private TextField txtNumero;
    @FXML private TextField txtNumero2;
    @FXML private TextField txtNumero3;

    @FXML private TextField txtDiaCertificado;
    @FXML private TextField txtMesCertificado;
    @FXML private TextField txtAnioCertificado;

    private TipoCertificado tipoSeleccionado;

    public void setTipoCertificado(TipoCertificado tipo) {
        this.tipoSeleccionado = tipo;

        if (lblTipo != null) {
            lblTipo.setText("Certificado: " + tipo.name());
        }
    }

    @FXML
    private void generarPDF() {
        try {
            if (tipoSeleccionado == null) {
                lblMensaje.setText("Seleccioná un tipo de certificado");
                return;
            }

            Certificado certificado = new Certificado();

            certificado.setNombre(texto(txtNombre));
            certificado.setDni(texto(txtDni));
            certificado.setProvinciaNacimiento(texto(txtProvinciaNacimiento));
            certificado.setFechaNacimiento(fecha(dpFechaNacimiento));

            certificado.setCapacitacionCursada(texto(txtCapacitacionCursada));
            certificado.setCfpnumero(texto(txtCfpnumero));
            certificado.setCantidadHoras(texto(txtCantidadHoras));
            certificado.setTrayectoFormativo(texto(txtTrayectoFormativo));
            certificado.setFechaEgreso(fecha(dpFechaEgreso));
            certificado.setCiudadEgreso(texto(txtCiudadEgreso));

            certificado.setIdComponente1(texto(txtIdComponente1));
            certificado.setCapacitacion1(texto(txtCapacitacion1));
            certificado.setHorasReloj1(texto(txtHorasReloj1));
            certificado.setNumeroCarton1(texto(numeroCarton1));

            certificado.setIdComponente2(texto(txtIdComponente2));
            certificado.setCapacitacion2(texto(txtCapacitacion2));
            certificado.setHorasReloj2(texto(txtHorasReloj2));
            certificado.setNumeroCarton2(texto(numeroCarton2));

            certificado.setCargaHorariaAcumulada(texto(cargaHorariaAcumulada));

            certificado.setSerie(texto(txtSerie));
            certificado.setNumero(texto(txtNumero));
            certificado.setNumero2(texto(txtNumero2));
            certificado.setNumero3(texto(txtNumero3));

            certificado.setDiaCertificado(texto(txtDiaCertificado));
            certificado.setMesCertificado(texto(txtMesCertificado));
            certificado.setAnioCertificado(texto(txtAnioCertificado));

            PdfService service = new PdfService();
            service.generar(certificado, tipoSeleccionado);

            lblMensaje.setText("PDF generado correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            lblMensaje.setText(e.getMessage());
        }
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
            List<Certificado> list = excel.leerExcel(file, tipoSeleccionado);
            if (list == null || list.isEmpty()) {
                lblMensaje.setText("No se encontraron registros en el archivo.");
                return;
            }

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

        txtNombre.setText(safe(c.getNombre()));
        txtDni.setText(safe(c.getDni()));
        txtProvinciaNacimiento.setText(safe(c.getProvinciaNacimiento()));
        // fechaNacimiento puede venir en formato ISO o como string
        txtFechaNacimientoSet(c.getFechaNacimiento()!=null?c.getFechaNacimiento():c.getFecha_nacimiento());

        txtCapacitacionCursada.setText(safe(c.getCapacitacionCursada()));
        txtCfpnumero.setText(safe(c.getCfpnumero()!=null?c.getCfpnumero():c.getCfpNumero()));
        txtCantidadHoras.setText(safe(c.getCantidadHoras()));
        txtTrayectoFormativo.setText(safe(c.getTrayectoFormativo()));
        txtFechaEgresoSet(c.getFechaEgreso()!=null?c.getFechaEgreso():c.getFecha_egreso());
        txtCiudadEgreso.setText(safe(c.getCiudadEgreso()!=null?c.getCiudadEgreso():c.getLocalidad()));

        txtIdComponente1.setText(safe(c.getIdComponente1()));
        numeroCarton1.setText(safe(c.getNumeroCarton1()));
        txtCapacitacion1.setText(safe(c.getCapacitacion1()));
        txtHorasReloj1.setText(safe(c.getHorasReloj1()));

        txtIdComponente2.setText(safe(c.getIdComponente2()));
        numeroCarton2.setText(safe(c.getNumeroCarton2()));
        txtCapacitacion2.setText(safe(c.getCapacitacion2()));
        txtHorasReloj2.setText(safe(c.getHorasReloj2()));

        cargaHorariaAcumulada.setText(safe(c.getCargaHorariaAcumulada()));

        txtSerie.setText(safe(c.getSerie()));
        txtNumero.setText(safe(c.getNumero()));
        txtNumero2.setText(safe(c.getNumero2()));
        txtNumero3.setText(safe(c.getNumero3()));

        txtDiaCertificado.setText(safe(c.getDiaCertificado()));
        txtMesCertificado.setText(safe(c.getMesCertificado()));
        txtAnioCertificado.setText(safe(c.getAnioCertificado()));
    }

    private void txtFechaEgresoSet(String fecha) {
        if (fecha == null || fecha.isBlank()) return;
        try {
            LocalDate d = LocalDate.parse(fecha);
            dpFechaEgreso.setValue(d);
        } catch (Exception ex) {
            // ignore parse error
        }
    }

    private void txtFechaNacimientoSet(String fecha) {
        if (fecha == null || fecha.isBlank()) return;
        try {
            LocalDate d = LocalDate.parse(fecha);
            dpFechaNacimiento.setValue(d);
        } catch (Exception ex) {
            // ignore
        }
    }

    private String safe(TextField field) {
        if (field == null || field.getText() == null) return "";
        return field.getText().trim();
    }



    @FXML
    private void volverAlMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/seleccion-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/estilo.css").toExternalForm());

        Stage stage = (Stage) lblTipo.getScene().getWindow();
        stage.setScene(scene);
    }

    private String texto(TextField field) {
        if (field == null || field.getText() == null) {
            return "";
        }
        return field.getText().trim();
    }

    private String fecha(DatePicker datePicker) {
        if (datePicker == null) {
            return "";
        }

        LocalDate value = datePicker.getValue();
        return value != null ? value.toString() : "";
    }
}
