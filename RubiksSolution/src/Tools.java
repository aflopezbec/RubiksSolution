
import java.util.BitSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author AndresL
 */
public class Tools {
    
    public static BitSet RandomCube(int level){
        String pathNewCube = "";
        BitSet cube = originalCube();
        for (int i = 0; i < level; i++) {
            int indexrandom = (int)(Math.random()*5);
            switch (indexrandom){
                case 0: cube = MoveLPrime(cube); pathNewCube+="L' "; break;
                case 1: cube = MoveUPrime(cube); pathNewCube+="U' "; break;
                case 2: cube = MoveBPrime(cube); pathNewCube+="B' "; break;
                case 3: cube = MoveDPrime(cube); pathNewCube+="D' "; break;
                case 4: cube = MoveFPrime(cube); pathNewCube+="F' "; break;
                case 5: cube = MoveRPrime(cube); pathNewCube+="R' "; break;
            }
        }    
        System.out.println("Camino random: "+pathNewCube);  
        //cube.printArrayCube();
        return cube;
    }
    
    public static BitSet moveCube(Long node, BitSet cubeArray){
        
        //long module = node%18;
        long module = node%6;//6
        long tmp = 0;
        if (module == 0) tmp=4;
        else if (module == 1) tmp=5;
        else tmp = module-2;
        
//        System.out.println("move "+tmp);
//        cube.printArrayCube();
        
        if (tmp==0) return MoveL(cubeArray);
        if (tmp==1) return MoveU(cubeArray);
        if (tmp==2) return MoveB(cubeArray);
        if (tmp==3) return MoveD(cubeArray);
        if (tmp==4) return MoveF(cubeArray);
        if (tmp==5) return MoveR(cubeArray);
        System.out.println("Error, Movimiento invalido: "+tmp);
        System.exit(0);
        return null;
    }
    
    public static BitSet moveCube(int tmp, BitSet cubeArray){
        if (tmp==0) return MoveL(cubeArray);
        if (tmp==1) return MoveU(cubeArray);
        if (tmp==2) return MoveB(cubeArray);
        if (tmp==3) return MoveD(cubeArray);
        if (tmp==4) return MoveF(cubeArray);
        if (tmp==5) return MoveR(cubeArray);
        System.out.println("Error, Movimiento invalido: "+tmp);
        System.exit(0);
        return null;
    }
    
    public static BitSet originalCube( ) 
    {   //Create base cube:: UP LEFT FRONT RIGHT BACK DOWN
        // Face UP: White (000) 0:0-24
        // Face LEFT: Green (001) 24-48
        // Face FRONT: Red (010) 48-72
        // Face RIGTH: Blue (100) 72-96
        // Face BACK: ORANGE (110) 96-120
        // Face DOWN: Yellow (111) 120-144
        
        BitSet bitCube = new BitSet();
        //GREEEN
        for (int i = 26; i < 48; i+=3) {
            bitCube.set(i);
        }
        //RED
        for (int i = 49; i < 72; i+=3) {
            bitCube.set(i);
        }
        //BLUE
        for (int i = 72; i < 96; i+=3) {
            bitCube.set(i);
        }
        //ORANGE
        for (int i = 96; i < 120; i+=3) {
            bitCube.set(i);
            bitCube.set(i+1);
        }
        //YELLOW
        for (int i = 120; i < 144; i++) {
            bitCube.set(i);
        }
        return bitCube;
    }
    
    private static void cBits(BitSet bitCube, BitSet current,
            int x,int y,int z,int xp,int yp,int zp){
        bitCube.set(x, current.get(xp));
        bitCube.set(x+1, current.get(xp+1));
        bitCube.set(x+2, current.get(xp+2));
        bitCube.set(y, current.get(yp));
        bitCube.set(y+1, current.get(yp+1));
        bitCube.set(y+2, current.get(yp+2));
        bitCube.set(z, current.get(zp));
        bitCube.set(z+1, current.get(zp+1));
        bitCube.set(z+2, current.get(zp+2));
    }
    
