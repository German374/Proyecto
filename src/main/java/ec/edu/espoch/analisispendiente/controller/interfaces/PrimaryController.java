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

public class PrimaryController
{
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
    public void initialize()
    {
        cbTipoFuncion.getItems().addAll("Función Lineal", "Función Cuadrática");

        // al iniciar no se permitir ingresar hasta seleccionar
        deshabilitarCampos();

        // Cuando el usuario seleccione una opción se habilita lo necesario
        cbTipoFuncion.setOnAction(event -> configurarCamposSegunTipo());
    }

    private void deshabilitarCampos(){
        txtA.setDisable(true);
        txtB.setDisable(true);
        txtC.setDisable(true);
    }

    private void configurarCamposSegunTipo(){
        String tipo = cbTipoFuncion.getValue();

        if (tipo == null){
            deshabilitarCampos();
            return;
        }

        // Para ambos tipos, a y b se usan
        txtA.setDisable(false);
        txtB.setDisable(false);

        // Para Lineal: solo a y b (c deshabilitado)
        if (tipo.equals("Función Lineal"))
        {
            txtC.clear();
            txtC.setDisable(true);
        }
        else
        {
            // Para Cuadrática: a, b y c habilitados
            txtC.setDisable(false);
        }
    }

    @FXML
    private void analizar(){
        String tipo = cbTipoFuncion.getValue();

        double a = Double.parseDouble(txtA.getText());
        double b = Double.parseDouble(txtB.getText());

        double c;
        if (tipo != null && tipo.equals("Función Lineal")){
            c = 0;
        }
        else{
            c = Double.parseDouble(txtC.getText());
        }

        funcionActual = controlador.crearFuncion(tipo, a, b, c);
        txtAnalisis.setText(controlador.obtenerAnalisis(funcionActual));
    }
//cambiar a funcionImpl
    @FXML
    private void limpiar(){
        txtA.clear();
        txtB.clear();
        txtC.clear();
        txtAnalisis.clear();
        funcionActual = null;

        cbTipoFuncion.getSelectionModel().clearSelection();
        deshabilitarCampos();
    }

    @FXML
    private void irSegundaVista() throws Exception{
        if (funcionActual == null){
            analizar();
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espoch/analisispendiente/view/secondary.fxml"));

        Scene scene = new Scene(loader.load());

        SecondaryController secondaryController = loader.getController();
        secondaryController.recibirFuncion(funcionActual);

        Stage stage = new Stage();
        stage.setTitle("Resultado del cálculo");
        stage.setScene(scene);
        stage.show();
    }
}


