import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Generator generator = new Generator();
        ArtistController artistController = new ArtistController();

        Artist artist = generator.generateArtist();
        //   artistController.create(artist.getName(), artist.getCountry());

//        List<Artist> artistList=artistController.getAllArtists();
//        System.out.println("Artists:");
//        for (Artist artist1:artistList){
//            System.out.println(artist1);
//        }


        Album album = generator.generateAlbum();
        AlbumController albumController = new AlbumController();
        //     albumController.create(album.getName(),album.getArtistId(),album.getReleaseYear());

//        List<Album> albumList = albumController.getAllAlbums();
//        System.out.println("Albums:");
//        for (Album album1:albumList){
//            System.out.println(album1);
//        }

        Chart chart = generator.generateChart();
        ChartController chartController = new ChartController();
        chartController.create(chart.getIdAlbum(), chart.getVotes());
        //chartController.updateVotes(2,99);
        List<Chart> chartList = chartController.getAllCharts();
        System.out.println("Charts:");
        for (Chart chart1 : chartList) {
            System.out.println(chart1);
        }


        System.out.println("Ranking");
        artistController.displayRanking();

        Database.closeConnection();

    }
}
