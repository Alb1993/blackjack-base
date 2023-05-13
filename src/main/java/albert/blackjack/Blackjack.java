/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package albert.blackjack;

import java.util.Random;
import java.util.Scanner;


/**
 *
 * @author Albert
 */
public class Blackjack {
    
    public static Scanner in = new Scanner(System.in);
    public static Baraja baraja;
    public static Mano mano;
    public static Mano manoCrupier;
    public static Random rand = new Random();
    public static boolean victoria = false;
    
    public static void main(String[] args) {
        System.out.println("Bienvenido a BlackJack");
        System.out.println("1. Usuario Nuevo");
        System.out.println("2. Usuario Existente");
        int opcion = in.nextInt();
        switch (opcion){
            case 1:
                registro();
                break;
            case 2:
                login();
                break;
        }
    }
    /***
     * Funcion para registrar a un jugador.
     */
    public static void registro(){
        System.out.println("Introduce Nombre de Usuario: ");
        String username = in.nextLine();
        System.out.println("Introduce Contraseña: ");
        String password = in.nextLine();
        int puntuacion = 0;
        Usuario usuario = new Usuario(username, password, puntuacion);
    }
    /***
     * Funcion para hacer login en el juego.
     */
    public static void login(){
        System.out.println("Introduce Nombre de Usuario: ");
        String username = in.nextLine();
        System.out.println("Introduce Contraseña: ");
        String password = in.nextLine();
        //Comprobacion
        eligeDificultad();
        //jugar();
    }
    /***
     * Funcion para elegir una dificultad dentro del juego.
     */
    public static void eligeDificultad(){
        System.out.println("Elige Dificultad: ");
        System.out.println("1. Facil");
        System.out.println("2. Dificil");
        int opcionDif = in.nextInt();
        //Falta hacer las opciones de dificultad.
        switch(opcionDif){
            case 1:
                jugar();
                break;
            case 2: 
                jugar();
                break;
        }
    }
    public static void jugar(){
            //Iniciamos una baraja y las manos, tanto la nuestra como la del crupier.
            baraja = new Baraja();
            mano = new Mano();
            manoCrupier = new Mano();
            
            //Sacamos dos cartas, tanto para nosotros como para el crupier.
            sacarCarta(mano);
            sacarCarta(mano);
            sacarCarta(manoCrupier);
            sacarCarta(manoCrupier);
            
        //Mientras que no haya un ganador, se le preguntara al usuario que quiere hacer.
        //Las decisiones del crupier vendran automatizadas dependiendo del valor de la mano.
        while (victoria == false){
            int opcion = eligeOpcion();
            int opcionCrupier = eligeOpcionCrupier();
            gestionaOpcion(opcion, mano);
            gestionaOpcion(opcionCrupier,manoCrupier);
        }
        //Una vez que haya un ganador, comprobaremos las manos de cada jugador y asignaremos la puntuacion en variables nuevas con las comprobaciones hechas.
        int puntuacion = recuentoPuntos(mano);
        int puntuacionCrupier = recuentoPuntos(manoCrupier);
        //I, por ultimo, mostramos el ganador.
        mostrarValores(puntuacion,puntuacionCrupier);
    
    }
    
    /***
     * Funcion para sacar una carta del mazo i añadirla a la mano de un jugador.
     * @param mano 
     */
    public static void sacarCarta(Mano mano){
            //Empezamos generando una posicion aleatoria.
            int posicion = rand.nextInt(baraja.cartas.length + 1);
            //Luego llamamos a sacarCarta, la que nos devolvera el valor de la carta sacada de la baraja en la posicion escogida.
            int cartaEscogida =  Baraja.sacarCarta(baraja.cartas, posicion);
            //Ahora reducimos en uno el tamaño de la baraja.
            baraja.cartas = baraja.reduceBaraja(posicion, baraja.cartas);
            //Añadimos la carta a la mano del jugador.
            mano.añadirCarta(cartaEscogida);
            //I, por ultimo, mezclamos las cartas de la baraja.
            baraja.shuffle(baraja.cartas);
    }
    
    
    /***
     * Funcion para procesar las decisiones del jugador cada Ronda.
     * @return 
     */
    public static int eligeOpcion(){
        mano.mirarMano();
        System.out.println("Que deseas hacer?");
        System.out.println("1. Sacar Carta");
        System.out.println("2. Plantarse");
        int opcion = in.nextInt();
        return opcion;
    }
    
    /***
     * Funcion que procesara las decisiones automatizadas del crupier.
     * @return 
     */
    public static int eligeOpcionCrupier(){
        int valorMano = manoCrupier.devolverTotal();
        int opcion = 0;
        if(valorMano<11){
            opcion=1;
        }else if(valorMano>=17){
            opcion=2;
        }else if((manoCrupier.comprobarAs())&&(valorMano>17)){
            opcion=1;
        }
        return opcion;
    }
    
    /***
     * Función que gestionará las decisiones del jugador y del crupier.
     * @param opcion
     * @param manoJugador 
     */
    public static void gestionaOpcion(int opcion,Mano manoJugador){
        //Opcion 1: Sacar carta, Opcion 2: Plantarse.
        if(opcion == 1){
            sacarCarta(manoJugador);
        }else{
            victoria=true;
        }
    }
    /***
     * Funcion que recuenta los puntos de la mano del jugador que le pasemos.
     * @param mano
     * @return 
     */
    public static int recuentoPuntos(Mano mano){
        int puntuacion = mano.devolverTotal();
        //Si la mano contiene un As y suma mas de 21 puntos, se le restaran 10 puntos, ya que el As puede valer 1 o 11, dependiendo de la decision del jugador.
        if((mano.comprobarAs())&&(puntuacion>21)){
        puntuacion=mano.devolverTotal()-10;
        }
        return puntuacion;
    }
    
    /***
     * Funcion que mostrará el total de puntos de los dos jugadores y el ganador.
     * @param puntos
     * @param puntosCrupier 
     */
    public static void mostrarValores(int puntos, int puntosCrupier){
        System.out.println("Total puntos Jugador: " + puntos);
        System.out.println("Total puntos Crupier: " + puntosCrupier);
        if((puntos>puntosCrupier)&&(puntos<=21)){
            System.out.println("Enhorabuena! Has Ganado!");
        }else{
            System.out.println("Mala Suerte, Intentalo otra vez.");
        }
        
    }
    
}


