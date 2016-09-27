/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.lang.instrument.Instrumentation;

import java.util.BitSet;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
/**
 *
 * @author AnDresLoPz
 */
public class tmp {
    
    
    public static void main(String []args){
        
        Queue<RubiksCube_A> cola = new PriorityQueue<>();
        RubiksCube_A a = new RubiksCube_A();
        a.setCost(10);
        RubiksCube_A b = new RubiksCube_A();
        b.setCost(15);
        RubiksCube_A c = new RubiksCube_A();
        c.setCost(17);
        
        cola.add(c);
        cola.add(b);
        cola.add(a);
        RubiksCube_A h = new RubiksCube_A();
        h.setCost(8);
        cola.add(h);
        
        System.out.println(cola.remove().getCost());
        System.out.println(cola.remove().getCost());
        System.out.println(cola.remove().getCost());  

    }    
    
    }
