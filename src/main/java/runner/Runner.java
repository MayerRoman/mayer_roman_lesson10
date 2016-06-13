package runner;

import dao.DAOFactory;
import dao.SingerDAO;
import dao.impl.XMLDAOFactory;
import model.Album;
import model.Singer;
import model.SingersCatalog;
import model.Song;

import java.time.Duration;
import java.util.List;

/**
 * Created by Mayer Roman on 31.05.2016.
 */
public class Runner {
    public void start() {
        DAOFactory daoFactory = new XMLDAOFactory();
        SingerDAO singerDAO = daoFactory.getSingerDAO();

        savingAndReadingSingersCatalog(singerDAO);

        savingAndReadingSinger(singerDAO);

        gettingDurationOfAllSongsOfSinger(singerDAO);

        updatingSinger(singerDAO);

        deletingSinger(singerDAO);

    }


    private void savingAndReadingSingersCatalog(SingerDAO singerDAO) {
        SingersCatalog singersCatalog = prepareSingersCatalog();
        System.out.println("Saving and reading singersCatalog:");

        System.out.println("SingersCatalog before saving and reading:");
        System.out.println(singersCatalog);

        singerDAO.createSingersCatalog(singersCatalog);
        singersCatalog = singerDAO.readSingersCatalog();

        System.out.println("SingersCatalog after saving and reading:");
        System.out.println(singersCatalog);
        System.out.println();

    }

    private void savingAndReadingSinger(SingerDAO singerDAO) {
        prepareAndWriteInXMLSingersCatalog(singerDAO);
        System.out.println("Saving and reading singer:");

        int newSingerId = 4;
        Singer singer = prepareSinger(newSingerId);

        System.out.println("Singer before saving and reading:");
        System.out.println(singer);


        singerDAO.createSinger(singer);
        singer = singerDAO.readSinger(newSingerId);

        System.out.println("Singer after saving and reading:");
        System.out.println(singer);
        System.out.println();

    }

    private void gettingDurationOfAllSongsOfSinger(SingerDAO singerDAO) {
        prepareAndWriteInXMLSingersCatalog(singerDAO);
        System.out.println("Getting duration of all songs of singer:");

        int newSingerId = 5;
        Singer singer = prepareSinger(newSingerId);

        Duration durationBeforeSaving = Duration.ZERO;
        List<Album> albumList = singer.getAlbums();
        for (Album album : albumList) {
            for (Song song : album.getSongs()) {
                durationBeforeSaving = durationBeforeSaving.plus(song.getDuration());
            }
        }
        System.out.println("Duration of all songs of singer before saving:");
        System.out.println(durationBeforeSaving);

        singerDAO.createSinger(singer);

        Duration durationReadFromXML = singerDAO.getDurationOfAllSongsOfSinger(newSingerId);
        System.out.println("Duration of all songs of singer read from XML:");
        System.out.println(durationBeforeSaving);
        System.out.println();

    }

    private void updatingSinger(SingerDAO singerDAO) {
        prepareAndWriteInXMLSingersCatalog(singerDAO);
        System.out.println("Updating singer:");

        int singerID = 1;

        System.out.println("Singer with id \"1\" before updating:");
        System.out.println(singerDAO.readSinger(singerID));

        singerDAO.updateSinger((prepareSinger(singerID)));

        System.out.println("Singer with id \"1\" after updating:");
        System.out.println(singerDAO.readSinger(singerID));
        System.out.println();
    }

    private void deletingSinger(SingerDAO singerDAO) {
        prepareAndWriteInXMLSingersCatalog(singerDAO);
        System.out.println("Deleting singer:");


        System.out.println("SingersCatalog before deleting singer with id \"1\":");
        System.out.println(singerDAO.readSingersCatalog());

        singerDAO.deleteSinger(1);

        System.out.println("SingersCatalog after deleting singer with id \"1\":");
        System.out.println(singerDAO.readSingersCatalog());
        System.out.println();

    }


    private SingersCatalog prepareSingersCatalog() {
        SingersCatalog singersCatalog = new SingersCatalog();
        Singer u2 = new Singer(1, "U2");
        Singer sting = new Singer(2, "Sting");
        Singer acDc = new Singer(3, "AC/DC");

        Album allThatYouCantLeaveBehind = new Album("All that you can't left behind", "Pop-Rock");
        Album howToDismantleAnAtomicBomb = new Album("How To Dismantle An Atomic Bomb", "Rock");
        Album brandNewDay = new Album("Brand New Day", "Pop");
        Album whoMadeWho = new Album("Who Made Who", "Rock");

        Song elevation = new Song("Elevation", Duration.ofMinutes(3).plusSeconds(46));
        Song walkOn = new Song("Walk On", Duration.ofMinutes(4).plusSeconds(56));
        Song vertigo = new Song("Vertigo", Duration.ofMinutes(3).plusSeconds(13));
        Song desertRose = new Song("Desert Rose", Duration.ofMinutes(4).plusSeconds(47));
        Song forThoseAboutToRock = new Song("For Those About To Rock", Duration.ofMinutes(5).plusSeconds(53));

        u2.setAlbum(allThatYouCantLeaveBehind);
        u2.setAlbum(howToDismantleAnAtomicBomb);
        sting.setAlbum(brandNewDay);
        acDc.setAlbum(whoMadeWho);

        allThatYouCantLeaveBehind.setSong(elevation);
        allThatYouCantLeaveBehind.setSong(walkOn);
        howToDismantleAnAtomicBomb.setSong(vertigo);
        brandNewDay.setSong(desertRose);
        whoMadeWho.setSong(forThoseAboutToRock);

        singersCatalog.addSinger(u2);
        singersCatalog.addSinger(sting);
        singersCatalog.addSinger(acDc);


        return singersCatalog;
    }

    private Singer prepareSinger(int singerId) {
        Singer singer = new Singer(singerId, "TestSinger");

        Album album = new Album("TestAlbum", "TestGenre");
        singer.setAlbum(album);

        Song song = new Song("TestSong1", Duration.ofMinutes(4).plusSeconds(45));
        album.setSong(song);
        song = new Song("TestSong2", Duration.ofMinutes(3).plusSeconds(34));
        album.setSong(song);


        return singer;
    }

    private void prepareAndWriteInXMLSingersCatalog(SingerDAO singerDAO) {
        SingersCatalog singersCatalog = prepareSingersCatalog();
        singerDAO.createSingersCatalog(singersCatalog);

    }

}
