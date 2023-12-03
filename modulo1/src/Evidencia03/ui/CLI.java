/**
 * @author Miguel Gutiérrez
 */

package Evidencia03.ui;

import Evidencia03.ui.Menu;
import Evidencia03.ui.MenuAccion;
import Evidencia03.utils.Grafo;
import Evidencia03.utils.NodoGrafo;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * CLI contiene todos los datos que van a ser procesados.
 * CLI define los detalles del menú de usuario y lo ejecuta.
 */
public class CLI {
    /**
     * Datos utilizados por CLI:
     */
    private static Grafo animals = new Grafo();
    private static ArrayList<String> animalFeatures;
    private static Scanner input = new Scanner(System.in);

    /**
     * Metodos empleados por la clase CLI:
     */
    private static String readYesNoAnswer(){
        Scanner input = new Scanner(System.in);
        String ans = "";

        do {
            System.out.print("Por favor, ingresa 'Sí' o 'No': ");
            ans = input.nextLine().trim();
        } while (!"Sí".equalsIgnoreCase(ans) && !"No".equalsIgnoreCase(ans));

        return ans;
    }

    private static String readAnimalFeature(){
        String feature;

        System.out.println("¿Qué característica tiene el animal en el que pensabas?");
        if(animalFeatures.size() > 0){
            System.out.println(" Además de las siguientes:");
            for(String animalFeature : animalFeatures){
                System.out.println("\t-> " + animalFeature);
            }
            System.out.println();
        }

        feature = input.nextLine();

        return feature;
    }

    private static String readAnimal(){
        String animal;

        System.out.println("¿En qué animal estabas pensando?");
        animal = input.nextLine();

        return animal;
    }

    /**
     * Creando el menu de usuario:
     */
    private static Menu menu = new Menu();

    /**
     *  Opciones del menú:
     */
    private static String GUESS_ANIMAL = "Piensa en un animal para intentar adivinarlo...";

    /**
     * Acciones del menú.
     */
    /**
     * Define la acción que simula el trabajo simultáneo de dos servidores en ejecución diferentes.
     */
    private static MenuAccion startGame = () -> {
        NodoGrafo currentNode = animals.getRoot();

        animalFeatures = new ArrayList<>();
        String hasFeature = "No", isAnimal = "No";

        System.out.println("Juego iniciado.");
        System.out.println("Piensa en un animal e intentaré adivinar de cuál se trata, ¿va?");
        System.out.println("¡Empecemos!");

        while(currentNode.getNeighbours().size() > 0) {
            // Changing animal accordingly.
            System.out.println("¿El animal en el que estás pensando posee la siguiente característica?" +
                    "\n\t-> " + currentNode.getNextAnimalFeature());
            hasFeature = readYesNoAnswer();

            if (hasFeature.equalsIgnoreCase("Sí")) {
                currentNode = currentNode.getNeighbourYes();
            }
            else if (hasFeature.equalsIgnoreCase("No")) {
                currentNode = currentNode.getNeighbourNo();
            }

            // Guessing animal
            System.out.println("¿El animal en el que estás pensando es el siguiente?" +
                    "\n\t-> " + currentNode.getAnimal());
            isAnimal = readYesNoAnswer();
            if(isAnimal.equalsIgnoreCase("Sí")){
                break;
            }
        }

        if(isAnimal.equalsIgnoreCase("Sí")){
            System.out.println("¡He adivinado! ¡Wuuu!");
        }
        else if(isAnimal.equalsIgnoreCase("No")){
            String animal, feature;

            System.out.println("He perdido. :(");
            feature = readAnimalFeature();
            animal = readAnimal();

            currentNode.setNextAnimalFeature(feature);

            animals.addEdgeYes(currentNode, new NodoGrafo(animal));

        }

    };

    /**
     * Aqui se agregan las opciones y las acciones dentro del menú:
     */
    static {
        menu
                .addOption(GUESS_ANIMAL, startGame)
                .addExitOption();
    }

    /**
     * Mostrando menú y ejecutando las acciones en relación a la opción seleccionada...
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
