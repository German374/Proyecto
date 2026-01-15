package ec.edu.espoch.analisispendiente.controller.usecase;

import ec.edu.espoch.analisispendiente.modelo.objetos.Funcion;

public class ControladorFuncion{
    public Funcion crearFuncion(String tipoFuncion, double a, double b, double c){
        return Funcion.crearFuncion(tipoFuncion, a, b, c);
    }

    public String obtenerAnalisis(Funcion funcion){
        return funcion.analizarFuncion();
    }

    public String obtenerCalculo(Funcion funcion){
        return funcion.generarCalculo();
    }
}

