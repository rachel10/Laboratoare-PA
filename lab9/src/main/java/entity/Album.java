package entity;

import javax.persistence.*;
import java.io.Serializable;
@NamedQueries({@NamedQuery(name = "findAlbumByName",query = "SELECT a FROM Album a WHERE a.name LIKE :name"),
        @NamedQuery(name = "findByArtist", query = "SELECT a FROM Album a WHERE a.artist = :artist")})
@Entity
@Table(name="albums")
public class Album implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;

    @Column(name="name")
    @Basic(optional = false)
    private String name;

    @Column(name="release_year")
    private Integer releaseYear;

    @JoinColumn(name="artist_id",referencedColumnName = "id")
    @ManyToOne
    private Artist artist;

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

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseYear=" + releaseYear +
                ", artist=" + artist +
                '}';
    }
}
