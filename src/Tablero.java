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

        // System.out.println("[?] Ficha sin mover");
        // System.out.println("fichaA: " + fichaA.getInformacion());
        // System.out.println("celdaB: " + celdaB.getInformacion());

        // diferencias entre posiciones A y B, filas y columnas
        int difFil = filB - filA;
        int difCol = colB - colA;
        // si movimiento es en diagonal
        boolean esDiagonal = difFil * difFil == difCol * difCol; 
        // celdaA SI tiene ficha y celaB NO tiene ficha
        boolean sonCeldasHabiles = fichaA.getTieneFicha() & !celdaB.getTieneFicha();
        // verificar si ficha se mueve recta, lo correcto es diagonal
        boolean esDifCERO = difFil == 0 | difCol == 0;

        // System.out.println("[?] Diferencias de posicion entre celdaOrigen y celdaDestino");
        // System.out.println("difFil: " + difFil);
        // System.out.println("difCol: " + difCol);
        if (esDiagonal & sonCeldasHabiles & !esDifCERO) 
        {
            // verificar si ficha se mueve una celda
            boolean esDifUNO = difFil == 1 | difFil == -1 | difCol == 1 | difCol == -1;
            // diferencias distan 2 unidad o mas para tener fichaM
            if (!esDifUNO)            
            {
                int filM = difFil > 0 ? filB - 1 : filB + 1;
                int colM = difCol > 0 ? colB - 1 : colB + 1;

                // celda entre celdaA y celdaB, celdaM
                Celda celdaM = celdas[filM][colM];

                // si celdaM tiene ficha, fichaM, y coloM != colorA, quitarla
                if (celdaM.getTieneFicha() & (celdaM.getEsFichaColor1() != fichaA.getEsFichaColor1())) 
                {
                    celdaM.setSinFicha();
                    System.out.println("[!] Ficha (" + filM + "," + colM + ") eliminada");
                    //System.out.println("celdaM: " + celdaM.getInformacion());
                }
            }

            // aqui se mueve la fichaA a celdaB, quitando fichaA de celdaA
            // establecemos celdaB con ficha de color fichaA 
            celdaB.setFichaColor1(fichaA.getEsFichaColor1());
            // [!] setSinFicha() cambia de color a la ficha, es indeseado, 
            // pero asi se elaboro, en otras palabras, cuidado con el orden de su uso
            fichaA.setSinFicha(); 
            System.out.println("[?] Ficha movida");
            // System.out.println("fichaA: " + fichaA.getInformacion());
            // System.out.println("celdaB: " + celdaB.getInformacion() + "\n");    
            imprimirTablero();
            verificarFichas();
        }
        else
        {
            System.out.println("[!] No puede mover en alguna celda");
        }
    }

    void verificarFichas()
    {
        int fichasColor1 = 0;
        int fichasColor2 = 0;
        for (int i = 0; i < celdasPorLado; i = i + 1) 
        {
            for (int j = 0; j < celdasPorLado; j = j + 1)
            {
                if(celdas[i][j].getTieneFicha())
                {
                    if(celdas[i][j].getEsFichaColor1())
                    {
                        fichasColor1 = fichasColor1 + 1;
                    }
                    else
                    {
                        fichasColor2 = fichasColor2 + 1;
                    }
                }
            }
        }
        System.out.println("fichasColor1: " + fichasColor1);
        System.out.println("fichasColor2: " + fichasColor2);
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

    public int getCeldasPorLado() {
        return celdasPorLado;
    }

        
    /*
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

            System.out.println("[?] Diferencias de posicion entre celdaOrigen y celdaDestino");
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
            System.out.println("celdaB: " + celdaB.getInformacion() + "\n");    
            imprimirTablero();
        }
        else
        {
            System.out.println("[!] No puede mover en alguna celda");
        }
    }

    void moverFicha(boolean esFichaColor1, int filA, int colA, int filB, int colB)
    {
        Detalles.limpiarPantalla();
        celdas[filA][colA].setSinFicha();
        celdas[filB][colB].setFichaColor1(esFichaColor1);
        System.out.println();
        imprimirTablero();
    }
    */
}
