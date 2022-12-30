package com.mjh.ProjectBoard.repository;

import com.mjh.ProjectBoard.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment,Long> {
}
