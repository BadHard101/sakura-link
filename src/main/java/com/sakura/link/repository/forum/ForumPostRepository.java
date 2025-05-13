// src/main/java/com/sakura/link/repository/forum/ForumPostRepository.java
package com.sakura.link.repository.forum;

import com.sakura.link.models.forum.ForumPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumPostRepository extends JpaRepository<ForumPost, Long> {}
