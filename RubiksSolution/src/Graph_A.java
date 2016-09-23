
import java.util.Arrays;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AnDresLoPz
 */
public class Graph_A {
    
    public void BFS (long position, byte[] problem, long NodesLevels,byte[] original){
        System.out.println("BFS IN");
        Queue<byte[]> quedeBFS = new LinkedList<>();
        //Runtime garbage = Runtime.getRuntime();
        //problem.printArrayCube();
        quedeBFS.add(problem);
        long iter = 2;
        long cursor = 0;
        //cursor<=NodesLevels &&
        System.out.println("i:"+iter+" pos: "+position);
        byte[] rc;
        while( cursor < NodesLevels && !quedeBFS.isEmpty()){
            rc = quedeBFS.remove();            
            //garbage.gc();
            cursor++;
            if(Arrays.equals(original, rc)){                
                System.out.println("----------------------------------------");
                System.out.println("Solucion en el nodo: "+cursor);
                System.out.println("Solución: "+path(cursor));
                System.out.println("----------------------------------------");
                break;
            }            
            if(iter < NodesLevels ){
                int coun = 0;
                for (int i = 0; i < 6; i++) {
                    coun++;
                //System.out.println("i: "+iter); 
                quedeBFS.add(Tools.moveCube(i,rc));
                iter++;
                }                              
            }
        }
        System.out.println("c: "+cursor);
    }
    
   public boolean DFS (long position, byte[] problem, long NodesLevels, int level,byte[] original){
        
        Stack stack = new Stack();
        //System.out.print("problem: ");
        //tools.printArrayCube(problem);
        stack.push(problem);
        
        byte[] tmp;
        
        long cursor = 0;
        int countlevel = 0;
        int padre = 1;
        byte hijos = 0;
        int positionmove = 1;
        
        while (cursor < NodesLevels && !stack.isEmpty()){
            tmp = (byte[]) stack.pop();
            //tools.printArrayCube(tmp);
            if(Arrays.equals(original, tmp)){
                System.out.println("----------------------------------------");
                System.out.println("Solucion en el nodo: "+cursor);
                System.out.println("----------------------------------------");
                System.out.println("Solución: "+path(positionmove));
                System.out.println("----------------------------------------");
                return true;
            }
            //System.out.println(countlevel);
            if(countlevel < level){
                countlevel++;
                for (int i = 5; i >= 0; i--) {
                    //System.out.println("i: "+i);
                    stack.push(Tools.moveCube(i,tmp));
                }
                padre=positionmove;
                positionmove = positionNode(padre, 0);                
                //System.out.println("hijo: "+positionmove+" padre: "+padre+" -- "+stack.size());
                
            }
            else if (hijos==5){
                countlevel--;
                hijos=0;
                while (positionNode(fatherNode(padre), 5) == padre){
                    //System.out.println("p:: "+padre+" - "+fatherNode(padre)+"-"+positionNode(fatherNode(padre), 17) );
                    padre = fatherNode(padre);
                    countlevel--;
                }                
                padre =padre+1;
                positionmove=padre;
                //System.out.println("new padre "+fatherNode(padre));
                
            }
            else{
                hijos++;
                positionmove = positionNode(padre, hijos);
                //System.out.println("hijo: "+positionmove+" padre: "+padre);
            }
            cursor++;            
        }
        return false;
        //System.out.println("CO: "+cursor);
    }
        
    public void DFSIterative (long position, byte[] problem, long NodesLevels, int maxlevel,byte[] original){
        for (int i = 1; i <= maxlevel; i++) {
            long TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
            TInicio = System.currentTimeMillis(); 
            if (DFS(position, problem, NodesLevels, i,original)){
                TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
                tiempo = TFin - TInicio; //Calculamos los milisegundos de diferencia
                System.out.println("DFS Iterativo nivel "+i+" : " + tiempo+" ms");
                break;
            }
            TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
            tiempo = TFin - TInicio; //Calculamos los milisegundos de diferencia
            System.out.println("DFS Iterativo nivel "+i+" : " + tiempo+" ms");
        }
    }
    
