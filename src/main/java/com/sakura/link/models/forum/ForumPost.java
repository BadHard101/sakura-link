// src/main/java/com/sakura/link/models/forum/ForumPost.java
package com.sakura.link.models.forum;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "forum_posts")
public class ForumPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thread_id")
    private ForumThread thread;

    private Integer authorId;

    @Lob
    private String content;

    private String image;              // опционально

    private Integer likes = 0;

    private LocalDateTime createdAt = LocalDateTime.now();

    private Boolean solution = false;

    /* ---------- constructors ---------- */

    public ForumPost() {}

    /* ---------- getters / setters ---------- */

    public Long getId() { return id; }

    public ForumThread getThread() { return thread; }
    public void setThread(ForumThread thread) { this.thread = thread; }

    public Integer getAuthorId() { return authorId; }
    public void setAuthorId(Integer authorId) { this.authorId = authorId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public Integer getLikes() { return likes; }
    public void setLikes(Integer likes) { this.likes = likes; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Boolean getSolution()          { return solution; }
    public void    setSolution(Boolean s) { this.solution = s; }
}