    private static void gFace(BitSet bitCube, BitSet current,int x){
        //ChangeCorners
        bitCube.set(x, current.get(x+15));
        bitCube.set(x+1, current.get(x+16));
        bitCube.set(x+2, current.get(x+17));
        
        bitCube.set(x+6, current.get(x));
        bitCube.set(x+7, current.get(x+1));
        bitCube.set(x+8, current.get(x+2));
        
        bitCube.set(x+21, current.get(x+6));
        bitCube.set(x+22, current.get(x+7));
        bitCube.set(x+23, current.get(x+8));
        
        bitCube.set(x+15, current.get(x+21));
        bitCube.set(x+16, current.get(x+22));
        bitCube.set(x+17, current.get(x+23));
        
        //ChageCenters
        bitCube.set(x+3, current.get(x+9));
        bitCube.set(x+4, current.get(x+10));
        bitCube.set(x+5, current.get(x+11));
        
        bitCube.set(x+9, current.get(x+18));
        bitCube.set(x+10, current.get(x+19));
        bitCube.set(x+11, current.get(x+20));
        
        bitCube.set(x+18, current.get(x+12));
        bitCube.set(x+19, current.get(x+13));
        bitCube.set(x+20, current.get(x+14));
        
        bitCube.set(x+12, current.get(x+3));
        bitCube.set(x+13, current.get(x+4));
        bitCube.set(x+14, current.get(x+5));
        
    }
    
    private static void gFacePrime(BitSet bitCube, BitSet current,int x){
        //ChangeCorners
        bitCube.set(x, current.get(x+6));
        bitCube.set(x+1, current.get(x+7));
        bitCube.set(x+2, current.get(x+8));
        
        bitCube.set(x+6, current.get(x+21));
        bitCube.set(x+7, current.get(x+22));
        bitCube.set(x+8, current.get(x+23));
        
        bitCube.set(x+21, current.get(x+15));
        bitCube.set(x+22, current.get(x+16));
        bitCube.set(x+23, current.get(x+17));
        
        bitCube.set(x+15, current.get(x));
        bitCube.set(x+16, current.get(x+1));
        bitCube.set(x+17, current.get(x+2));
        
        //ChageCenters
        bitCube.set(x+3, current.get(x+12));
        bitCube.set(x+4, current.get(x+13));
        bitCube.set(x+5, current.get(x+14));
        
        bitCube.set(x+9, current.get(x+3));
        bitCube.set(x+10, current.get(x+4));
        bitCube.set(x+11, current.get(x+5));
        
        bitCube.set(x+18, current.get(x+9));
        bitCube.set(x+19, current.get(x+10));
        bitCube.set(x+20, current.get(x+11));
        
        bitCube.set(x+12, current.get(x+18));
        bitCube.set(x+13, current.get(x+19));
        bitCube.set(x+14, current.get(x+20));
        
    }
    
    public static BitSet MoveL(BitSet current){
        BitSet bitCube = (BitSet)current.clone();
        
        cBits(bitCube, current, 0, 9, 15, 117, 108, 102);
        cBits(bitCube, current, 48, 57, 63, 0, 9, 15);
        cBits(bitCube, current, 120, 129, 135, 48, 57, 63);
        cBits(bitCube, current, 117, 108, 102, 120, 129, 135);
        gFace(bitCube, current, 24); //Girate LEFT
        return bitCube;
    }
    
    public static BitSet MoveLPrime(BitSet current){
        BitSet bitCube = (BitSet)current.clone();
        
        cBits(bitCube, current, 0, 9, 15, 48, 57, 63);
        cBits(bitCube, current, 48, 57, 63, 120, 129, 135);
        cBits(bitCube, current, 120, 129, 135, 117, 108, 102);
        cBits(bitCube, current, 117, 108, 102, 0, 9, 15);
        gFacePrime(bitCube, current, 24); //Girate LEFT
        return bitCube;
    }
    
    public static BitSet MoveR(BitSet current){
        BitSet bitCube = (BitSet)current.clone();
        cBits(bitCube, current, 6, 12, 21, 54, 60, 69);
        cBits(bitCube, current, 54, 60, 69, 126, 132, 141);
        cBits(bitCube, current, 126, 132, 141, 111, 105, 96);
        cBits(bitCube, current, 111, 105, 96, 6, 12, 21);
        gFace(bitCube, current, 72); //Init Right
        return bitCube;
    }
    
    public static BitSet MoveRPrime(BitSet current){
        BitSet bitCube = (BitSet)current.clone();
        cBits(bitCube, current, 6, 12, 21, 111, 105, 96);
        cBits(bitCube, current, 54, 60, 69, 6, 12, 21);
        cBits(bitCube, current, 126, 132, 141, 54, 60, 69);
        cBits(bitCube, current, 111, 105, 96, 126, 132, 141);
        gFacePrime(bitCube, current, 72); //Init Right
        return bitCube;
    }
    
