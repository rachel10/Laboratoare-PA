package app;

import entity.Album;
import entity.Artist;
import entity.Chart;
import repo.AbstractRepository;
import repo.AlbumRepository;
import repo.ArtistRepository;
import repo.ChartRepository;
import util.PersistenceUtil;

public class AlbumManager {
    public static void main(String[] args) {
/*
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
        //albumRepository.create(album);

        Chart chart=new Chart();
        chart.setAlbum(albumRepository.findById(43));
        chart.setVotes(100);

        ChartRepository chartRepository=new ChartRepository();
        chartRepository.create(chart);

        System.out.println(chartRepository.findByAlbum(albumRepository.findById(43)));

        System.out.println(albumRepository.findByArtist(artistRepository.findById(35)));
        System.out.println(albumRepository.findByName("album nou"));

        artistRepository.closeEntityManager();
        albumRepository.closeEntityManager();
       chartRepository.closeEntityManager();
        PersistenceUtil.getInstance().closeFactory();
  */
        AbstractRepository<Artist> abstractRepository=new ArtistRepository();

        Artist artist=new Artist();
        artist.setCountry("Germania");
        artist.setName("Artist1");
        abstractRepository.create(artist);


        AbstractRepository<Album> abstractRepository1=new AlbumRepository();
        Album album=new Album();
        album.setName("album1");
        album.setReleaseYear(2020);
        album.setArtist(artist);
        abstractRepository1.create(album);

        AbstractRepository abstractRepository2=new ChartRepository();
        Chart chart=new Chart();
        chart.setAlbum(album);
        chart.setVotes(99);

        abstractRepository2.create(chart);

        System.out.println("Find artist by id=34:");
        System.out.println(abstractRepository.findById(34));

        System.out.println("Find album by name='Quo Vadis'");
        System.out.println(abstractRepository1.findByName("Quo Vadis"));

        System.out.println("Find charts by id:5 ");
        System.out.println(abstractRepository2.findById(5));


        abstractRepository.closeEntityManager();
        abstractRepository1.closeEntityManager();
        abstractRepository2.closeEntityManager();
        PersistenceUtil.getInstance().closeFactory();
    }
}
