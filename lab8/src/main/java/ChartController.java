import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChartController {
    private Connection connection;

    public ChartController() {
        connection=Database.getConnection();
    }

    public void create(int id_album, int votes) {

        String sql = "INSERT charts (id_album,votes) VALUES (?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id_album);
            statement.setInt(2, votes);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Chart> getAllCharts() {
        List<Chart> chartList = new ArrayList();

        String sql = "SELECT * FROM charts";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Chart chart = new Chart();
                chart.setIdAlbum(resultSet.getInt("id_album"));
                chart.setVotes(resultSet.getInt("votes"));
                chartList.add(chart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chartList;
    }

    public void updateVotes(int id_album,int votes){
        String sql="UPDATE charts SET votes=? WHERE id_album=?";
        try{
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setInt(1,votes);
            statement.setInt(2,id_album);
            statement.executeUpdate();
            connection.commit();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
