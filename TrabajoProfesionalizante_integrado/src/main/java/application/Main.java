package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage)
            throws Exception {

        Scene scene =
                new Scene(
                        FXMLLoader.load(
                                getClass()
                                .getResource(
                                        "/view/seleccion-view.fxml")));
        
        scene.getStylesheets().add(getClass().getResource("/css/estilo.css").toExternalForm());


        stage.setTitle(
                "Generador de Certificados");

        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }
}