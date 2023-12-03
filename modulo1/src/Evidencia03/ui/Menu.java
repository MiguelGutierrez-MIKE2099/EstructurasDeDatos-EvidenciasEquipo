/**
 * @author Miguel Gutiérrez
 */

package Evidencia03.ui;

import Evidencia03.ui.MenuAccion;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * El menú proporciona un conjunto de variables y miembros que describen una plantilla de un menú de usuario.
 */
public class Menu {
    /**
     * Textos que aparecen como elementos de interacción en el menú:
     */
    private String MENU_WELCOME = "Menú";
    private String REQUEST_OPTION = "Digite el número correspondiente a la opción a elegir: ";
    private String NUMERIC_TYPE_ERROR = "El valor ingresado no posee un formato numérico. Intente de nuevo: ";
    private String OUT_OF_RANGE_ERROR = "Opción no disponible. Intente de nuevo: ";
    private String END_OF_PROGRAM = "Programa finalizado.";

    /**
     * optionList almacena las cadenas que corresponden a las opciones mostradas al usuario + una opción de salida.
     * menuActionPrototypeList almacena las funciones asociadas a cada opción mostrada al usuario excepto la opción de salida.
     */
    private ArrayList<String> optionList = new ArrayList<>();
    private ArrayList<MenuAccion> menuActionPrototypeList = new ArrayList<>();

    /**
     * alive establece el estado de disponibilidad del menú para el usuario y ayuda a determinar si el menú debe mantenerse mostrado o no.
     */
    private boolean alive = true;

    /**
     * killMenu establece que el menú del usuario debe dejar de mostrarse al usuario.
     */
    public void killMenu() {
        alive = false;
    }

    /**
     * isAlive determina el estado de disponibilidad del menú para el usuario.
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * addOption un menú de usuario dado las opciones que debe proporcionarles y las acciones asociadas a cada uno de ellos.
     * @param option: un texto que contiene una de las opciones del menú.
     * @param menuAccion: el método que debe ejecutarse al seleccionar la opción asociada a él.
     * @devolver esto: el menú actual con sus correspondientes modificaciones.
     */
    public Menu addOption(String option, MenuAccion menuAccion) {
        optionList.add(option);
        menuActionPrototypeList.add(menuAccion);
        return this;
    }

    /**
     * createMenu añade un método de salida al final del menú.
     */
    public void addExitOption() {
        optionList.add("Salir");
    }

    /**
     * cleanMenu elimina todas las opciones y acciones asociadas a ellos del menú.
     */
    public void clearMenu() {
        optionList.clear();
        menuActionPrototypeList.clear();
    }

    /**
     showMenu muestra las opciones dentro del menú
     */
    public void showMenu() {
        System.out.println(MENU_WELCOME);
        for (int i = 0; i < optionList.size(); i++) {
            System.out.println(i + 1 + ") " + optionList.get(i));
        }
    }

    public void requestOption(){
        System.out.print(REQUEST_OPTION);
    }

    /**
     * readOption lee la entrada del usuario y la valida en el formato adecuado.
     * @return opción: un número que representa el índice de una opción específica.
     */
    public int readOption() {
        Scanner input = new Scanner(System.in);
        while (true) {
            try {
                int option = input.nextInt();
                input.nextLine();
                if (option < 0 || option > optionList.size()) {
                    System.out.print(OUT_OF_RANGE_ERROR);
                    continue;
                }
                else if(option == 0){
                    killMenu();
                    option = optionList.size();
                }
                return option;
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.print(NUMERIC_TYPE_ERROR);
            }
        }
    }

    /**
     * selectAndRunOption ejecuta la función asociada a la opción seleccionada por el usuario
     * @param option: el número que representa el índice de la opción seleccionada por el usuario.
     */
    public void executeOption(int option) {
        if (option == optionList.size()) {
            killMenu();
            System.out.println(END_OF_PROGRAM);
            return;
        }
        menuActionPrototypeList.get(option - 1).definedAction();
    }
}