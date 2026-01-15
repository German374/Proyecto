package ec.edu.espoch.analisispendiente.controller.usecase;

import ec.edu.espoch.analisispendiente.modelo.implementation.FuncionImpl;
import ec.edu.espoch.analisispendiente.modelo.interfaces.IFuncion;
import ec.edu.espoch.analisispendiente.modelo.objetos.Funcion;

public class ControladorFuncion
{
    private final IFuncion funcionImpl;

    public ControladorFuncion(){
        funcionImpl = new FuncionImpl();
    }

    public Funcion crearFuncion(String tipoFuncion, double a, double b, double c){
        return Funcion.crearFuncion(tipoFuncion, a, b, c);
    }

    public String obtenerAnalisis(Funcion funcion){
        return funcionImpl.analizar(funcion);
    }

    public String obtenerCalculo(Funcion funcion){
        return funcionImpl.calcular(funcion);
    }
}

