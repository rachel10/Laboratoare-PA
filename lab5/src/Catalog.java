import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable {
    private String name;
    private String path;
    private List<Document> documents = new ArrayList<>();

    public Catalog() {
    }

    public Catalog(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void addDocument(Document document) {
        for (Document doc : documents) {
            if (doc.equals(document)) {
                throw new InvalidIdException(doc.getId());
            }
        }

        documents.add(document);
    }

    public Document findById (String id){
        for(Document doc:documents){
            if(doc.getId().equals(id)){
                return doc;
            }
        }
        return null;
    }

}
