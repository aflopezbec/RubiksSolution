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
    
    
    
    public static boolean getBit(int pos,byte cube[]) {
        return (cube[pos / 8] & (1 << (pos % 8))) != 0;
    }
    
    public static void setBit(int pos, boolean b,byte cube[]) {
        byte b8 = cube[pos / 8];
        byte posBit = (byte)(1 << (pos % 8));
        if (b) {
            b8 |= posBit;
        } else {
            b8 &= (255 - posBit);
        }
        cube[pos / 8] = b8;
    }
    
    public static byte[] RandomCube(int level){
        String pathNewCube = "";
        byte cube[] = originalCube();
        for (int i = 0; i < level; i++) {
            byte indexrandom = (byte)(Math.random()*6);
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
    
    public static byte[] moveCube(Long node, byte[] cubeArray){
        
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
    
    public static byte[] moveCube(int tmp, byte[] cubeArray){
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
    
    public static byte[] originalCube( ) 
    {   //Create base cube:: UP LEFT FRONT RIGHT BACK DOWN
        // Face UP: White (000) 0:0-24
        // Face LEFT: Green (001) 24-48
        // Face FRONT: Red (010) 48-72
        // Face RIGTH: Blue (100) 72-96
        // Face BACK: ORANGE (110) 96-120
        // Face DOWN: Yellow (111) 120-144
        byte bitCube[] = new byte[18];
        //GREEEN
        for (int i = 26; i < 48; i+=3) {
            setBit(i,true,bitCube);
        }
        //RED
        for (int i = 49; i < 72; i+=3) {
            setBit(i,true,bitCube);
        }
        //BLUE
        for (int i = 72; i < 96; i+=3) {
            setBit(i,true,bitCube);
        }
        //ORANGE
        for (int i = 96; i < 120; i+=3) {
            setBit(i,true,bitCube);
            setBit(i+1,true,bitCube);
        }
        //YELLOW
        for (int i = 120; i < 144; i++) {
            setBit(i,true,bitCube);
        }
        return bitCube;
    }
    
    private static void cBits(byte[] bitCube, byte[] current,
            int x,int y,int z,int xp,int yp,int zp){
        setBit(x, getBit(xp,current),bitCube);
        setBit(x+1, getBit(xp+1,current),bitCube);
        setBit(x+2, getBit(xp+2,current),bitCube);
        setBit(y, getBit(yp,current),bitCube);
        setBit(y+1, getBit(yp+1,current),bitCube);
        setBit(y+2, getBit(yp+2,current),bitCube);
        setBit(z, getBit(zp,current),bitCube);
        setBit(z+1, getBit(zp+1,current),bitCube);
        setBit(z+2, getBit(zp+2,current),bitCube);
    }
    
    private static void gFace(byte[] bitCube, byte[] current,int x){
        //ChangeCorners
        setBit(x, getBit(x+15,current),bitCube);
        setBit(x+1, getBit(x+16,current),bitCube);
        setBit(x+2, getBit(x+17,current),bitCube);
        
        setBit(x+6, getBit(x,current),bitCube);
        setBit(x+7, getBit(x+1,current),bitCube);
        setBit(x+8, getBit(x+2,current),bitCube);
        
        setBit(x+21, getBit(x+6,current),bitCube);
        setBit(x+22, getBit(x+7,current),bitCube);
        setBit(x+23, getBit(x+8,current),bitCube);
        
        setBit(x+15, getBit(x+21,current),bitCube);
        setBit(x+16, getBit(x+22,current),bitCube);
        setBit(x+17, getBit(x+23,current),bitCube);
        
        //ChageCenters
        setBit(x+3, getBit(x+9,current),bitCube);
        setBit(x+4, getBit(x+10,current),bitCube);
        setBit(x+5, getBit(x+11,current),bitCube);
        
        setBit(x+9, getBit(x+18,current),bitCube);
        setBit(x+10, getBit(x+19,current),bitCube);
        setBit(x+11, getBit(x+20,current),bitCube);
        
        setBit(x+18, getBit(x+12,current),bitCube);
        setBit(x+19, getBit(x+13,current),bitCube);
        setBit(x+20, getBit(x+14,current),bitCube);
        
        setBit(x+12, getBit(x+3,current),bitCube);
        setBit(x+13, getBit(x+4,current),bitCube);
        setBit(x+14, getBit(x+5,current),bitCube);
        
    }
    
    private static void gFacePrime(byte[] bitCube, byte[] current,int x){
        //ChangeCorners
        setBit(x, getBit(x+6,current),bitCube);
        setBit(x+1, getBit(x+7,current),bitCube);
        setBit(x+2, getBit(x+8,current),bitCube);
        
        setBit(x+6, getBit(x+21,current),bitCube);
        setBit(x+7, getBit(x+22,current),bitCube);
        setBit(x+8, getBit(x+23,current),bitCube);
        
        setBit(x+21, getBit(x+15,current),bitCube);
        setBit(x+22, getBit(x+16,current),bitCube);
        setBit(x+23, getBit(x+17,current),bitCube);
        
        setBit(x+15, getBit(x,current),bitCube);
        setBit(x+16, getBit(x+1,current),bitCube);
        setBit(x+17, getBit(x+2,current),bitCube);
        
        //ChageCenters
        setBit(x+3, getBit(x+12,current),bitCube);
        setBit(x+4, getBit(x+13,current),bitCube);
        setBit(x+5, getBit(x+14,current),bitCube);
        
        setBit(x+9, getBit(x+3,current),bitCube);
        setBit(x+10, getBit(x+4,current),bitCube);
        setBit(x+11, getBit(x+5,current),bitCube);
        
        setBit(x+18, getBit(x+9,current),bitCube);
        setBit(x+19, getBit(x+10,current),bitCube);
        setBit(x+20, getBit(x+11,current),bitCube);
        
        setBit(x+12, getBit(x+18,current),bitCube);
        setBit(x+13, getBit(x+19,current),bitCube);
        setBit(x+14, getBit(x+20,current),bitCube);
        
    }
    
    public static byte[] MoveL(byte[] current){
        byte[] bitCube = (byte[])current.clone();
        
        cBits(bitCube, current, 0, 9, 15, 117, 108, 102);
        cBits(bitCube, current, 48, 57, 63, 0, 9, 15);
        cBits(bitCube, current, 120, 129, 135, 48, 57, 63);
        cBits(bitCube, current, 117, 108, 102, 120, 129, 135);
        gFace(bitCube, current, 24); //Girate LEFT
        return bitCube;
    }
    
    public static byte[] MoveLPrime(byte[] current){
        byte[] bitCube = (byte[])current.clone();
        
        cBits(bitCube, current, 0, 9, 15, 48, 57, 63);
        cBits(bitCube, current, 48, 57, 63, 120, 129, 135);
        cBits(bitCube, current, 120, 129, 135, 117, 108, 102);
        cBits(bitCube, current, 117, 108, 102, 0, 9, 15);
        gFacePrime(bitCube, current, 24); //Girate LEFT
        return bitCube;
    }
    
    public static byte[] MoveR(byte[] current){
        byte[] bitCube = (byte[])current.clone();
        cBits(bitCube, current, 6, 12, 21, 54, 60, 69);
        cBits(bitCube, current, 54, 60, 69, 126, 132, 141);
        cBits(bitCube, current, 126, 132, 141, 111, 105, 96);
        cBits(bitCube, current, 111, 105, 96, 6, 12, 21);
        gFace(bitCube, current, 72); //Init Right
        return bitCube;
    }
    
    public static byte[] MoveRPrime(byte[] current){
        byte[] bitCube = (byte[])current.clone();
        cBits(bitCube, current, 6, 12, 21, 111, 105, 96);
        cBits(bitCube, current, 54, 60, 69, 6, 12, 21);
        cBits(bitCube, current, 126, 132, 141, 54, 60, 69);
        cBits(bitCube, current, 111, 105, 96, 126, 132, 141);
        gFacePrime(bitCube, current, 72); //Init Right
        return bitCube;
    }
    
    public static byte[] MoveU(byte[] current){
        byte[] bitCube = (byte[])current.clone();
        cBits(bitCube, current, 24, 27, 30, 48, 51, 54);
        cBits(bitCube, current, 48, 51, 54, 72, 75, 78);
        cBits(bitCube, current, 72, 75, 78, 96, 99, 102);
        cBits(bitCube, current, 96, 99, 102, 24, 27, 30);
        gFace(bitCube, current, 0); //Init Right
        return bitCube;
    }
    
    public static byte[] MoveUPrime(byte[] current){
        byte[] bitCube = (byte[])current.clone();
        cBits(bitCube, current, 24, 27, 30, 96, 99, 102);
        cBits(bitCube, current, 48, 51, 54, 24, 27, 30);
        cBits(bitCube, current, 72, 75, 78, 48, 51, 54);
        cBits(bitCube, current, 96, 99, 102, 72, 75, 78);
        gFacePrime(bitCube, current, 0); //Init Right
        return bitCube;
    }
    
    public static byte[] MoveD(byte[] current){
        byte[] bitCube = (byte[])current.clone();
        cBits(bitCube, current, 39, 42, 45, 111, 114, 117);
        cBits(bitCube, current, 63, 66, 69, 39, 42, 45);
        cBits(bitCube, current, 87, 90, 93, 63, 66, 69);
        cBits(bitCube, current, 111, 114, 117, 87, 90, 93);
        gFace(bitCube, current, 120); //Init Right
        return bitCube;
    }
    
    public static byte[] MoveDPrime(byte[] current){
        byte[] bitCube = (byte[])current.clone();
        cBits(bitCube, current, 39, 42, 45, 63, 66, 69);
        cBits(bitCube, current, 63, 66, 69, 87, 90, 93);
        cBits(bitCube, current, 87, 90, 93, 111, 114, 117);
        cBits(bitCube, current, 111, 114, 117, 39, 42, 45);
        gFacePrime(bitCube, current, 120); //Init Right
        return bitCube;
    }
    
    public static byte[] MoveF(byte[] current){
        byte[] bitCube = (byte[])current.clone();
        cBits(bitCube, current, 15, 18, 21, 45, 36, 30);
        cBits(bitCube, current, 72, 81, 87, 15, 18, 21);
        cBits(bitCube, current, 126, 123, 120, 72, 81, 87);
        cBits(bitCube, current, 45, 36, 30, 126, 123, 120);
        gFace(bitCube, current, 48); //Init Right
        return bitCube;
    }
    
    public static byte[] MoveFPrime(byte[] current){
        byte[] bitCube = (byte[])current.clone();
        cBits(bitCube, current, 15, 18, 21, 72, 81, 87);
        cBits(bitCube, current, 72, 81, 87, 126, 123, 120);
        cBits(bitCube, current, 126, 123, 120, 45, 36, 30);
        cBits(bitCube, current, 45, 36, 30, 15, 18, 21);
        gFacePrime(bitCube, current, 48); //Init Right
        return bitCube;
    }
    
    public static byte[] MoveB(byte[] current){
        byte[] bitCube = (byte[])current.clone();
        cBits(bitCube, current, 0, 3, 6, 78, 84, 93);
        cBits(bitCube, current, 78, 84, 93, 141, 138, 135);
        cBits(bitCube, current, 141, 138, 135, 39, 33, 24);
        cBits(bitCube, current, 39, 33, 24, 0, 3, 6);
        gFace(bitCube, current, 96); //Init Right
        return bitCube;
    }
    
    public static byte[] MoveBPrime(byte[] current){
        byte[] bitCube = (byte[])current.clone();
        cBits(bitCube, current, 0, 3, 6, 39, 33, 24);
        cBits(bitCube, current, 78, 84, 93, 0, 3, 6);
        cBits(bitCube, current, 141, 138, 135, 78, 84, 93);
        cBits(bitCube, current, 39, 33, 24, 141, 138, 135);
        gFacePrime(bitCube, current, 96); //Init Right
        return bitCube;
    }
    
    public static String printRubiks (byte[] current){
        String text = "";
        for (int i = 0; i < 144; i++) {
            boolean tmp = getBit(i,current);
            if (tmp) text+="1 ";
            else text+="0 ";
            if (i%3==2) text+="| ";
            if (i%24==23) text+="\n";
        }
        return text;
    }
    
    public static int positionNode(int node, int move){
        //System.out.println("pos: "+((18*(node-1))+2+move)+" - "+move);
        return ((6*(node-1))+2+move);
    }
}