    public static BitSet MoveU(BitSet current){
        BitSet bitCube = (BitSet)current.clone();
        cBits(bitCube, current, 24, 27, 30, 48, 51, 54);
        cBits(bitCube, current, 48, 51, 54, 72, 75, 78);
        cBits(bitCube, current, 72, 75, 78, 96, 99, 102);
        cBits(bitCube, current, 96, 99, 102, 24, 27, 30);
        gFace(bitCube, current, 0); //Init Right
        return bitCube;
    }
    
    public static BitSet MoveUPrime(BitSet current){
        BitSet bitCube = (BitSet)current.clone();
        cBits(bitCube, current, 24, 27, 30, 96, 99, 102);
        cBits(bitCube, current, 48, 51, 54, 24, 27, 78);
        cBits(bitCube, current, 72, 75, 78, 48, 51, 54);
        cBits(bitCube, current, 96, 99, 102, 72, 75, 78);
        gFacePrime(bitCube, current, 0); //Init Right
        return bitCube;
    }
    
    public static BitSet MoveD(BitSet current){
        BitSet bitCube = (BitSet)current.clone();
        cBits(bitCube, current, 39, 42, 45, 111, 114, 117);
        cBits(bitCube, current, 63, 66, 69, 39, 42, 45);
        cBits(bitCube, current, 87, 90, 93, 63, 66, 69);
        cBits(bitCube, current, 111, 114, 117, 87, 90, 93);
        gFace(bitCube, current, 120); //Init Right
        return bitCube;
    }
    
    public static BitSet MoveDPrime(BitSet current){
        BitSet bitCube = (BitSet)current.clone();
        cBits(bitCube, current, 39, 42, 45, 63, 66, 69);
        cBits(bitCube, current, 63, 66, 69, 87, 90, 93);
        cBits(bitCube, current, 87, 90, 93, 111, 114, 117);
        cBits(bitCube, current, 111, 114, 117, 39, 42, 45);
        gFacePrime(bitCube, current, 120); //Init Right
        return bitCube;
    }
    
    public static BitSet MoveF(BitSet current){
        BitSet bitCube = (BitSet)current.clone();
        cBits(bitCube, current, 15, 18, 21, 45, 36, 30);
        cBits(bitCube, current, 72, 81, 87, 15, 18, 21);
        cBits(bitCube, current, 126, 123, 120, 72, 81, 87);
        cBits(bitCube, current, 45, 36, 30, 126, 123, 120);
        gFace(bitCube, current, 48); //Init Right
        return bitCube;
    }
    
    public static BitSet MoveFPrime(BitSet current){
        BitSet bitCube = (BitSet)current.clone();
        cBits(bitCube, current, 15, 18, 21, 72, 81, 87);
        cBits(bitCube, current, 72, 81, 87, 126, 123, 120);
        cBits(bitCube, current, 126, 123, 120, 45, 36, 30);
        cBits(bitCube, current, 45, 36, 30, 15, 18, 21);
        gFacePrime(bitCube, current, 48); //Init Right
        return bitCube;
    }
    
    public static BitSet MoveB(BitSet current){
        BitSet bitCube = (BitSet)current.clone();
        cBits(bitCube, current, 0, 3, 6, 78, 84, 93);
        cBits(bitCube, current, 78, 84, 93, 141, 138, 135);
        cBits(bitCube, current, 141, 138, 135, 39, 33, 24);
        cBits(bitCube, current, 39, 33, 24, 0, 3, 6);
        gFace(bitCube, current, 96); //Init Right
        return bitCube;
    }
    
    public static BitSet MoveBPrime(BitSet current){
        BitSet bitCube = (BitSet)current.clone();
        cBits(bitCube, current, 0, 3, 6, 39, 33, 24);
        cBits(bitCube, current, 78, 84, 93, 0, 3, 6);
        cBits(bitCube, current, 141, 138, 135, 78, 84, 93);
        cBits(bitCube, current, 39, 33, 24, 141, 138, 135);
        gFacePrime(bitCube, current, 96); //Init Right
        return bitCube;
    }
    
    public static String printRubiks (BitSet current){
        String text = "";
        for (int i = 0; i < 144; i++) {
            boolean tmp = current.get(i);
            if (tmp) text+="1 ";
            else text+="0 ";
            if (i%3==2) text+="| ";
            if (i%24==23) text+="\n";
        }
        return text;
    }
    
    
}
