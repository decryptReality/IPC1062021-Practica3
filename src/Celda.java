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
