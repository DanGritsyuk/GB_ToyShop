package Model.Store.StoreItems;

import Model.ChanceMaker.LotteryItem;
import Model.Store.StoreItem;

public class Toy implements StoreItem, LotteryItem {

    private String name;
    private int id;
    private int count;
    private int weight;

    public Toy(String name, int id, int count, int weight){
        this.name = name;
        this.id = id;
        this.count = count;
        this.weight = weight;
    }

    @Override
    public int getId() {
        return this.id;
    }
    @Override
    public int getWeight() {
        return this.weight;
    }
}
