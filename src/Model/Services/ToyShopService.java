package Model.Services;

import Model.IdGenerator.IdGenerator;
import Model.ShopService;
import Model.ChanceMaker.ChanceMaker;
import Model.ChanceMaker.RafflePrizes;
import Model.Store.Store;
import Model.Store.StoreItem;
import Model.Store.StoreItems.Toy;

public class ToyShopService implements ShopService {
    private Store<StoreItem> store;
    private ChanceMaker raffleToy;
    private IdGenerator idGenerator;
    public ToyShopService(){
        this.store = new Store<>();
        this.raffleToy = new RafflePrizes();
        this.idGenerator = IdGenerator.getIdGenerator();
    }

    public StoreItem getItemById(int id){
        return this.store.getItemById(id);
    }

    public void addToy(String name, int count, int weight){
        boolean result = this.store.addStoreItem(new Toy(name, idGenerator.GetNewId(), count, weight));
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
    public void updateStoreItem(int id, String name, int count, int weight){
        String errorMess = "Не удалось внести изменения.";
        var oldItem = this.store.getItemById(id);
        if (oldItem == null){
            throw new RuntimeException(errorMess + " Не найдена заданная игрушка");
        }
        if (this.store.deleteStoreItem(oldItem.getId())){

            if (!this.store.addStoreItem(new Toy(name, id, count, weight))){
                throw new RuntimeException(errorMess + " Новые данные не сохранены.");
            }
        }
        else {
            throw new RuntimeException(errorMess + " Некорректное удаление старого экзмепляра.");
        }
    }
}