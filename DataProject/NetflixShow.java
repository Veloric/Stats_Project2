/**
 * @author Kyle Geddes
 */

public class NetflixShow {
    private String title;
    private int runtime;
    private double score;

    public NetflixShow(String title, int runtime, double score){
        this.title = title;
        this.runtime = runtime;
        this.score = score;
    }

    public String getTitle(){
        return this.title;
    }
    
    public int getRuntime(){
        return this.runtime;
    }

    public double getScore(){
        return this.score;
    }

    @Override
    public String toString(){
        return this.title + " Runtime: " + this.runtime + "| Score: " + this.score;
    }
    
}
