package factory;

import repo.AbstractRepository;
import repo.AlbumRepository;
import repo.ArtistRepository;
import repo.ChartRepository;

public class JpaFactory implements AbstractFactory<AbstractRepository>{
    public AbstractRepository create(String option) {
        if("chart".equalsIgnoreCase(option)){
            return new ChartRepository();
        }
        else {
            if ("album".equalsIgnoreCase(option)){
                return new AlbumRepository();
            }
            else {
                if("artist".equalsIgnoreCase(option)){
                    return new ArtistRepository();
                }
            }
        }
        return null;
    }
}
