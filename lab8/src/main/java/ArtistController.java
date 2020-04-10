import java.sql.*;

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
        ;
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
}