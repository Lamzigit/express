package main.com.express.entity;

/**
 * Created by linzhijie on 2017/1/3.
 */
public class Express {
    private int id;

    private String FromLNG;

    private String FromLAT;

    private int Weight;

    public String getFromLAT() {
        return FromLAT;
    }

    public void setFromLAT(String fromLAT) {
        FromLAT = fromLAT;
    }

    public String getFromLNG() {
        return FromLNG;
    }

    public void setFromLNG(String fromLNG) {
        FromLNG = fromLNG;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int weight) {
        Weight = weight;
    }
}
