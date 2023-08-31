package Model.Store.Comporators;

import java.util.Comparator;

import Model.Store.StoreItem;

public class StoreItemComparatorById<E extends StoreItem> implements Comparator<E> {

    @Override
    public int compare(E o1, E o2) {
        return o1.getId() - o2.getId();
    }
}