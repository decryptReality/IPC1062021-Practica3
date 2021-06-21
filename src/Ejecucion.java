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

    static void listarJugadores()
    {
        for (int j = 0; j < jugadores.length; j = j + 1) 
        {
            System.out.println((j + 1) + ") " + jugadores[j].getNombre());
        }
    }
}
