/**
 * @author Miguel Gutierrez
 */

package evidencia1.ui;

import evidencia1.utils.PriorityQuese;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * CLI contiene todos los datos que serán procesados por la clase Pasajeros.
 * CLI define los detalles del menú de usuario y lo ejecuta.
 */
public class CLI {
/**
 * Datos utilizados por el paquete de procesos.
 */
private static PriorityQueue priorityQueue = new PriorityQueue();
    private static Scanner input = new Scanner(System.in);
    private static int limit;
    private static int minPushTime = 1;
    private static int maxPushTime = 5;
    private static int minPopTime = 1;
    private static int maxPopTime = 5;
    private static Random random = new Random();

    /**
     * Creando el menu de usuario.
     */
    private static Menu menu = new Menu();

    /**
     *  Opciones del menu.
     */
    private static String SET_PRIORITY_QUEUE_LIMIT = "Definicion el tope de la cola de prioridad.";
    private static String SET_PUSH_TIME_RANGE = "Definicion del tiempo de espera antes de insertar un nuevo elemento a la cola de prioridad.";
    private static String SET_POP_TIME_RANGE = "Definicion el tiempo de espera antes de eliminar un elemento a la cola de prioridad.";;
    private static String START_SIMULATION = "Iniciar la simulación de la cola de prioridad.";

    /**
     * Muestreo de otras opciones.
     */
    private static String INPUT_MIN_PUSH_TIME = "Ingrese el tiempo mínimo en segundos que puede tomar un elemento antes de ser ingresado a la cola de prioridad:\n";
    private static String INPUT_MAX_PUSH_TIME = "Ingrese el tiempo máximo en segundos que puede tomar un elemento antes de ser ingresado a la cola de prioridad:\n";
    private static String INPUT_MIN_POP_TIME = "Ingrese el tiempo mínimo en segundos que puede tomar un elemento antes de ser eliminado a la cola de prioridad:\n";
    private static String INPUT_MAX_POP_TIME = "Ingrese el tiempo máximo en segundos que puede tomar un elemento antes de ser eliminado a la cola de prioridad:\n";
    private static String ELEMENT_PUSHED_MESSAGE = "Se ha insertado el elemento %d a la cola de prioridad.\n";
    private static String ELEMENT_POPPED_MESSAGE = "Se ha eliminado el elemento %d a la cola de prioridad.\n";

    /**
     * Define la acción que simula el trabajo
     * simultáneo de dos servidores en ejecución diferentes.
     */
    private static MenuActionPrototype setPriorityQueueLimit = () -> {
        System.out.printf("Ingrese el tope de la cola de prioridad:\n");
        limit = input.nextInt();
    };

    private static MenuActionPrototype setPushTimeRange = () -> {
        do {
            System.out.printf(INPUT_MIN_PUSH_TIME);
            minPushTime = input.nextInt();

            System.out.printf(INPUT_MAX_PUSH_TIME);
            maxPushTime = input.nextInt();

            if (minPushTime >= maxPushTime) {
                System.out.println("El valor mínimo debe ser menor que el valor máximo. Inténtalo de nuevo.");
            }
        } while (minPushTime >= maxPushTime);
    };

    private static MenuActionPrototype setPopTimeRange = () -> {
        do {
            System.out.printf(INPUT_MIN_POP_TIME);
            minPopTime = input.nextInt();

            System.out.printf(INPUT_MAX_POP_TIME);
            maxPopTime = input.nextInt();

            if (minPopTime >= maxPopTime) {
                System.out.println("El valor mínimo debe ser menor que el valor máximo. Inténtalo de nuevo.");
            }
        } while (minPopTime >= maxPopTime);
    };

    private static MenuActionPrototype startSimulation = () -> {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        // Ejecuta push
        executorService.scheduleAtFixedRate(() -> {
            int elmntToPush = random.nextInt((5 - 1) + 1) + 1;
            priorityQueue.push(elmntToPush);
            System.out.println(String.format(ELEMENT_PUSHED_MESSAGE, elmntToPush));
        }, 0, random.nextInt((maxPushTime - minPushTime) + 1) + minPushTime, TimeUnit.SECONDS);

        // Ejecuta pop
        executorService.scheduleAtFixedRate(() -> {
            int poppedElmnt = priorityQueue.pop().get();
            System.out.println(String.format(ELEMENT_POPPED_MESSAGE, poppedElmnt));
        }, 0, random.nextInt((maxPopTime - minPushTime) + 1) + minPushTime, TimeUnit.SECONDS);

        // Ejecuta el programa durante 3 minutos (180 segundos)
        try {
            Thread.sleep(180000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Detiene el programa después de 3 minutos
        executorService.shutdown();
    };

    /**
     * Añadir las opciones y acciones del menú.
     */
    static {
        menu
                .addOption(SET_PRIORITY_QUEUE_LIMIT, setPriorityQueueLimit)
                .addOption(SET_PUSH_TIME_RANGE, setPushTimeRange)
                .addOption(SET_POP_TIME_RANGE, setPopTimeRange)
                .addOption(START_SIMULATION, startSimulation)
                .addExitOption();
    }

    /**
     * Mostrar el menú y ejecutar las acciones asociadas a cada opción.
     */
    public static void launchApp() {
        do {
            menu.showMenu();
            menu.requestOption();
            int option = menu.readOption();
            menu.executeOption(option);
        } while (menu.isAlive());
    }
}
