package ec.edu.espoch.analisispendiente.modelo.objetos;

public class Funcion
{
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
// cambiar a funcionImpl
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
}


