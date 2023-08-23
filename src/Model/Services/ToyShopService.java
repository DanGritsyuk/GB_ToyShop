package Model.Services;

import Model.ShopService;
import Model.ToyStore.Store;
import Model.ToyStore.StoreItem;

public class ToyShopService implements ShopService {
    private Store<StoreItem> store;
    public ToyShopService(){
        store = new Store<>();
    }
}
