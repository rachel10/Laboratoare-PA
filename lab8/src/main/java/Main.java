import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        ArtistController artistController=new ArtistController();
        artistController.create("Ludwig van Beethoven", "Germania");

        System.out.println(artistController.findByName("Ludwig van Beethoven"));


        AlbumController albumController=new AlbumController();
        albumController.create("Moonlight",1,1997);
        albumController.create("The Best of Beethoven",1,1984);


        List<Album> albumList=albumController.findByArtist(1);

        for (Album album:albumList){
            System.out.println(album);
        }

        Database.closeConnection();

    }
}
