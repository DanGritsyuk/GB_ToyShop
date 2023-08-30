package Model.Store.StoreItems;

import Model.ChanceMaker.LotteryItem;
import Model.Store.StoreItem;

public class Toy implements StoreItem, LotteryItem {

    private String name;
    private int id;
    private int count;
    private int chance;

    public Toy(String name, int id, int count, int chance){
        this.name = name;
        this.id = id;
        this.count = count;
        this.chance = chance;
    }

    public String getName(){
        return this.name;
    }

    public int getCount(){
        return this.count;
    }

    @Override
    public int getId() {
        return this.id;
    }
    @Override
    public int getChance() {
        return this.chance;
    }
}
