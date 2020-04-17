package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {
    private static PersistenceUtil instance=null;
    private static EntityManagerFactory factory=null;

    private PersistenceUtil(){}

    public static PersistenceUtil getInstance(){
        if(instance==null){
            instance=new PersistenceUtil();
        }
        return instance;
    }

    public EntityManagerFactory getFactory(){
        if (factory==null){
            factory= Persistence.createEntityManagerFactory("MusicAlbumsPU");
        }
        return factory;
    }

    public void closeFactory(){
        if (factory!=null){
            factory.close();
        }
    }

}
