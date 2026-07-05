package controller;

import java.io.IOException;
import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Certificado;
import model.TipoCertificado;
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
