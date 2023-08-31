package Model;

import Model.Store.StoreItem;

import java.util.List;

public interface ShopService {
    String getShopName();

    void addItem(String name, int count, int chance);

    void updateItem(int id, String name, int count, int weight);
    void deleteItem(int id);
    boolean save();
    boolean load();

    String getPrize();

    List<String> getItemsInfo();
}
