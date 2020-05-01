package repo;

import entity.Album;
import entity.Artist;

import java.util.List;

public abstract class AbstractRepository<T> {
    public abstract void create(T object);
    public abstract T findById(Integer id);

    public abstract List<T> findByName(String name);

    public abstract void closeEntityManager();
}
