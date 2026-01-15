package ec.edu.espoch.analisispendiente.modelo.implementation;

import ec.edu.espoch.analisispendiente.modelo.interfaces.IFuncion;
import ec.edu.espoch.analisispendiente.modelo.objetos.Funcion;

public class FuncionImpl implements IFuncion
{
    @Override
    public String analizar(Funcion funcion){
        // Llama al analisis que ya esta en Funcion
        return funcion.analizarFuncion();
    }

    @Override
    public String calcular(Funcion funcion){
        // Llama al calculo y derivadas que ya están en funcion
        return funcion.generarCalculo();
    }
}
