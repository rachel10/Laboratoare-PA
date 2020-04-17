package entity;

import javax.persistence.*;
import java.io.Serializable;

@NamedQuery(name="findByName",query = "SELECT a FROM Artist a WHERE a.name LIKE :name"
)
@Entity
@Table(name="artists")
public class Artist implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;

    @Column(name="name")
    @Basic(optional = false)
    private String name;

    @Column(name="country")
    private String country;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
