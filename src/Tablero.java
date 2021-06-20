import java.util.Scanner;

public class Tablero 
{
    private Celda[][] celdas;
    private int celdasPorLado;

    void ingresarMovimiento()
    {
        Scanner scanner1 = new Scanner(System.in);
        System.out.print("\nPosicion de la ficha a mover (fil,col): ");
        int filA = scanner1.nextInt();
        int colA = scanner1.nextInt();
        System.out.print("\nPosicion destino (fil,col): ");
        int filB = scanner1.nextInt();
        int colB = scanner1.nextInt();
        System.out.println("\n[?] (" + filA + "," + colA + ") > (" + filB + "," + colB + ")");
        moverFicha(filA, colA, filB, colB);
        //scanner1.close();
    }

    void moverFicha(int filA, int colA, int filB, int colB) 
    {
        // el tablero maneja numeros en [1,8], Celda[][] celdas en [0,7]
        filA = filA - 1;
        colA = colA - 1;
        filB = filB - 1;
        colB = colB - 1;

        // para moverse: celdaA SI tener ficha, y celdaB NO tener ficha, esto se verifica
        Celda fichaA = celdas[filA][colA];
        Celda celdaB = celdas[filB][colB];
        System.out.println("[?] Ficha sin mover");
        System.out.println("fichaA: " + fichaA.getInformacion());
        System.out.println("celdaB: " + celdaB.getInformacion());

        // si celdaA tiene ficha y celaB NO tiene ficha
        if (fichaA.getTieneFicha() & !celdaB.getTieneFicha()) 
        {
            // diferencias entre posiciones A y B, filas y columnas
            int difFil = filB - filA;
            int difCol = colB - colA;
            System.out.println("difFil: " + difFil);
            System.out.println("difCol: " + difCol);

            // si diferencias distan 2 unidades y hay ficha oponente en medio
            if ((difFil == 2 | difFil == -2) & (difCol == 2 | difCol == -2)) 
            {
                int filM = difFil == 2 ? filA + 1 : filA - 1;
                int colM = difCol == 2 ? colA + 1 : colA - 1;

                // celda entre celdaA y celdaB, celdaM
                Celda celdaM = celdas[filM][colM];
                System.out.println("celdaM: " + celdaM.getInformacion());

                // si celdaM tiene ficha, fichaM, y coloM != colorA, quitarla
                if (celdaM.getTieneFicha() & (celdaM.getEsFichaColor1() != fichaA.getEsFichaColor1())) 
                {
                    celdaM.setSinFicha();
                }
            }

            // aqui se mueve la fichaA a celdaB, quitando fichaA de celdaA
            //Detalles.limpiarPantalla();
            // establecemos celdaB con ficha de color fichaA 
            celdaB.setFichaColor1(fichaA.getEsFichaColor1());
            // [!] setSinFicha() cambia de color a la ficha, es indeseado, 
            // pero asi se elaboro, en otras palabras, cuidado con el orden de su uso
            fichaA.setSinFicha(); 
            System.out.println("[?] Ficha movida");
            System.out.println("fichaA: " + fichaA.getInformacion());
            System.out.println("celdaB: " + celdaB.getInformacion());    
            System.out.println();
            imprimirTablero();
        }
        else
        {
            System.out.println("[!] No puede mover en alguna posicion");
        }
    }

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
