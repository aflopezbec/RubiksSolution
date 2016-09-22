
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

public class RubiksCube_A {
    
    private BitSet bitCube = new BitSet();
    private int nameNode;
    
    public RubiksCube_A( ) 
    {   //Create base cube:: UP LEFT FRONT RIGHT BACK DOWN
        // Face UP: White (000) 0:0-24
        // Face LEFT: Green (001) 24-48
        // Face FRONT: Red (010) 48-72
        // Face RIGTH: Blue (100) 72-96
        // Face BACK: ORANGE (110) 96-120
        // Face DOWN: Yellow (111) 120-144
        
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
        
    }
    
    public RubiksCube_A( BitSet bitCube, int nameNode ) 
    {
        this.bitCube = bitCube;
        this.nameNode = nameNode;
    }
    
    public String printRubiks (){
        String text = "";
        for (int i = 0; i < 144; i++) {
            boolean tmp = getBitCube().get(i);
            if (tmp) text+="1 ";
            else text+="0 ";
            if (i%3==2) text+="| ";
            if (i%24==23) text+="\n";
        }
        return text;
    }
    
    public static void main (String []args){
        RubiksCube_A cube = new RubiksCube_A();
        System.out.println(Tools.printRubiks(cube.getBitCube()));
        System.out.println(Tools.printRubiks(Tools.MoveL(cube.getBitCube())));
    }

    /**
     * @return the bitCube
     */
    public BitSet getBitCube() {
        return bitCube;
    }

    /**
     * @param bitCube the bitCube to set
     */
    public void setBitCube(BitSet bitCube) {
        this.bitCube = bitCube;
    }

    /**
     * @return the nameNode
     */
    public int getNameNode() {
        return nameNode;
    }

    /**
     * @param nameNode the nameNode to set
     */
    public void setNameNode(int nameNode) {
        this.nameNode = nameNode;
    }
}
