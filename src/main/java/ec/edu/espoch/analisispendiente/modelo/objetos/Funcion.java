package ec.edu.espoch.analisispendiente.modelo.objetos;

public class Funcion {
    private Double a;
    private Double b;
    private Double c;
    private String tipoFuncion;

    private Funcion(String tipoFuncion, double a, double b, double c){
        this.tipoFuncion = tipoFuncion;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public static Funcion crearFuncion(String tipoFuncion, double a, double b, double c){
        return new Funcion(tipoFuncion, a, b, c);
    }

    public Double getA(){
        return a;
    }

    public Double getB(){
        return b;
    }

    public Double getC(){
        return c;
    }

    public String getTipoFuncion(){
        return tipoFuncion;
    }

    public String analizarFuncion(){
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

            analisis = "Con los valores ingresados (a = " + a + ", b = " + b + ", c = " + c
                    + "), se obtiene un analisis para interpretar una ruta que es de " + m
                    + ", lo que indica un trayecto " + tipoRuta
                    + " con una inclinación " + inclinacion + ". Debido a esta característica, "
                    + "el recorrido requiere un esfuerzo " + esfuerzo + " y un mayor control, "
                    + "por lo que la ruta debe ser evaluada con precaución para el ingreso.";
        }

        return analisis;
    }

    public String calculoDetallado(){
        double m = (b != 0) ? -a / b : 0;

        String tipoRuta;
        if (b == 0){
            tipoRuta = "vertical";
        }
        else if (m > 0){
            tipoRuta = "ascendente";
        }
        else if (m < 0){
            tipoRuta = "descendente";
        }
        else{
            tipoRuta = "plana";
        }

        String inclinacion;
        String esfuerzo;

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

        return "Tipo de función: " + tipoFuncion + "\n"
                + "Valor de a: " + a + "\n"
                + "Valor de b: " + b + "\n"
                + "Valor de c: " + c + "\n"
                + "Pendiente m: " + String.format("%.2f", m) + "\n"
                + "Tipo de ruta: " + tipoRuta + "\n"
                + "Inclinación: " + inclinacion + "\n"
                + "Esfuerzo requerido: " + esfuerzo;
    }

    public String generarCalculo(){
        StringBuilder sb = new StringBuilder();

        sb.append("Valores ingresados: a = ").append(a)
                .append(", b = ").append(b)
                .append(", c = ").append(c).append("\n\n");

        if ("Función Lineal".equals(tipoFuncion)){
            double pendiente = a;

            sb.append("Función Lineal: f(x) = ").append(a).append("x + ").append(b).append("\n");
            sb.append("Primera derivada: f'(x) = ").append(pendiente).append("\n");
            sb.append("Segunda derivada: f''(x) = 0\n");
            sb.append("Pendiente indica que la función es ");

            sb.append(pendiente > 0 ? "creciente" : pendiente < 0 ? "decreciente" : "constante").append("\n");
        }
        else{
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

