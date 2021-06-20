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

    void imprimirTablero() 
    {
        for (int i = 0; i < celdasPorLado; i = i + 1) 
        {
            for (int j = 0; j < celdasPorLado; j = j + 1)
            {
                System.out.print(celdas[i][j].getFila1());
            }
            System.out.println();
            for (int j = 0; j < celdasPorLado; j = j + 1)
            {
                System.out.print(celdas[i][j].getFila2());
            }
            System.out.println();
            for (int j = 0; j < celdasPorLado; j = j + 1)
            {
                System.out.print(celdas[i][j].getFila1());
            }
            System.out.println();
        }
    }    
        
    void ordenarFichas()
    {
        for (int i = 0; i < 3; i = i + 1) 
        {
            for (int j = 0; j < celdasPorLado; j = j + 1)
            {
                if(!celdas[i][j].getEsCeldaColorA())
                {
                    celdas[i][j].setFichaColor1(false);
                }
            }

            for (int j = 0; j < celdasPorLado; j = j + 1)
            {
                if(!celdas[celdasPorLado - 1 - i][j].getEsCeldaColorA())
                {
                    celdas[celdasPorLado - 1 - i][j].setFichaColor1(true);
                }
            }
        }
        imprimirTablero();
    }
}
