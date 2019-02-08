package meTube.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "tubes")
public class Tube extends BaseEntity {

    private String title;
    private String author;
    private String description;
    private String youTubeId;
    private long views;
    private User uploader;

    public Tube() {
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "author", nullable = false)
    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(name = "description", nullable = false)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "youTubeId", nullable = false)
    public String getYouTubeId() {
        return this.youTubeId;
    }

    public void setYouTubeId(String youTubeId) {
        this.youTubeId = youTubeId;
    }

    @Column(name = "views", nullable = false)
    public long getViews() {
        return this.views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "uploader", referencedColumnName = "id")
    public User getUploader() {
        return this.uploader;
    }

    public void setUploader(User uploader) {
        this.uploader = uploader;
    }
}
