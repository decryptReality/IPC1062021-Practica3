public class Celda 
{
    // COLORES DE CELDA
    private static String colorA = "\033[47m";
    private static String colorB = "\033[0;106m";
    // COLORES DE FICHA
    private static String color1 = "\033[42m";
    private static String color2 = "\033[43m";
    // RESTABLECER COLOR A POR DEFECTO
    private static String colorReset = "\033[0m";

    private String colorCelda;
    private String colorFicha;

    private boolean esCeldaColorA;
    private boolean tieneFicha;
    private boolean esFichaColor1;
    private String fila1;
    private String fila2;

    public Celda(boolean esCeldaColorA) 
    {
        this.esCeldaColorA = esCeldaColorA;
        tieneFicha = false;
        esFichaColor1 = true;
        colorCelda = "";
        if(this.esCeldaColorA)
        {
            colorCelda = colorA;
        }
        else
        {
            colorCelda = colorB;
        }
        fila1 = colorCelda + "      " + colorReset;
        fila2 = fila1;
    }

    void setSinFicha()
    {
        tieneFicha = false;
        esFichaColor1 = true;
        setFilas();
    }

    void setFichaColor1(boolean esFichaColor1)
    {
        tieneFicha = true;
        this.esFichaColor1 = esFichaColor1;
        setFilas();
    }
    
    public String getFila1() {
        return fila1;
    }

    public String getFila2() {
        return fila2;
    }

    public boolean getTieneFicha() {
        return tieneFicha;
    }

    public boolean getEsFichaColor1() {
        return esFichaColor1;
    }
}
