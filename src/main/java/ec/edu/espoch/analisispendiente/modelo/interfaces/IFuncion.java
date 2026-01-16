package ec.edu.espoch.analisispendiente.modelo.interfaces;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public interface IFuncion
{
    String analizar(ec.edu.espoch.analisispendiente.modelo.objetos.Funcion funcion);
    String calcular(ec.edu.espoch.analisispendiente.modelo.objetos.Funcion funcion);

    void limpiarFormulario(TextField txtA, TextField txtB, TextField txtC, TextArea txtAnalisis);

    void limpiarResultado(PasswordField txtClave, TextArea txtResultado);
}


