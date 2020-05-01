package repo;
import entity.Album;
import entity.Chart;
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class ChartRepository extends AbstractRepository<Chart> {
    private EntityManager entityManager= PersistenceUtil.getInstance().getFactory().createEntityManager();

    public void create(Chart chart){
        entityManager.getTransaction().begin();
        entityManager.persist(chart);
        entityManager.getTransaction().commit();
    }

    public Chart findById(Integer id){
        Chart chart=entityManager.find(Chart.class,id);
        return chart;
    }

    public List<Chart> findByName(String name){
        return null;
    }
    public Chart findByAlbum(Album album){
        return (Chart) entityManager.createNamedQuery("findByAlbum").setParameter("album",album).getSingleResult();
    }

    public void closeEntityManager(){
        entityManager.close();
    }
}
