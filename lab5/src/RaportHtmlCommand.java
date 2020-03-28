import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class RaportHtmlCommand extends Command {
    public RaportHtmlCommand() {
        super("RaportHtmlCommand");
    }

    @Override
    public Catalog solver(String path) throws InvalidCatalogException, InvalidDocumentException {
        Command command=new LoadCommand();
        Catalog catalog=new Catalog();
        try {
            catalog = command.solver(path);
        }catch (Exception e){
            throw new InvalidCatalogException(e);
        }
        String raport="<html><head><title>Raport</title></head><body>";

        raport=raport.concat("<h1> Raport pentru catalogul: "+catalog.getName()+"</h1>");
        raport=raport.concat("<h2> Locatia catalogului este: "+catalog.getPath()+"</h2>");

        raport=raport.concat("<h2> Documentele catalogului: </h2>");

        for(Document document:catalog.getDocuments()){
            raport=raport.concat("<h4><u>Id:</u>  "+ document.getId()+",  <u>titlu:</u>  "+document.getName()+",  <u>locatia:</u>  "+document.getLocation()+",  <u>tag-uri:</u>  "+(document.getTags().toString().equals("{}")?" nu are":document.getTags())+"</h4>");
        }
        raport=raport.concat("</body></html>");
        try(PrintWriter printWriter=new PrintWriter("e:/raport.html")){
                printWriter.println(raport);
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
}
