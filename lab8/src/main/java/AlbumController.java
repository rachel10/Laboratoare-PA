import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlbumController {
    private Connection connection;

    public AlbumController() {
        connection = Database.getConnection();
    }

    public void create(String name, int artistId, int releaseYear) {

        String sql = "INSERT albums (name, artist_id, release_year) VALUES (?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, artistId);
            statement.setInt(3, releaseYear);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Album> findByArtist(int artistId) {
        List<Album> albumList = new ArrayList();

        String sql = "SELECT id,name,release_year FROM albums WHERE artist_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, artistId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Album album = new Album();
                album.setId(resultSet.getInt("id"));
                album.setArtistId(artistId);
                album.setName(resultSet.getString("name"));
                album.setReleaseYear(resultSet.getInt("release_year"));

                albumList.add(album);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return albumList;
    }

    public List<Album> getAllAlbums() {
        List<Album> albumList = new ArrayList();

        String sql = "SELECT * FROM albums";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Album album = new Album();
                album.setId(resultSet.getInt("id"));
                album.setArtistId(resultSet.getInt("artist_id"));
                album.setName(resultSet.getString("name"));
                album.setReleaseYear(resultSet.getInt("release_year"));

                albumList.add(album);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return albumList;
    }
}
