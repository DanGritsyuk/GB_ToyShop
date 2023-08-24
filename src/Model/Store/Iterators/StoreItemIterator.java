package Model.Store.Iterators;

import Model.Store.StoreItem;

import java.util.Iterator;
import java.util.List;

public class StoreItemIterator<E extends StoreItem> implements Iterator<E> {
    private int index;
    private List<E> items;

    public StoreItemIterator(List<E> items) {
        this.items = items;
    }

    @Override
    public boolean hasNext() {
        return items.size() > index;
    }

    @Override
    public E next() {
        return items.get(index++);
    }
}

