package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Certificado;
import model.TipoCertificado;
import service.PdfService;

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