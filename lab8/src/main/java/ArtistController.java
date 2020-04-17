import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistController {
    private Connection connection;

    public ArtistController() {
        connection = Database.getConnection();
    }

    public void create(String name, String country) {

        String sql = "INSERT artists (name, country) VALUES (?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, country);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Artist findByName(String name) {
        String sql = "SELECT id,country FROM artists WHERE UPPER(name)=?";
        Artist artist = new Artist();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name.toUpperCase());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                artist.setId(resultSet.getInt("id"));
                artist.setCountry(resultSet.getString("country"));
                artist.setName(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return artist;
    }

    public List<Artist> getAllArtists() {
        String sql = "SELECT * FROM artists";
        Artist artist;
        List<Artist> artists = new ArrayList();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                artist=new Artist();
                artist.setId(resultSet.getInt("id"));
                artist.setCountry(resultSet.getString("country"));
                artist.setName(resultSet.getString("name"));

                artists.add(artist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return artists;
    }

    public void displayRanking(){
        String sql="SELECT artist_id,SUM(votes) FROM charts RIGHT OUTER JOIN albums ON (charts.id_album=albums.id) GROUP BY artist_id ORDER BY 2 DESC";
        String sql1="SELECT name FROM artists WHERE id=?";
        try {
            PreparedStatement statement=connection.prepareStatement(sql);
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next()) {

                PreparedStatement statementForName = connection.prepareStatement(sql1);
                statementForName.setInt(1, resultSet.getInt(1));
                ResultSet resultSetForName = statementForName.executeQuery();
                while (resultSetForName.next()) {
                    System.out.println("Artist: id: " +resultSet.getInt(1)+", name: "+ resultSetForName.getString(1) + ", votes: " + resultSet.getInt(2));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}