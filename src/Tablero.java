import java.util.Scanner;

public class Tablero 
{
    private Celda[][] celdas;
    private int celdasPorLado;

    public Tablero(int celdasPorLado) 
    {
        this.celdasPorLado = celdasPorLado;
        celdas = new Celda[celdasPorLado][celdasPorLado];

        for (int i = 0; i < celdasPorLado; i = i + 1) 
        {
            boolean iniciaColorA = i % 2 == 0 ? true : false;

            for (int j = 0; j < celdasPorLado; j = j + 1)
            {
                boolean esColorA = iniciaColorA ? j % 2 == 0 : j % 2 == 1;

                celdas[i][j] = new Celda(esColorA);
            }
        }
    }
}