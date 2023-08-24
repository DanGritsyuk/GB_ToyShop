package Model.Store;

import Model.Store.Iterators.StoreItemIterator;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

public class Store<T extends StoreItem> implements Serializable, Iterable<StoreItem>{
    private List<StoreItem> storeItems;
    @Override
    public Iterator<StoreItem> iterator() {
        return new StoreItemIterator<>(storeItems);
    }
}
