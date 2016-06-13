package dao.impl;

import dao.DAOFactory;
import dao.SingerDAO;
import model.Album;
import model.Singer;
import model.SingersCatalog;
import model.Song;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Mayer Roman on 10.06.2016.
 */
public class XMLSingerDAOTest {
    private static SingerDAO singerDAO;

    @BeforeClass
    public static void createSingerDAO() {
        DAOFactory daoFactory = new XMLDAOFactory();
        singerDAO = daoFactory.getSingerDAO();
    }

    @AfterClass
    public static void removeReferenceToSingerDAO() {
        singerDAO = null;
    }



    @Test
    public void testCreateSingersCatalog() throws Exception {
        SingersCatalog singersCatalogToWriteInXML = prepareSingersCatalog();

        singerDAO.createSingersCatalog(singersCatalogToWriteInXML);

        SingersCatalog singersCatalogFromXML = singerDAO.readSingersCatalog();

        assertEquals(singersCatalogFromXML, singersCatalogToWriteInXML);

    }

    @Test
    public void testCreateSinger() throws Exception {
        prepareAndWriteInXMLSingersCatalog(singerDAO);

        int newSingerId = 4;
        Singer singerForSaving = prepareSinger(newSingerId);
        singerDAO.createSinger(singerForSaving);

        Singer singerFromXML = singerDAO.readSinger(newSingerId);

        assertEquals(singerFromXML, singerForSaving);
    }

    @Test
    public void testReadSingersCatalog() throws Exception {
        SingersCatalog singersCatalogToWriteInXML = prepareSingersCatalog();

        singerDAO.createSingersCatalog(singersCatalogToWriteInXML);

        SingersCatalog singersCatalogFromXML = singerDAO.readSingersCatalog();

        assertEquals(singersCatalogFromXML, singersCatalogToWriteInXML);
    }

    @Test
    public void testReadSinger() throws Exception {
        prepareAndWriteInXMLSingersCatalog(singerDAO);

        int newSingerId = 4;
        Singer singerForSaving = prepareSinger(newSingerId);
        singerDAO.createSinger(singerForSaving);

        Singer singerFromXML = singerDAO.readSinger(newSingerId);

        assertEquals(singerFromXML, singerForSaving);
    }

    @Test
    public void testGetDurationOfAllSongsOfSinger() throws Exception {
        prepareAndWriteInXMLSingersCatalog(singerDAO);

        int newSingerId = 5;
        Singer singer = prepareSinger(newSingerId);

        Duration durationBeforeSaving = Duration.ZERO;
        List<Album> albumList = singer.getAlbums();
        for (Album album : albumList) {
            for (Song song : album.getSongs()) {
                durationBeforeSaving = durationBeforeSaving.plus(song.getDuration());
            }
        }

        singerDAO.createSinger(singer);

        Duration durationReadFromXML = singerDAO.getDurationOfAllSongsOfSinger(newSingerId);

        assertEquals(durationReadFromXML, durationBeforeSaving);
    }

    @Test
    public void testUpdateSinger() throws Exception {
        prepareAndWriteInXMLSingersCatalog(singerDAO);

        int singerID = 1;

        Singer singerBeforeUpdating = singerDAO.readSinger(singerID);

        singerDAO.updateSinger((prepareSinger(singerID)));

        Singer singerAfterUpdating = singerDAO.readSinger(singerID);

        assertNotEquals(singerAfterUpdating, singerBeforeUpdating);
    }

    @Test
    public void testDeleteSinger() throws Exception {
        prepareAndWriteInXMLSingersCatalog(singerDAO);

        singerDAO.deleteSinger(1);

        Singer expectedNull = singerDAO.readSinger(1);

        assertNull(expectedNull);

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