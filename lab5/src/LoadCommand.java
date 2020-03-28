import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class LoadCommand extends Command {

    public LoadCommand() {
        super("LoadCommand");
    }

    @Override
    public  Catalog solver(String path) throws InvalidCatalogException {
        Catalog catalog;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path))) {
            catalog = (Catalog) inputStream.readObject();
        } catch (Exception exception) {
            throw new InvalidCatalogException(exception);
        }
        return catalog;
    }

}
