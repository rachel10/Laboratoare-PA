import java.awt.*;
import java.io.*;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class CatalogUtil {

    public static void saveAsPlainText(Catalog catalog) throws IOException {
        try (OutputStreamWriter oos = new OutputStreamWriter(new FileOutputStream(catalog.getPath()))) {
            oos.write(catalog.toString());
        }
    }

    public static Catalog loadAsPlainText(String path) throws InvalidCatalogException {
        Catalog catalog=new Catalog();
        String buffer;
        int lineNumber=0;
        try (InputStreamReader inputStream = new InputStreamReader(new FileInputStream(path))) {
            Scanner scanner=new Scanner(inputStream);
            while(scanner.hasNextLine()) {
               buffer = scanner.nextLine();
               if(lineNumber==0)//numele catalogului
               {
                   catalog.setName(buffer);
               } else{
                   if (lineNumber==1)//path-ul
                    {
                        catalog.setPath(buffer);
                    }
                    else{//document
                        Document document=new Document();
                        Scanner scanDocument=new Scanner(buffer);
                        int lineDocument=0;
                        String docBuffer;
                       scanDocument.useDelimiter("]|, ");
                        while(scanDocument.hasNext()){
                            docBuffer=scanDocument.next();
                            if(lineDocument==0){//id-ul
                                document.setId(docBuffer.substring(1));
                            }else{
                                if (lineDocument==1){//name
                                    document.setName(docBuffer);
                                }else{
                                    if(lineDocument==2){//location
                                        document.setLocation(docBuffer);
                                    }else {
                                        Scanner scanTags=new Scanner(docBuffer);
                                        scanTags.useDelimiter(", |}|=");
                                        int first=1;
                                        String string,object;
                                        while(scanTags.hasNext()){
                                            string = scanTags.next();
                                            if(first==1) {
                                               string=string.substring(1);
                                                first=0;
                                            }
                                            if(scanTags.hasNext()){
                                            object=scanTags.next();
                                            document.addTag(string,object);}
                                        }
                                    }
                                }
                            }
                            lineDocument++;
                        }
                        catalog.addDocument(document);
                   }
               }
                lineNumber++;
            }

        } catch (Exception exception) {
            throw new InvalidCatalogException(exception);
        }
        return catalog;
    }

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
