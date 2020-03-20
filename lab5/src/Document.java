import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Document implements Serializable {
    private String id;
    private String name;
    private String location;
    private Map<String, Object> tags = new HashMap<>();

    public Document(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public Map<String, Object> getTags() {
        return tags;
    }

    public void addTag(String key, Object obj) {
        tags.put(key, obj);
    }

    public void setTags(Map<String, Object> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Document)) return false;
        Document document = (Document) o;
        return getId().equals(document.getId());
    }


}
