package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.TipoCertificado;

public class SeleccionController {

    @FXML
    private ComboBox<TipoCertificado> cmbTipo;

    @FXML
    public void initialize() {
        cmbTipo.getItems().addAll(TipoCertificado.values());
    }

    @FXML
    private void continuar() throws IOException {
        TipoCertificado tipo = cmbTipo.getValue();

        if (tipo == null) {
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource(vistaPara(tipo)));
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(getClass().getResource("/css/estilo.css").toExternalForm());

        Object controller = loader.getController();

        if (controller instanceof MinisterialesController) {
            ((MinisterialesController) controller).setTipoCertificado(tipo);
        } else if (controller instanceof TrayectoriaController) {
            ((TrayectoriaController) controller).setTipoCertificado(tipo);
        } else if (controller instanceof PdfController) {
            ((PdfController) controller).setTipoCertificado(tipo);
        }

        Stage stage = (Stage) cmbTipo.getScene().getWindow();
        stage.setScene(scene);
    }

    private String vistaPara(TipoCertificado tipo) {
        switch (tipo) {
            case MINISTERIALES:
                return "/view/ministeriales-view.fxml";

            case ACTUALIZACION:
            case TRAYECTORIA:
                return "/view/trayectoria-view.fxml";

            case CAPACITACION:
            default:
                return "/view/pdf-view.fxml";
        }
    }
}
