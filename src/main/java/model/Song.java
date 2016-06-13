package model;

import dao.impl.parse_util.jaxb.DurationAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.Duration;

/**
 * Created by Mayer Roman on 29.05.2016.
 */
@XmlType(propOrder = {"title", "duration"})
public class Song {
    private String title;

    private Duration duration;


    public Song() {}

    public Song(String title, Duration duration) {
        this.title = title;
        this.duration = duration;
    }


    public String getTitle() {
        return title;
    }

    @XmlElement
    public void setTitle(String title) {
        this.title = title;
    }


    public Duration getDuration() {
        return duration;
    }

    @XmlJavaTypeAdapter(DurationAdapter.class)
    public void setDuration(Duration duration) {
        this.duration = duration;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Song song = (Song) o;

        if (title != null ? !title.equals(song.title) : song.title != null) return false;
        return duration != null ? duration.equals(song.duration) : song.duration == null;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Song{" +
                "title='" + title + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
