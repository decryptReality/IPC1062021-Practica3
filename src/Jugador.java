public class Jugador 
{  
    private String nombre;
    private int ganadas;
    private int perdidas;

    public Jugador(String nombre) 
    {
        this.nombre = nombre;
        ganadas = 0;
        perdidas = 0;
    }

    void gano()
    {
        ganadas = ganadas + 1;
    }
    
    void perdio()
    {
        perdidas = perdidas + 1;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getGanadas() {
        return ganadas;
    }
    public void setGanadas(int ganadas) {
        this.ganadas = ganadas;
    }
    public int getPerdidas() {
        return perdidas;
    }
    public void setPerdidas(int perdidas) {
        this.perdidas = perdidas;
    }
}
