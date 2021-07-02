package by.sergel.repository;

import java.util.List;

public interface Repository<K, V> {
    V get(K id);
    boolean add(V element);
    boolean update(V element);
    boolean remove(V element);
    List<V> query(Specification specification);
}
