package Model.Services;

import Model.ShopService;
import Model.ChanceMaker.ChanceMaker;
import Model.ChanceMaker.RafflePrizes;
import Model.Store.Store;
import Model.Store.StoreItem;

import javax.crypto.BadPaddingException;

public class ToyShopService implements ShopService {
    private Store<StoreItem> store;
    private ChanceMaker raffleToy;
    public ToyShopService(){
        this.store = new Store<>();
        this.raffleToy = new RafflePrizes();
    }

    public StoreItem getItemById(int id){
        return this.store.getItemById(id);
    }

    public void addToy(){
        boolean result = this.store.addStoreItem(null);
        if (!result) {
            throw new RuntimeException("Не удалось добавить игрушку.");
        }
    }
    public void deleteToy(int id){
        boolean result = this.store.deleteStoreItem(id);
        if (!result) {
            throw new RuntimeException("Не удалось удалить игрушку.");
        }
    }
    public void updateStoreItem(StoreItem item){
        String errorMess = "Не удалось внести изменения.";
        var oldItem = this.store.getItemById(item.getId());
        if (oldItem == null){
            throw new RuntimeException(errorMess + " Не найдена заданная игрушка");
        }
        if (this.store.deleteStoreItem(oldItem.getId())){
            if (!this.store.addStoreItem(item)){
                throw new RuntimeException(errorMess + " Новые данные не сохранены.");
            }
        }
        else {
            throw new RuntimeException(errorMess + " Некорректное удаление старого экзмепляра.");
        }
    }
}