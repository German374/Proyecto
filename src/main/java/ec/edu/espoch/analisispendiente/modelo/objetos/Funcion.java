package ec.edu.espoch.analisispendiente.modelo.objetos;

public class Funcion
{
    private Double a;
    private Double b;
    private Double c;
    private String tipoFuncion;

    // Antes estaba private, pero ahora debe ser public porque la creación la hará FuncionImpl
    public Funcion(String tipoFuncion, double a, double b, double c)
    {
        this.tipoFuncion = tipoFuncion;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Double getA()
    {
        return a;
    }

    public Double getB()
    {
        return b;
    }

    public Double getC()
    {
        return c;
    }

    public String getTipoFuncion()
    {
        return tipoFuncion;
    }
}



