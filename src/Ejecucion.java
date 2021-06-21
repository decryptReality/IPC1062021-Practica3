import java.util.Random;
import java.util.Scanner;

public class Ejecucion 
{
    public static void main(String[] args) {
        iniciarJuego();
    }

    private static Scanner scanner1 = new Scanner(System.in);
    private static Random random1 = new Random(); 

    private static Jugador[] jugadores = new Jugador[10];
    private static Jugador jugadorAaA;
    private static Jugador jugadorBbB;;

    private static Tablero tablero1 = new Tablero(8);

    static void iniciarJuego()
    {
        for(int i = 0; i < jugadores.length; i = i + 1)
        {
            jugadores[i] = new Jugador("jugador" + (i + 1));
        }
        menuInicial();
    }

    static void menuInicial()
    {
        int opcion = 1;
        while (opcion != 0) 
        {
            System.out.println("\n\nM E N U  I N I C I A L");
            System.out.println("[1] Jugar");
            System.out.println("[2] Ordenar jugadores");
            System.out.println("[0] Salir");
            opcion = scanner1.nextInt();
            switch (opcion) 
            {
                case 1:
                    ingresaJugadores();
                    break;
                case 2:
                    ordenarJugadores();
                    break;
            }
        }
    }

    static void ingresaJugadores()
    {
        System.out.println("[?] Seleccione ingresando un indice");
        listarJugadores();

        System.out.print("\nIdice de jugadorA: ");
        int indiceA = scanner1.nextInt();
        System.out.print("\nNombre de jugadorA: ");
        String nombreA = scanner1.next();
        System.out.print("\nIndice de jugadorB: ");
        int indiceB = scanner1.nextInt();
        System.out.print("\nNombre de jugadorB: ");
        String nombreB = scanner1.next();

        jugadorAaA = jugadores[indiceA - 1];
        jugadorAaA.setNombre(nombreA);
        jugadorBbB = jugadores[indiceB - 1];
        jugadorBbB.setNombre(nombreB);
        primerTurno();
    }

    static void primerTurno()
    {
        String[] cosas = {"piedra", "papel", "tijera"};
        String tiroA = cosas[random1.nextInt(3)];
        String tiroB = cosas[random1.nextInt(3)];
        while(tiroA.equals(tiroB))
        {
             tiroA = cosas[random1.nextInt(3)];
             tiroB = cosas[random1.nextInt(3)];
        }
                        
        System.out.println("\nPIEDRA, PAPEL O TIJERA");
        System.out.println("El ganador toma el primer turno");
        System.out.println(jugadorAaA.getNombre() + ": " + tiroA);
        System.out.println(jugadorBbB.getNombre() + ": " + tiroB);

        // se unen los resultados para verificar si es forma de ganar
        String tiroGanador = tiroA + tiroB;
        //System.out.println("[?] " + tiroGanador);

        // en equals("palabra") hay palabras que son formas de ganar, las unicas
        boolean esGanadorA = (tiroGanador).equals("piedratijera") | (tiroGanador).equals("papelpiedra") | (tiroGanador).equals("tijerapapel");
        if(esGanadorA)
        {
            System.out.println("[!] Ganador: " + jugadorAaA.getNombre());
        }
        else
        {
            System.out.println("[!] Ganador: " + jugadorBbB.getNombre());
        }
        partida();
    }

    static void listarJugadores()
    {
        for (int j = 0; j < jugadores.length; j = j + 1) 
        {
            System.out.println((j + 1) + ") " + jugadores[j].getNombre());
        }
    }

    static void ordenarJugadores()
    {
        Jugador[] jugadoresOrdenados = jugadores;
  
        for (int i = 0; i < jugadoresOrdenados.length - 1; i = i + 1) 
        {
            for (int j = i + 1; j < jugadoresOrdenados.length; j = j + 1) 
            {
                if (jugadoresOrdenados[i].getGanadas() < jugadoresOrdenados[j].getGanadas()) 
                {
                    Jugador temporal = jugadoresOrdenados[i];
                    jugadoresOrdenados[i] = jugadoresOrdenados[j];
                    jugadoresOrdenados[j] = temporal;
                }
            }
        }
        for (int j = 0; j < jugadoresOrdenados.length; j = j + 1) 
        {
            System.out.println((j + 1) + ") " + jugadoresOrdenados[j].getNombre() + ": " + jugadoresOrdenados[j].getGanadas());
        }
    }

    static void partida()
    {
        int resp1 = 0;
        int resp2 = 0;

        tablero1.ordenarFichas();
        boolean salir = false;
        while(!salir)
        {
            tablero1.ingresarMovimiento();
            System.out.println("Para continuar ingrese 1, para salir ingrese -1");
            resp1 = scanner1.nextInt();
            if(resp1 == -1)
            {
                salir = true;
                System.out.println("Ingrese 1 si es verdadero, 0 sino");
                System.out.print("Es " + jugadorAaA.getNombre() + " el ganador? ");
                resp2 = scanner1.nextInt();
                if(resp2 == 1)
                {
                    System.out.print("[?] " + jugadorAaA.getNombre() + " es el ganador");
                    jugadorAaA.gano();
                    jugadorBbB.perdio();
                }
                else
                {
                    System.out.print("[?] " + jugadorBbB.getNombre() + " es el ganador");
                    jugadorAaA.perdio();
                    jugadorBbB.gano();
                }
            }
        }
    }
}
