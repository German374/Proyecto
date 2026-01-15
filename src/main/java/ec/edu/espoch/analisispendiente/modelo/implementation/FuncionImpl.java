package ec.edu.espoch.analisispendiente.modelo.implementation;

import ec.edu.espoch.analisispendiente.modelo.interfaces.IFuncion;
import ec.edu.espoch.analisispendiente.modelo.objetos.Funcion;

public class FuncionImpl implements IFuncion
{
    @Override
    public String analizar(Funcion funcion)
    {
        double a = funcion.getA();
        double b = funcion.getB();
        double c = funcion.getC();

        String analisis;
        String tipoRuta = "";
        String inclinacion = "";
        String esfuerzo = "";

        if (b == 0)
        {
            analisis = "La ruta es vertical (b = 0), por lo que no se puede definir una pendiente ni realizar el análisis de inclinación.";
        }
        else
        {
            double m = -a / b;

            if (m > 0)
            {
                tipoRuta = "ascendente";
            }
            else if (m < 0)
            {
                tipoRuta = "descendente";
            }
            else
            {
                tipoRuta = "plana";
            }

            if (Math.abs(m) < 0.3)
            {
                inclinacion = "suave";
                esfuerzo = "bajo";
            }
            else if (Math.abs(m) <= 0.7)
            {
                inclinacion = "moderada";
                esfuerzo = "medio";
            }
            else
            {
                inclinacion = "empinada";
                esfuerzo = "alto";
            }

            analisis = "Con los valores ingresados (a = " + a + ", b = " + b + ", c = " + c
                    + "), se obtiene un analisis para interpretar una ruta que es de " + m
                    + ", lo que indica un trayecto " + tipoRuta
                    + " con una inclinación " + inclinacion + ". Debido a esta característica, "
                    + "el recorrido requiere un esfuerzo " + esfuerzo + " y un mayor control, "
                    + "por lo que la ruta debe ser evaluada con precaución para el ingreso.";
        }

        return analisis;
    }

    @Override
    public String calcular(Funcion funcion)
    {
        double a = funcion.getA();
        double b = funcion.getB();
        double c = funcion.getC();
        String tipoFuncion = funcion.getTipoFuncion();

        StringBuilder sb = new StringBuilder();

        sb.append("Valores ingresados: a = ").append(a)
                .append(", b = ").append(b)
                .append(", c = ").append(c).append("\n\n");

        if ("Función Lineal".equals(tipoFuncion))
        {
            double pendiente = a;

            sb.append("Función Lineal: f(x) = ").append(a).append("x + ").append(b).append("\n");
            sb.append("Primera derivada: f'(x) = ").append(pendiente).append("\n");
            sb.append("Segunda derivada: f''(x) = 0\n");
            sb.append("Pendiente indica que la función es ");

            sb.append(pendiente > 0 ? "creciente" : pendiente < 0 ? "decreciente" : "constante").append("\n");
        }
        else
        {
            double derivada1 = b;
            double derivada2 = 2 * a;
            double puntoCritico = -b / (2 * a);
            String tipoExtremo = derivada2 > 0 ? "mínimo" : "máximo";

            sb.append("Función Cuadrática: f(x) = ").append(a).append("x² + ").append(b).append("x + ").append(c).append("\n");
            sb.append("Primera derivada: f'(x) = ").append(2 * a).append("x + ").append(derivada1).append("\n");
            sb.append("Segunda derivada: f''(x) = ").append(derivada2).append("\n");
            sb.append("Punto crítico: x = ").append(puntoCritico).append("\n");
            sb.append("La función tiene un ").append(tipoExtremo).append(" en el punto crítico.\n");
            sb.append("Cuando la pendiente en x = 0, la pendiente en y es ").append(derivada1).append("\n");
        }

        return sb.toString();
    }
}

