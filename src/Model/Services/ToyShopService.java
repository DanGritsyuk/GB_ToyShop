package Model.Services;

import Model.ShopService;
import Model.Store.ChanceMaker.ChanceMaker;
import Model.Store.ChanceMaker.RafflePrizes;
import Model.Store.Store;
import Model.Store.StoreItem;

public class ToyShopService implements ShopService {
    private Store<StoreItem> store;
    private ChanceMaker raffleToy;
    public ToyShopService(){
        this.store = new Store<>();
        this.raffleToy = new RafflePrizes();
    }
}