package Model.Services;

import Model.FileHandler.FileHandler;
import Model.IdGenerator.IdGenerator;
import Model.ShopService;
import Model.ChanceMaker.ChanceMaker;
import Model.ChanceMaker.RafflePrizes;
import Model.Store.Store;
import Model.Store.StoreItems.Toy;

import java.util.LinkedList;
import java.util.List;

public class ToyShopService implements ShopService {
    private final String DATA_FOLDER = "Data\\";
    private final String FILE_STORE = "toy_store.data";
    private final String ID_GENERATOR_FILE_NAME = "id_gen.data";
    private Store<Toy> store;
    private final ChanceMaker<Toy> raffleToy;
    private IdGenerator idGenerator;
    private final FileHandler<Store<Toy>> storeFileHandler;
    private final FileHandler<IdGenerator> idsFileHandler;

    public ToyShopService(){
        this.store = new Store<>("Магазин игрушек");
        this.raffleToy = new RafflePrizes<>();
        this.idGenerator = IdGenerator.getIdGenerator();
        this.storeFileHandler = new FileHandler<>();
        this.idsFileHandler = new FileHandler<>();
    }

    public Toy raffleOff(){
        updateLotteryChance();
        if (this.raffleToy.startPrizeDraw()) {
            Toy toy = this.raffleToy.getPrize();
            if (toy != null){
                return toy;
            }
            else{
                throw new RuntimeException("Приз был утерян.");
            }
        }
        else {
            return null;
        }
    }

    public boolean saveShop() {
        return this.idsFileHandler.save(idGenerator, DATA_FOLDER + ID_GENERATOR_FILE_NAME)
                && storeFileHandler.save(this.store,
                DATA_FOLDER + FILE_STORE);
    }

    public boolean loadShop() {
        var store = storeFileHandler.read(DATA_FOLDER + FILE_STORE);
        var idGenerator = idsFileHandler.read(DATA_FOLDER + ID_GENERATOR_FILE_NAME);
        if (store != null && idGenerator != null) {
            this.store = store;
            this.idGenerator = idGenerator;
            return true;
        }
        return false;
    }

    @Override
    public String getShopName() {
        return store.getName();
    }

    @Override
    public List<String> getItemsInfo() {
        List<String> list = new LinkedList<>();
        this.store.sortById();
        for(Toy toy : this.store){
            list.add(toy.getId() + " - " + toy.getName() + "; На складе: " + toy.getCount() + "; шанс выпадения: " + toy.getChance());
        }
        return list;
    }

    @Override
    public void addItem(String name, int count, int chance) {
        boolean result = this.store.addStoreItem(new Toy(name, idGenerator.GetNewId(), count, chance));
        if (!result) {
            throw new RuntimeException("Не удалось добавить игрушку.");
        }
    }

    @Override
    public void updateItem(int id, String name, int count, int weight){
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
    @Override
    public void deleteItem(int id){
        var toy = this.store.getItemById(id);
        this.raffleToy.deleteLotteryItem(toy);
        boolean result = this.store.deleteStoreItem(id);
        if (!result) {
            throw new RuntimeException("Не удалось удалить игрушку.");
        }
    }

    @Override
    public boolean save(){
        return saveShop();
    }

    @Override
    public boolean load(){
        return loadShop();
    }

    @Override
    public String getPrize(){
        String text = "";
        var lottery = true;
        while (lottery) {
            try {
                Toy toy = raffleOff();
                if (toy == null) {return "В магазине нет игрушек!"; }
                this.raffleToy.deleteLotteryItem(toy);
                updateItem(toy.getId(), toy.getName(), toy.getCount() - 1, toy.getChance());
                toy = this.store.getItemById(toy.getId());
                if (toy.getCount() > 0) { this.raffleToy.setLotteryItem(toy); }
                text = "Поздравляем! Вы выиграли " + toy.getName() + "! Можете забрать приз.";
                lottery = false;
            }
            catch (Exception ex){
                text = ex.getMessage();
                lottery = false;
            }
        }
        return text;
    }

    private void updateLotteryChance(){
        for(var toy : this.store.getItems()) {
            if(toy.getCount() > 0){
                this.raffleToy.setLotteryItem(toy);
            }
        }
    }
}