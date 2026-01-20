package ec.edu.espoch.analisispendiente.modelo.implementation;

import ec.edu.espoch.analisispendiente.modelo.interfaces.IFuncion;
import ec.edu.espoch.analisispendiente.modelo.objetos.Funcion;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FuncionImpl implements IFuncion
{
    public Funcion crearFuncion(String tipoFuncion, double a, double b, double c){
        return new Funcion(tipoFuncion, a, b, c);
    }

    @Override
    public String analizar(Funcion funcion)
    {
        // Validacion defensiva: evita que se quede sin mensaje
        if (funcion == null){
            return "Primero es necesario ingresar los valores en los campos. Luego presiona 'Analizar' para continuar.";
        }

        double a = funcion.getA();
        double b = funcion.getB();
        double c = funcion.getC();

        String analisis;
        String tipoRuta = "";
        String inclinacion = "";
        String esfuerzo = "";

        if (b == 0){
            analisis = "La ruta es vertical (b = 0), por lo que no se puede definir una pendiente ni realizar el análisis de inclinación.";
        }
        else{
            double m = -a / b;

            if (m > 0){
                tipoRuta = "ascendente";
            }
            else if (m < 0){
                tipoRuta = "descendente";
            }
            else{
                tipoRuta = "plana";
            }

            if (Math.abs(m) < 0.3){
                inclinacion = "suave";
                esfuerzo = "bajo";
            }
            else if (Math.abs(m) <= 0.7){
                inclinacion = "moderada";
                esfuerzo = "medio";
            }
            else{
                inclinacion = "empinada";
                esfuerzo = "alto";
            }

            // redondeo del valor que se va a mostrar
            String mFormateado = String.format("%.2f", m);

            analisis = "Con los valores ingresados (a = " + a + ", b = " + b + ", c = " + c +
                    "), se obtiene un analisis para interpretar una ruta que es de " + mFormateado +
                    ", lo que indica un trayecto " + tipoRuta +
                    " con una inclinación " + inclinacion + ". Debido a esta característica, " +
                    "el recorrido requiere un esfuerzo " + esfuerzo +
                    " y un mayor control, por lo que la ruta debe ser evaluada con precaución.";
        }

        return analisis;
    }

    @Override
    public String calcular(Funcion funcion)
    {
        // Validacion defensiva: evita errores si no llega funcion
        if (funcion == null){
            return "Debes ingresar todos los campos para poder analizar la funcion. Completa los valores y vuelve a intentar.";
        }

        double a = funcion.getA();
        double b = funcion.getB();
        double c = funcion.getC();
        String tipoFuncion = funcion.getTipoFuncion();

        // Validacion defensiva del tipo de funcion
        if (tipoFuncion == null || tipoFuncion.trim().isEmpty()){
            return "Primero selecciona el tipo de funcion y luego ingresa los valores para poder analizar.";
        }

        StringBuilder sb = new StringBuilder();

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
            // Evita division por cero en cuadratica
            if (a == 0){
                return "Para analizar la funcion cuadratica, el valor de a no puede ser 0. Ingresa un valor diferente y vuelve a intentar.";
            }

            double derivada1 = b;
            double derivada2 = 2 * a;
            double puntoCritico = -b / (2 * a);
            String tipoExtremo = derivada2 > 0 ? "mínimo" : "máximo";

            sb.append("Función Cuadrática: f(x) = ").append(a).append("x² + ").append(b).append("x + ").append(c).append("\n");
            sb.append("Primera derivada: f'(x) = ").append(2 * a).append("x + ").append(derivada1).append("\n");
            sb.append("Segunda derivada: f''(x) = ").append(derivada2).append("\n");

            // redondeo del valor que se va a mostrar
            String puntoCriticoFormateado = String.format("%.2f", puntoCritico);
            sb.append("Punto crítico: x = ").append(puntoCriticoFormateado).append("\n");
            sb.append("La función tiene un ").append(tipoExtremo).append(" en el punto crítico.\n");
            sb.append("Cuando la pendiente en x = 0, la pendiente en y es ").append(derivada1).append("\n");
        }

        return sb.toString();
    }

    // Limpira primera vista
    @Override
    public void limpiarFormulario(TextField txtA, TextField txtB, TextField txtC, TextArea txtAnalisis)
    {
        txtA.clear();
        txtB.clear();
        txtC.clear();
        txtAnalisis.clear();
    }

    // Limpiar resultado con contraseña
    @Override
    public void limpiarResultado(PasswordField txtClave, TextArea txtResultado)
    {
        String clave = txtClave.getText().trim();

        if (clave.isEmpty()){
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Advertencia");
            alerta.setHeaderText(null);
            alerta.setContentText("Por favor ingrese la contraseña para poder realizar esta acción");
            alerta.showAndWait();
        }
        else if (clave.equals("1234")){
            txtResultado.clear();
            txtClave.clear();
        }
        else{
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText(null);
            alerta.setContentText("Contraseña incorrecta, intente de nuevo");
            alerta.showAndWait();
        }
    }
}
