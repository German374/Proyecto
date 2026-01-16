package ec.edu.espoch.analisispendiente.controller.usecase;

import ec.edu.espoch.analisispendiente.modelo.implementation.FuncionImpl;
import ec.edu.espoch.analisispendiente.modelo.interfaces.IFuncion;
import ec.edu.espoch.analisispendiente.modelo.objetos.Funcion;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControladorFuncion
{
    private final FuncionImpl funcionImpl;   // Para crear el objeto
    private final IFuncion funcion;          // Para analizar, calcular y limpiar

    public ControladorFuncion(){
        funcionImpl = new FuncionImpl();
        funcion = funcionImpl;
    }

    public Funcion crearFuncion(String tipoFuncion, double a, double b, double c){
        return funcionImpl.crearFuncion(tipoFuncion, a, b, c);
    }

    public String obtenerAnalisis(Funcion funcionObj){
        return funcion.analizar(funcionObj);
    }

    public String obtenerCalculo(Funcion funcionObj){
        return funcion.calcular(funcionObj);
    }

    public void limpiarFormulario(TextField txtA, TextField txtB, TextField txtC, TextArea txtAnalisis){
        funcion.limpiarFormulario(txtA, txtB, txtC, txtAnalisis);
    }

    public void limpiarResultado(PasswordField txtClave, TextArea txtResultado){
        funcion.limpiarResultado(txtClave, txtResultado);
    }
}


