
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

//bits total 54*3 == 162
//bits without centers 48*3 = 144

public class RubiksCube_A implements Comparable<RubiksCube_A>{
    
    private byte[] bitCube = new byte[18];
    private long nameNode;
    private int cost;
    
    public RubiksCube_A ( ) 
    {   //Create base cube:: UP LEFT FRONT RIGHT BACK DOWN
        // Face UP: White (000) 0:0-24
        // Face LEFT: Green (001) 24-48
        // Face FRONT: Red (010) 48-72
        // Face RIGTH: Blue (100) 72-96
        // Face BACK: ORANGE (110) 96-120
        // Face DOWN: Yellow (111) 120-144
        
        //GREEEN
        for (int i = 26; i < 48; i+=3) {
            Tools.setBit(i,true,bitCube);
        }
        //RED
        for (int i = 49; i < 72; i+=3) {
            Tools.setBit(i,true,bitCube);
        }
        //BLUE
        for (int i = 72; i < 96; i+=3) {
            Tools.setBit(i,true,bitCube);
        }
        //ORANGE
        for (int i = 96; i < 120; i+=3) {
            Tools.setBit(i,true,bitCube);
            Tools.setBit(i+1,true,bitCube);
        }
        //YELLOW
        for (int i = 120; i < 144; i++) {
            Tools.setBit(i,true,bitCube);
        }
        
    }
    
    public RubiksCube_A( byte[] bitCube, long nameNode) 
    {
        this.bitCube = bitCube;
        this.nameNode = nameNode;
    }
    
    public String printRubiks (){
        String text = "";
        for (int i = 0; i < 144; i++) {
            boolean tmp = Tools.getBit(i, getBitCube());
            if (tmp) text+="1 ";
            else text+="0 ";
            if (i%3==2) text+="| ";
            if (i%24==23) text+="\n";
        }
        return text;
    }
    
//    public static void main (String []args){
//        RubiksCube_A cube = new RubiksCube_A();
//        System.out.println(Tools.printRubiks(cube.getBitCube()));
//        System.out.println(Tools.printRubiks(Tools.MoveL(cube.getBitCube())));
//    }

    /**
     * @return the bitCube
     */
    public byte[] getBitCube() {
        return bitCube;
    }

    /**
     * @param bitCube the bitCube to set
     */
    public void setBitCube(byte[] bitCube) {
        this.bitCube = bitCube;
    }

    /**
     * @return the nameNode
     */
    public long getNameNode() {
        return nameNode;
    }

    /**
     * @param nameNode the nameNode to set
     */
    public void setNameNode(long nameNode) {
        this.nameNode = nameNode;
    }

    /**
     * @return the cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public int compareTo(RubiksCube_A o) {
        if (this.getCost() < o.getCost()) return -1;
        if (this.getCost() > o.getCost()) return 1;
        return 0;
    }
}
