package Model.Services;

import Model.ShopService;
import Model.Store;
import Model.ToyStore.ToyStore;

public class ToyShopService implements ShopService {
    private Store store;
    public ToyShopService(){
        store = new ToyStore();
    }
}
