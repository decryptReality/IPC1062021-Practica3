public class Celda 
{
    // COLORES PARA CELDA
    private static String colorA = "\033[0;107m";
    private static String colorB = "\033[0;100m";
    // COLORES PARA FICHA
    private static String color1 = "\033[0;106m";
    private static String color2 = "\033[0;101m";
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
        colorCelda = this.esCeldaColorA ? colorA : colorB;
        setFilas();
    }

    void setSinFicha()
    {
        tieneFicha = false;
        setFilas();
    }

    void setFichaColor1(boolean esFichaColor1)
    {
        tieneFicha = true;
        this.esFichaColor1 = esFichaColor1;
        setFilas();
    }

    private void setFilas()
    {
        if(tieneFicha)
        {
            if(esFichaColor1)
            {
                colorFicha = color1;
            }
            else
            {
                colorFicha = color2;
            }
            fila1 = colorCelda + "  " + colorFicha + "  " + colorCelda + "  " + colorReset;
            fila2 = colorCelda + " " + colorFicha + "    " + colorCelda + " " + colorReset;
        }
        else
        {
            fila1 = colorCelda + "      " + colorReset;
            fila2 = fila1;
        }
    }

    public String getInformacion()
    {
        return "tieneFicha: " + tieneFicha + ", esFichaColor1: " + esFichaColor1 + ", esCeldaColorA: " + esCeldaColorA;
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

    public boolean getEsCeldaColorA() {
        return esCeldaColorA;
    }

    public boolean getEsFichaColor1() {
        return esFichaColor1;
    }
}
