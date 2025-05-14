// src/main/java/com/sakura/link/dto/forum/ThreadDto.java
package com.sakura.link.dto.forum;

import java.time.LocalDateTime;
import java.util.List;

public record ThreadDto(
        Long id,
        String title,
        Integer authorId,
        String authorName,
        String authorAvatar,
        LocalDateTime createdAt,
        boolean solved,
        Long solutionPostId,
        List<PostDto> posts
) {}
