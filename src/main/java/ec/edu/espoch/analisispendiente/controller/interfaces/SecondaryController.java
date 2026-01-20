package ec.edu.espoch.analisispendiente.controller.interfaces;

import ec.edu.espoch.analisispendiente.controller.usecase.ControladorFuncion;
import ec.edu.espoch.analisispendiente.modelo.objetos.Funcion;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class SecondaryController
{
    @FXML
    private TextArea txtResultado;

    @FXML
    private PasswordField txtClave;

    private final ControladorFuncion controlador = new ControladorFuncion();
    private Funcion funcion;

    public void recibirFuncion(Funcion funcion)
    {
        if (funcion == null){
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Advertencia");
            alerta.setHeaderText(null);
            alerta.setContentText("Primero es necesario ingresar los valores en los campos y presionar 'Analizar' para continuar.");
            alerta.showAndWait();
            return;
        }

        this.funcion = funcion;
        txtResultado.setText(controlador.obtenerCalculo(funcion));
    }

    @FXML
    private void limpiar()
    {
        controlador.limpiarResultado(txtClave, txtResultado);
    }

    @FXML
    private void volver()
    {
        Stage stage = (Stage) txtResultado.getScene().getWindow();
        stage.close();
    }
}
