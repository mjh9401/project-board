package com.mjh.ProjectBoard.repository;

import com.mjh.ProjectBoard.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Long> {
}
