package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mayer Roman on 29.05.2016.
 */

@XmlType(propOrder = {"name", "genre", "songs"})
public class Album {
    private String name;

    private String genre;

    private List<Song> songs = new ArrayList<>();


    public Album() {}

    public Album(String name, String genre) {
        this.name = name;
        this.genre = genre;
    }


    public String getName() {
        return name;
    }

    @XmlElement(name = "albumName")
    public void setName(String name) {
        this.name = name;
    }


    public String getGenre() {
        return genre;
    }

    @XmlElement
    public void setGenre(String genre) {
        this.genre = genre;
    }


    public List<Song> getSongs() {
        return songs;
    }

    public Song getSong(int songIndex) {
        return songs.get(songIndex);
    }


    @XmlElement(name = "song")
    @XmlElementWrapper
    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public void setSong(Song song) {
        songs.add(song);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Album album = (Album) o;

        if (name != null ? !name.equals(album.name) : album.name != null) return false;
        if (genre != null ? !genre.equals(album.genre) : album.genre != null) return false;
        return songs != null ? songs.equals(album.songs) : album.songs == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (songs != null ? songs.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Album{" +
                "name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", songs=" + songs +
                '}';
    }
}
