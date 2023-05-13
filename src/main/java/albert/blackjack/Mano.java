/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package albert.blackjack;
import java.util.ArrayList;
/**
 *
 * @author Albert
 */
/***
 * Clase para crear y gestionar las manos de los jugadores. 
 * @author Albert
 */
public class Mano {
    private ArrayList<Integer> mano;

    public Mano() {
        this.mano = new ArrayList<Integer>();
    }

    public ArrayList<Integer> getMano() {
        return mano;
    }

    public void setMano(ArrayList<Integer> mano) {
        this.mano = mano;
    }
    
    /***
     * Funcion que devuelve las cartas de la mano, sus valores y el valor total de la mano.
     */
    public void mirarMano(){
        int total = 0;
        for(int i=0; i<mano.size(); i++){
            System.out.println("Posicion: " + i + " Valor " + mano.get(i));
            total+=mano.get(i);
        }
        System.out.println("El valor total de la mano es de " + total + " puntos.");
    }
    
    /***
     * Funcion que devuelve el valor total de la mano.
     * @return 
     */
    public int devolverTotal(){
        int total = 0;
        for(int i=0; i<mano.size(); i++){
            total+=mano.get(i);
        }
        return total;
    }
    
    /***
     * Funcion para añadir una carta a la mano del jugador.
     * @param carta 
     */
    public void añadirCarta(int carta){
    mano.add(carta);
    }
    
    /***
     * Funcion que comprobará si la mano contiene un As.
     * @return 
     */
    public boolean comprobarAs(){
        for(int i=0; i<mano.size(); i++){
            if(mano.get(i) == 11){
                return true;
            }
        }
        return false;
    }
    
    
}
