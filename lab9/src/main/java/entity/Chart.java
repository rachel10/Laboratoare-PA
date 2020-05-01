package entity;

import javax.persistence.*;
import java.io.Serializable;
@NamedQuery(name = "findByAlbum", query = "SELECT a FROM Chart a WHERE a.album = :album")
@Entity
@Table(name="charts")
public class Chart implements Serializable
{
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;

    @JoinColumn(name = "id_album", referencedColumnName = "id")
    @OneToOne
    private Album album;

    @Column(name="votes")
    @Basic(optional = false)
    private Integer votes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "Chart{" +
                "id=" + id +
                ", album=" + album +
                ", votes=" + votes +
                '}';
    }
}
