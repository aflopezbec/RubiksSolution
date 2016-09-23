/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.lang.instrument.Instrumentation;

import java.util.BitSet;
/**
 *
 * @author AnDresLoPz
 */
public class tmp {
    
        
    public static boolean getBit(int pos,int cube[]) {
        return (cube[pos / 32] & (1 << (pos % 32))) != 0;
    }
    
    public static void setBit(int pos, boolean b,int cube[]) {
        int b8 = cube[pos / 32];
        int posBit = (1 << (pos % 32));
        if (b) {
            b8 |= posBit;
        } else {
            b8 &= (255 - posBit);
        }
        cube[pos / 8] = b8;
    }
    
    public static void main(String []args){
        int cube[] = new int[5];
        tmp.setBit(1, true, cube);
        tmp.setBit(2, true, cube);
        
        System.out.println(tmp.getBit(0, cube));
        System.out.println(tmp.getBit(1, cube));
        System.out.println(tmp.getBit(143, cube));
        System.out.println(tmp.getBit(144, cube));
        System.out.println(tmp.getBit(145, cube));
    }    
}
