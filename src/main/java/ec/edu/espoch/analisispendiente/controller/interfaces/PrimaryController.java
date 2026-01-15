package ec.edu.espoch.analisispendiente.controller.interfaces;

import ec.edu.espoch.analisispendiente.controller.usecase.ControladorFuncion;
import ec.edu.espoch.analisispendiente.modelo.objetos.Funcion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PrimaryController{
    @FXML
    private ComboBox<String> cbTipoFuncion;

    @FXML
    private TextField txtA;

    @FXML
    private TextField txtB;

    @FXML
    private TextField txtC;

    @FXML
    private TextArea txtAnalisis;

    private final ControladorFuncion controlador = new ControladorFuncion();
    private Funcion funcionActual;

    @FXML
    public void initialize(){
        cbTipoFuncion.getItems().addAll("Función Lineal", "Función Cuadrática");
        cbTipoFuncion.setValue("Función Lineal");
    }

    @FXML
    private void analizar(){
        String tipo = cbTipoFuncion.getValue();

        double a = Double.parseDouble(txtA.getText());
        double b = Double.parseDouble(txtB.getText());
        double c = Double.parseDouble(txtC.getText());

        funcionActual = controlador.crearFuncion(tipo, a, b, c);
        txtAnalisis.setText(controlador.obtenerAnalisis(funcionActual));
    }

    @FXML
    private void irSegundaVista() throws Exception{
        if (funcionActual == null)
        {
            analizar();
        }

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/ec/edu/espoch/analisispendiente/view/secondary.fxml")
        );

        Scene scene = new Scene(loader.load());

        SecondaryController secondaryController = loader.getController();
        secondaryController.recibirFuncion(funcionActual);

        Stage stage = new Stage();
        stage.setTitle("Resultado del cálculo");
        stage.setScene(scene);
        stage.show();
    }
}

