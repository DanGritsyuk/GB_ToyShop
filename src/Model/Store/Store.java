package Model.Store;

import Model.Store.Comporators.StoreItemComparatorById;
import Model.Store.Iterators.StoreItemIterator;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Store<T extends StoreItem> implements Serializable, Iterable<T>{

    private String name;
    private List<T> items;

    public Store(String name){
        this.name = name;
        this.items = new LinkedList<>();
    }

    public String getName(){
        return this.name;
    }

    public List<T> getItems(){
        return this.items;
    }
    public T getItemById(int id) {
        for (T item : this.items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public boolean addStoreItem(T item){
        return this.items.add(item);
    }

    public boolean deleteStoreItem(int id) {
        var item = getItemById(id);
        return this.items.remove(item);
    }

    public void sortById() {
        Collections.sort(this.items, new StoreItemComparatorById<>());
    }

    @Override
    public Iterator<T> iterator() {
        return new StoreItemIterator<>(items);
    }
}
