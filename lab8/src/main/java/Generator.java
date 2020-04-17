import com.github.javafaker.Faker;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Generator {
    private Faker faker;

    public Generator() {
        faker=new Faker();
    }

    public Artist generateArtist(){
        Artist artist=new Artist();
        artist.setName(faker.name().fullName());
        artist.setCountry(faker.country().name());
        return artist;
    }

    public Album generateAlbum(){
        Album album=new Album();
        List<Integer> ids=new ArrayList();
        album.setName(faker.book().title());
        album.setReleaseYear(faker.number().numberBetween(1900,2020));

        Connection connection=Database.getConnection();
        String sql = "SELECT id FROM artists";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                ids.add(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        album.setArtistId(ids.get(faker.number().numberBetween(0,ids.size()-1)));

        return album;
    }

    public Chart generateChart(){
        Chart chart=new Chart();
        List<Integer> ids=new ArrayList();

        Connection connection=Database.getConnection();
        String sql = "SELECT id FROM albums";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                ids.add(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        chart.setIdAlbum(ids.get(faker.number().numberBetween(0,ids.size()-1)));

        chart.setVotes(faker.number().numberBetween(0,100));

        return chart;
    }

}
