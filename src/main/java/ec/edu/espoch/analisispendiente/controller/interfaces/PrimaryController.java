package ec.edu.espoch.analisispendiente.controller.interfaces;

import ec.edu.espoch.analisispendiente.controller.usecase.ControladorFuncion;
import ec.edu.espoch.analisispendiente.modelo.objetos.Funcion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    public void initialize(){
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
            // Para Cuadrática a, b y c habilitados
            txtC.setDisable(false);
        }
    }

    // mensaje agregado
    private void mostrarMensaje(String titulo, String mensaje, Alert.AlertType tipoAlerta){
        Alert alerta = new Alert(tipoAlerta);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private boolean validarCamposAntesDeAnalizar(String tipoFuncion){

        if (tipoFuncion == null || tipoFuncion.trim().isEmpty()){
            mostrarMensaje(
                "Advertencia",
                "Primero selecciona el tipo de función. Luego ingresa los valores en los campos para poder analizar.",
                Alert.AlertType.WARNING
            );
            return false;
        }

        String aTexto = txtA.getText() == null ? "" : txtA.getText().trim();
        String bTexto = txtB.getText() == null ? "" : txtB.getText().trim();
        String cTexto = txtC.getText() == null ? "" : txtC.getText().trim();

        // Campos en blanco
        if (aTexto.isEmpty() || bTexto.isEmpty()){
            mostrarMensaje(
                "Campos incompletos",
                "Debes ingresar todos los campos para poder analizar la función. Completa los valores y vuelve a intentar.",
                Alert.AlertType.WARNING
            );
            return false;
        }

        if ("Función Cuadrática".equals(tipoFuncion)){
            if (cTexto.isEmpty()){
                mostrarMensaje(
                    "Campos incompletos",
                    "Debes ingresar todos los campos (a, b y c) para poder analizar la función cuadrática. Completa los valores y vuelve a intentar.",
                    Alert.AlertType.WARNING
                );
                return false;
            }
        }

        // Solo numeros
        try{
            Double.parseDouble(aTexto);
            Double.parseDouble(bTexto);

            if ("Función Cuadrática".equals(tipoFuncion)){
                Double.parseDouble(cTexto);
            }
        }
        catch (NumberFormatException e){
            mostrarMensaje(
                "Entrada no válida",
                "Solo se aceptan números. Verifica que los campos contengan valores numéricos (por ejemplo: 2, -3, 4.5).",
                Alert.AlertType.ERROR
            );
            return false;
        }

        return true;
    }
   

    @FXML
    private void analizar(){
        String tipo = cbTipoFuncion.getValue();

        //validaciones
        if (!validarCamposAntesDeAnalizar(tipo)){
            return;
        }

        double a = Double.parseDouble(txtA.getText().trim());
        double b = Double.parseDouble(txtB.getText().trim());

        double c;
        if (tipo != null && tipo.equals("Función Lineal")){
            c = 0;
        }
        else{
            c = Double.parseDouble(txtC.getText().trim());
        }

        funcionActual = controlador.crearFuncion(tipo, a, b, c);

        if (funcionActual == null){
            mostrarMensaje(
                "Advertencia",
                "Primero es necesario ingresar los valores en los campos. Luego presiona 'Analizar' para continuar.",
                Alert.AlertType.WARNING
            );
            return;
        }

        txtAnalisis.setText(controlador.obtenerAnalisis(funcionActual));
    }

    @FXML
    private void limpiar(){
        controlador.limpiarFormulario(txtA, txtB, txtC, txtAnalisis);
        funcionActual = null;
        cbTipoFuncion.getSelectionModel().clearSelection();
        deshabilitarCampos();
    }

    @FXML
    private void irSegundaVista() throws Exception{
        if (funcionActual == null){
            analizar();

            if (funcionActual == null){
                return;
            }
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
