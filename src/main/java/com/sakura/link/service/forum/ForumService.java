// src/main/java/com/sakura/link/service/forum/ForumService.java
package com.sakura.link.service.forum;

import com.sakura.link.dto.forum.*;
import com.sakura.link.models.User;
import com.sakura.link.models.forum.*;
import com.sakura.link.repository.UserRepository;
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
    private final UserRepository userRepository;

    public ForumService(ForumThreadRepository threadRepo,
                        ForumPostRepository postRepo,
                        SimpMessagingTemplate broker,
                        UserRepository userRepository) {

        this.threadRepo = threadRepo;
        this.postRepo   = postRepo;
        this.broker     = broker;
        this.userRepository = userRepository;
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

    private PostDto toDto(ForumPost p) {

        /* вытаскиваем пользователя один раз, чтобы взять и имя, и аватар */
        var userOpt = userRepository.findById(p.getAuthorId());

        String name = userOpt
                .map(u -> u.getFirstName() + " " + u.getLastName())
                .orElse("User#" + p.getAuthorId());

        String avatarUrl = userOpt
                .map(User::getAvatar)
                .orElse(null);

        return new PostDto(
                p.getId(),
                p.getAuthorId(),
                name,
                avatarUrl,
                p.getContent(),
                p.getImage(),
                p.getLikes(),
                p.getSolution(),
                p.getCreatedAt()
        );
    }

    private ThreadDto toDto(ForumThread t) {
        Long solutionId = t.getPosts().stream()
                .filter(ForumPost::getSolution)
                .map(ForumPost::getId)
                .findFirst().orElse(null);

        return new ThreadDto(
                t.getId(), t.getTitle(), t.getAuthorId(), t.getCreatedAt(),
                t.isSolved(), solutionId,
                t.getPosts().stream().map(this::toDto).toList()
        );
    }

    /* ---------- принять решение ---------- */
    public ThreadDto acceptSolution(Long postId, Integer requestUserId) {
        ForumPost p = postRepo.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found " + postId));
        ForumThread thread = p.getThread();

        if (!thread.getAuthorId().equals(requestUserId))
            throw new SecurityException("Only thread author can accept solution");

        /* снимаем прежнее решение, если было */
        thread.getPosts().forEach(pp -> pp.setSolution(false));

        p.setSolution(true);
        thread.setSolved(true);

        ThreadDto dto = toDto(threadRepo.save(thread));

        // уведомляем подписчиков темы
        broker.convertAndSend("/chat/forum/" + thread.getId(), toDto(p));
        return dto;
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
