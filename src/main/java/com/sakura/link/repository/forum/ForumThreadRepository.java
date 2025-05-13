// src/main/java/com/sakura/link/repository/forum/ForumThreadRepository.java
package com.sakura.link.repository.forum;

import com.sakura.link.models.forum.ForumThread;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumThreadRepository extends JpaRepository<ForumThread, Long> {}
