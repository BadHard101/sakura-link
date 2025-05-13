// src/main/java/com/sakura/link/service/forum/ForumService.java
package com.sakura.link.service.forum;

import com.sakura.link.dto.forum.*;
import com.sakura.link.models.forum.*;
import com.sakura.link.repository.forum.ForumPostRepository;
import com.sakura.link.repository.forum.ForumThreadRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ForumService {

    private final ForumThreadRepository threadRepo;
    private final ForumPostRepository   postRepo;
    private final SimpMessagingTemplate broker;

    public ForumService(ForumThreadRepository threadRepo,
                        ForumPostRepository postRepo,
                        SimpMessagingTemplate broker) {

        this.threadRepo = threadRepo;
        this.postRepo   = postRepo;
        this.broker     = broker;
    }

    /* ---------- THREADS ---------- */

    public List<ThreadDto> findAllThreads() {
        return threadRepo.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public ThreadDto createThread(ThreadRequest req, Integer authorId) {

        ForumThread thread = new ForumThread();
        thread.setTitle(req.title());
        thread.setAuthorId(authorId);

        return toDto(threadRepo.save(thread));
    }

    public ThreadDto findThread(Long id) {
        ForumThread thread = threadRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Thread not found: " + id));

        return toDto(thread);
    }

    /* ---------- POSTS ---------- */

    public PostDto addPost(Long threadId, PostRequest req, Integer authorId) {

        ForumThread thread = threadRepo.findById(threadId)
                .orElseThrow(() -> new IllegalArgumentException("Thread not found: " + threadId));

        ForumPost post = new ForumPost();
        post.setThread(thread);
        post.setAuthorId(authorId);
        post.setContent(req.content());
        post.setImage(req.image());

        PostDto dto = toDto(postRepo.save(post));

        // рассылаем по WebSocket всем подписчикам темы
        broker.convertAndSend("/chat/forum/" + threadId, dto);

        return dto;
    }

    /* ---------- mapping helpers ---------- */

    private ThreadDto toDto(ForumThread t) {
        return new ThreadDto(
                t.getId(),
                t.getTitle(),
                t.getAuthorId(),
                t.getCreatedAt(),
                t.isSolved(),                  // ← добавили
                t.getPosts().stream().map(this::toDto).toList()
        );
    }

    private PostDto toDto(ForumPost p) {
        return new PostDto(
                p.getId(),
                p.getAuthorId(),
                p.getContent(),
                p.getImage(),
                p.getLikes(),
                p.getCreatedAt()
        );
    }

    public ThreadDto setSolved(Long id, boolean value, Integer requestUserId) {
        ForumThread t = threadRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Thread not found: " + id));

        if (!t.getAuthorId().equals(requestUserId))
            throw new SecurityException("Only author can change status");

        t.setSolved(value);
        return toDto(threadRepo.save(t));
    }

}
