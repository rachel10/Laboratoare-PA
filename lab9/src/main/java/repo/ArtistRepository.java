package repo;

import entity.Artist;
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class ArtistRepository {
    private EntityManager entityManager= PersistenceUtil.getInstance().getFactory().createEntityManager();

    public void create(Artist artist){
        entityManager.getTransaction().begin();
        entityManager.persist(artist);
        entityManager.getTransaction().commit();
    }

    public Artist findById(Integer id){
        Artist artist=entityManager.find(Artist.class,id);
        return artist;
    }

    public List<Artist> findByName(String name){
        return entityManager.createNamedQuery("findByName").setParameter("name",name).getResultList();
    }

    public void closeEntityManager(){
        entityManager.close();
    }
}
