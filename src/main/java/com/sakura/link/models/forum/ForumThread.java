// src/main/java/com/sakura/link/models/forum/ForumThread.java
package com.sakura.link.models.forum;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "forum_threads")
public class ForumThread {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Integer authorId;

    private LocalDateTime createdAt = LocalDateTime.now();

    private boolean solved = false;

    @OneToMany(mappedBy = "thread",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<ForumPost> posts = new ArrayList<>();

    /* ---------- constructors ---------- */

    public ForumThread() {
    }

    public ForumThread(String title, Integer authorId) {
        this.title = title;
        this.authorId = authorId;
    }

    /* ---------- getters / setters ---------- */

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<ForumPost> getPosts() {
        return posts;
    }

    public void setPosts(List<ForumPost> posts) {
        this.posts = posts;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean s) {
        this.solved = s;
    }
}
