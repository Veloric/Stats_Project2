/**
 * @author Kyle Geddes
 */

public class NetflixShow {
    private int id;
    private int runtime;
    private double score;

    public NetflixShow(int id, int runtime, double score){
        this.id = id;
        this.runtime = runtime;
        this.score = score;
    }

    public int getid(){
        return this.id;
    }
    
    public int getRuntime(){
        return this.runtime;
    }

    public double getScore(){
        return this.score;
    }

    @Override
    public String toString(){
        return this.id + " Runtime: " + this.runtime + "| Score: " + this.score;
    }
    
}
