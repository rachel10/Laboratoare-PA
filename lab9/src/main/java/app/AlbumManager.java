package app;

import entity.Album;
import entity.Artist;
import repo.AlbumRepository;
import repo.ArtistRepository;
import util.PersistenceUtil;

public class AlbumManager {
    public static void main(String[] args) {

        Artist artist=new Artist();
        artist.setName("artist Nou");
        artist.setCountry("Romania");

        ArtistRepository artistRepository=new ArtistRepository();
        //   artistRepository.create(artist);
        System.out.println(artistRepository.findByName("artist nou"));

        Album album=new Album();
        album.setName("album nou");
        album.setArtist(artistRepository.findById(35));
        album.setReleaseYear(2020);

        AlbumRepository albumRepository=new AlbumRepository();
        albumRepository.create(album);

        System.out.println(albumRepository.findByArtist(artistRepository.findById(35)));
        System.out.println(albumRepository.findByName("album nou"));

        artistRepository.closeEntityManager();
        albumRepository.closeEntityManager();
        PersistenceUtil.getInstance().closeFactory();
    }
}
