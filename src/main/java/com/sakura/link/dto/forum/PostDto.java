// src/main/java/com/sakura/link/dto/forum/PostDto.java
package com.sakura.link.dto.forum;

import java.time.LocalDateTime;

public record PostDto(
        Long id,
        Integer authorId,
        String authorName,
        String content,
        String image,
        Integer likes,
        boolean solution,
        LocalDateTime createdAt
) {}
