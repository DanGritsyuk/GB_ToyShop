package Model;
import View.View;

public class Presenter {
    private ShopService shop;
    private View view;

    public Presenter(ShopService shop, View view){
        this.shop = shop;
        this.view = view;
    }
}
