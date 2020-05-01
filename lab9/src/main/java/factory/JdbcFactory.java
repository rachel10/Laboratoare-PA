package factory;

import controller.AbstractController;
import controller.AlbumController;
import controller.ArtistController;
import controller.ChartController;

public class JdbcFactory implements AbstractFactory<AbstractController> {
    public AbstractController create(String option) {
        if ("chart".equalsIgnoreCase(option)){
            return new ChartController();
        }
        else {
            if ("album".equalsIgnoreCase(option)){
                return new AlbumController();
            }else{
                if ("artist".equalsIgnoreCase(option)){
                    return new ArtistController();
                }
            }
        }
        return null;
    }
}
