package controller;


import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Certificado;
import model.Ministeriales;
import model.TipoCertificado;
import service.PdfService;

public class MinisterialesController {
	
	@FXML
	private Label lblTipo;

	@FXML
    private TextField txtSerie;
	@FXML
    private TextField txtNumeroCertificado;
	@FXML
    private TextField txtCodigoCertificado;
	@FXML
    private TextField txtAñoCertificado;
	@FXML
    private TextField txtAnexo;
	@FXML
	private TextField txtNumeroCentroFormacion;

	@FXML
    private TextField txtNombre;
    @FXML
    private TextField txtDni;
    @FXML
    private TextField txtFechaNacimiento;
    @FXML
    private TextField txtProvinciaNacimiento;

    @FXML
    private TextField txtCiudad;
    @FXML
    private TextField txtDia;
    @FXML
    private TextField txtMes;
    @FXML
    private TextField txtAño;
    @FXML
    private TextField txtArea;
    @FXML
    private TextField txtTipoCertificado;
    @FXML
    private TextField txtHorasReloj;
    @FXML
    private TextField txtFechaEgreso;
    @FXML
    private Label lblMensaje;
    

    private PdfService pdfService =
            new PdfService();
    private TipoCertificado tipo;

    public void setTipoCertificado(
            TipoCertificado tipo) {

        this.tipo = tipo;

        lblTipo.setText(
                "Certificado: " + tipo.name());
    }

    @FXML
    private void generarCertificado() {

        try {

            Ministeriales c =
                    new Ministeriales();

            c.setSerie(
            		txtSerie.getText());

            c.setNumeroCertificado(
            		txtNumeroCertificado.getText());

            c.setCodigoCertificado(
            		txtCodigoCertificado.getText());

            c.setAñoCertificado(
            		txtAñoCertificado.getText());
            c.setAnexo(
            		txtAnexo.getText());

            c.setNombre(
        		   txtNombre.getText());

            c.setDni(
                    txtDni.getText());

            c.setFechaNacimiento(
            		txtFechaNacimiento.getText());

            c.setProvinciaNacimiento(
            		txtProvinciaNacimiento.getText());

            c.setCiudad(
            		txtCiudad.getText());

            c.setDia(
            		txtDia.getText());

            c.setMes(
            		txtMes.getText());

            c.setAño(
            		txtAño.getText());

            c.setNumeroCentroFormacion(
                    txtNumeroCentroFormacion.getText());

            c.setArea(
            		txtArea.getText());

            c.setTipoCertificado(
            		txtTipoCertificado.getText());

            c.setHorasReloj(
            		txtHorasReloj.getText());

            c.setFechaEgreso(
            		txtFechaEgreso.getText());

            pdfService.generar(null,c, tipo);

            lblMensaje.setText(
                    "PDF generado");

        } catch (Exception e) {

            lblMensaje.setText(
                    e.getMessage());
        }
    }
    @FXML
    private void volver() throws IOException {
        Scene scene = new Scene(
            FXMLLoader.load(
                getClass().getResource("/view/seleccion-view.fxml")));
        scene.getStylesheets().add(
                getClass().getResource("/css/estilo.css").toExternalForm()
        );
        Stage stage = (Stage) txtSerie.getScene().getWindow();
        stage.setScene(scene);
    }
}