    public void AStar (long position, byte[] problem, long NodesLevels, int level,byte[] original){
        Stack stack = new Stack();
        stack.push(new RubiksCube_A(problem,1));
        System.out.println("Cubo a resolver");
        System.out.println(Tools.printRubiks(problem));
        System.out.println("");
        RubiksCube_A tmp = null;
        
        long cursor = 0;
        int countlevel = 0;
        int padre = 0;
        
        boolean flag=true;        
        
        while (cursor < NodesLevels && !stack.isEmpty()){
            tmp = (RubiksCube_A) stack.pop();         
            
            flag=true;
            if(Arrays.equals(original, tmp.getBitCube())){
                System.out.println("----------------------------------------");
                System.out.println("Solucion en el nodo: "+tmp.getNameNode());
                System.out.println("----------------------------------------");
                System.out.println("Solución: "+path(tmp.getNameNode()));
                System.out.println("----------------------------------------");
                break;
            }
            
            if(countlevel < level ){
                 countlevel++;
                padre = tmp.getNameNode();
                for (int i = 5; i >= 0; i--) {
                    stack.push(new RubiksCube_A(Tools.moveCube(i,tmp.getBitCube()),positionNode(padre, i)));
                }                
            }
            
            else if (positionNode(fatherNode(tmp.getNameNode()), 5)==tmp.getNameNode()){
                countlevel--;
                padre = fatherNode(tmp.getNameNode());
                while (positionNode(fatherNode(padre), 5) == padre){
                    padre = fatherNode(padre);
                    countlevel--;
                }
            }
            cursor++;            
        }
        System.out.println("c: "+cursor+" level:"+countlevel+" tmp:"+tmp.getNameNode());
    }
    
    public int positionNode(int node, int move){
        //System.out.println("pos: "+((18*(node-1))+2+move)+" - "+move);
        return ((6*(node-1))+2+move);
    }
    
    public int fatherNode(int node){
        //System.out.println("pos: "+((18*(node-1))+2+move)+" - "+move);
        int module = node%6;
        int tmp = 0;
        if (module == 0) tmp=4;
        else if (module == 1) tmp=5;
        else tmp = module-2;
        
        return ((node -2 -tmp)/6)+1;
    }
    
    public String path(long numberNode){
        String pathNode = Long.toString(numberNode)+" ";
        while (numberNode>1){
            int tmp = numberMove(numberNode);            
            long tmpvalue = ((numberNode -2 -tmp)/6)+1;
            numberNode = tmpvalue;
            pathNode = Long.toString(tmpvalue)+":"+nameMove((int)tmp)+pathNode;           
        };
        return pathNode;
    }
    
    public int numberMove(long number){
        if (number == 1) return 19;
        long module = number%6;
        int tmp = 0;
        if (module == 0) tmp=4;
        else if (module == 1) tmp=5;
        else tmp = (int)module-2;
        return tmp;
    }
    
    public String nameMove(int numberMove){
        switch (numberMove){
                case 0: return "L ";                
                case 1: return "U "; 
                case 2: return "B ";                
                case 3: return "D ";
                case 4: return "F ";
                case 5: return "R ";
            }
        return null;
    }
    
    public boolean contraryMove(int father, int son){
        switch(father){
            case 0: return son==1;
            case 1: return son==0;
            case 2: return son==3;
            case 3: return son==2;
            case 4: return son==5;
            case 5: return son==4;
            case 6: return son==7;
            case 7: return son==6;
            case 8: return son==9;
            case 9: return son==8;
            case 10: return son==11;
            case 11: return son==10;
            case 12: return son==13;
            case 13: return son==12;
            case 14: return son==15;
            case 15: return son==14;
            case 16: return son==17;
            case 17: return son==16; 
        }
        return false;
    }
    
}
