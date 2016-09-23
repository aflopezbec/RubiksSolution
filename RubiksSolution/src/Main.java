
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Collection;
import java.util.Enumeration;
import java.util.GregorianCalendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Edwin
 */
public class Main {  
    
    //condiciones iniciales (restricciones)
    private static final int NUM_LEVELS = 10;//15;
    private static final int NUM_MOV = 6;
    private static final long NUM_NODES = (long)((Math.pow(NUM_MOV, NUM_LEVELS)-1)/(NUM_MOV-1));
    private static final long TOTAL = (long)((Math.pow(NUM_MOV, NUM_LEVELS+1)-1)/(NUM_MOV-1));
        
    public static void main(String []args){
        
        long TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        byte[] original = Tools.originalCube();
        Graph_A g = new Graph_A();
        int selection = 3;
        
        TInicio = System.currentTimeMillis();    
        System.out.println("Iniciio: "+ new GregorianCalendar().getTime());
        
        System.out.println("Representación cubo armando: ");
        System.out.println(Tools.printRubiks(original));
        System.out.println("");
        
        
        System.out.println("Cantidad todal de nodos : "+TOTAL+ " en el nivel :"+NUM_LEVELS);
        
        if (selection==0) g.DFS(1L, Tools.RandomCube(NUM_LEVELS), TOTAL,NUM_LEVELS,original);
        else if(selection == 1) g.BFS(1L,Tools.RandomCube(NUM_LEVELS), TOTAL,original);
        else if(selection == 2) g.DFSIterative(1L, Tools.RandomCube(NUM_LEVELS), TOTAL,NUM_LEVELS,original);
        else g.AStar(1L, Tools.RandomCube(NUM_LEVELS), TOTAL,NUM_LEVELS,original,1);
        //else g.AStar(1L, Tools.MoveDPrime(original), TOTAL,NUM_LEVELS,original);
        
        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = TFin - TInicio; //Calculamos los milisegundos de diferencia
        System.out.println("Tiempo de ejecución total : " + tiempo+"ms"); 
    }
       
}
