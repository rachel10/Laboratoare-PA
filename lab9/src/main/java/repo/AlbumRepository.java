package repo;

import entity.Album;
import entity.Artist;
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class AlbumRepository {
   private EntityManager entityManager=PersistenceUtil.getInstance().getFactory().createEntityManager();

   public void create(Album album){
       entityManager.getTransaction().begin();
       entityManager.persist(album);
       entityManager.getTransaction().commit();
   }

    public Album findById(Integer id){
        Album album=entityManager.find(Album.class,id);
        return album;
    }

    public List<Album> findByName(String name){
        return entityManager.createNamedQuery("findAlbumByName").setParameter("name",name).getResultList();
    }

    public List<Album> findByArtist(Artist artist){
        return entityManager.createNamedQuery("findByArtist").setParameter("artist",artist).getResultList();
    }

    public void closeEntityManager(){
       entityManager.close();
    }
}
