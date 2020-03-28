import java.awt.*;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ViewCommand extends Command {
    String docId;
    public ViewCommand(String id) {
        super("ViewCommand");
        docId=id;
    }

    @Override
    public Catalog solver(String path) throws InvalidCatalogException, InvalidDocumentException {
        Desktop desktop=Desktop.getDesktop();

        Command command=new LoadCommand();
        Catalog catalog=new Catalog();
        try {
            catalog = command.solver(path);
        }catch (Exception e){
            throw new InvalidCatalogException(e);
        }

        Document document=catalog.findById(docId);

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

        return catalog;
    }
}
