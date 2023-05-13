/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package albert.blackjack;

import java.util.Random;

/**
 *
 * @author Albert
 */
/***
 * Clase para la gestion de la baraja.
 * @author Albert
 */
public class Baraja {
    int[] cartas = new int[52];
    
    /***
     * Cuando creamos una baraja, rellenamos un array de 52 posiciones(Total de la baraja francesa) y con el metodo shuffle, mezclamos los valores.
     */
    public Baraja() {
        this.cartas = iniciaBaraja(cartas);
        this.cartas = shuffle(cartas);
    }

    public int[] getCartas() {
        return cartas;
    }

    public void setCartas(int[] cartas) {
        this.cartas = cartas;
    }
    
    /***
     * Funcion que recorre el mazo y asigna valores numericos al array de cartas.
     * @param cartas
     * @return 
     */
    public int[] iniciaBaraja(int[] cartas){
    int index = 0;
    //Cada palo tiene 13 Cartas.
    for (int i = 1; i <= 13; i++) {
        //Hay 4 palos en la baraja francesa.
        for (int j = 0; j < 4; j++) {
            if (i == 1) {
                cartas[index] = 11; // As tiene un valor inicial de 11
            } else if (i > 10) {
                cartas[index] = 10; // Las cartas J, Q y K valen 10 puntos
            } else {
                cartas[index] = i; // Las cartas numéricas valen su propio número
            }
            index++;
        }
    }
    return cartas;
    }
    
    /***
     * Funcion que mezcla los valores de la baraja por posiciones aleatorias mediante BubbleSort.
     * @param cartas
     * @return 
     */
    public int[] shuffle(int[] cartas) {
        Random rnd = new Random();
        for (int i = cartas.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int temp = cartas[index];
            cartas[index] = cartas[i];
            cartas[i] = temp;
        }
        return cartas;
    }
    
    /***
     * Funcion que indicandole una posicion y la baraja, nos devolverá la carta de esa posicion.
     * @param cartas
     * @param posicion
     * @return 
     */
    public static int sacarCarta(int[] cartas, int posicion){
    int cartaEscogida = cartas[posicion];
    return cartaEscogida;
    }
    
    /***
     * Funcion para reducir el mazo una vez que sacamos una carta.
     * @param carta
     * @param cartas
     * @return 
     */
    public static int[] reduceBaraja(int carta, int[] cartas){
    // Creamos una baraja nueva con una carta menos.
    int[] barajaReducida = new int[cartas.length - 1];
    
    // Copiamos la baraja menos la posicion de la carta que le pasamos.
    for (int i = 0, j = 0; i < cartas.length; i++) {
        if (i != carta) {
            barajaReducida[j] = cartas[i];
            j++;
        }
    }
    //Devolvemos la nueva baraja.
    return barajaReducida;
    }
}
