
import java.util.GregorianCalendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author AnDresLoPz
 */
public class Proceso extends Thread{

    String mensaje;
    int node;
    Proceso[] listprocess;
    byte[] problem;
    long NodesLevels;
    int level;
    byte[] original;
    int thread;
    
    public Proceso(String msg,int node,byte[] problem,long NodesLevels,int level,byte[] original,int thread)
    {   
        super(msg);
        this.mensaje=msg;
        this.node=node;
        this.problem=problem;
        this.NodesLevels=NodesLevels;
        this.level=level;
        this.original=original;
        this.thread= thread;
        
    }
     
    public void run()
    {
        Graph_A g = new Graph_A();
        //System.out.println(mensaje+"-"+"-"+node+"|"+Tools.positionNode(1, node)+"|"+level);
        RubiksCube_A tmp = g.AStar(Tools.positionNode(1, node), Tools.moveCube(node, problem), NodesLevels, level, original,(byte)0);
        
        if (tmp==null){
            System.out.println("Solucion en "+mensaje);
            for (int i = 0; i<6;i++) {
                if (i != thread){
                    listprocess[i].stop();
                    //System.out.println("stop");
                }
            }
            System.out.println("Iniciio: "+ new GregorianCalendar().getTime());
        }else{
            System.out.println("fin "+mensaje);
            System.out.println("Iniciio: "+ new GregorianCalendar().getTime());
        }       
    }

    
    public void setArrayProcess(Proceso[] process)
    {
        this.listprocess = process;
    }
 
}
