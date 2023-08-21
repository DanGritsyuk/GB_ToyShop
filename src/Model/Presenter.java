package Model;
import Model.Services.ToyShopService;
import View.View;

public class Presenter {
    private ShopService shop;
    private View view;

    public Presenter(View view){
        this.shop = new ToyShopService();
        this.view = view;
    }
}
