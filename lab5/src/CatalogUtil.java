import java.awt.*;
import java.io.*;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CatalogUtil {
    public static void save(Catalog catalog) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(catalog.getPath()))) {
            oos.writeObject(catalog);
        }
    }

    public static Catalog load(String path) throws InvalidCatalogException {
        Catalog catalog;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path))) {
            catalog = (Catalog) inputStream.readObject();
        } catch (Exception exception) {
            throw new InvalidCatalogException(exception);
        }
        return catalog;
    }

    public static void view(Document document) throws InvalidDocumentException{
        Desktop desktop=Desktop.getDesktop();

        try {
            if(document.getLocation().startsWith("http://") || document.getLocation().startsWith("https://")){
                URI uri= new URI(document.getLocation());
                desktop.browse(uri);
            }else{
                Path p1= Paths.get(document.getLocation());
                desktop.open(p1.toFile());
            }
        }catch (Exception e){
            throw new InvalidDocumentException(e);
        }


    }
}
