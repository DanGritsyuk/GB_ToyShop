package Model.Store;

import Model.Store.Iterators.StoreItemIterator;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Store<T extends StoreItem> implements Serializable, Iterable<StoreItem>{
    private List<StoreItem> items;

    public Store(){
        this.items = new LinkedList<>();
    }

    public StoreItem getItemById(int id) {
        for (StoreItem item : this.items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public boolean addStoreItem(StoreItem item){
        return this.items.add(item);
    }

    public boolean deleteStoreItem(int id) {
        var item = getItemById(id);
        return this.items.remove(item);
    }

    @Override
    public Iterator<StoreItem> iterator() {
        return new StoreItemIterator<>(items);
    }
}
