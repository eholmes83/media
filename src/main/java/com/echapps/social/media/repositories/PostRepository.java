package com.echapps.social.media.repositories;

import com.echapps.social.media.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
