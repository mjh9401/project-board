package com.mjh.ProjectBoard.repository;

import com.mjh.ProjectBoard.config.JpaConfig;
import com.mjh.ProjectBoard.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JPARepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JPARepositoryTest(@Autowired ArticleRepository articleRepository,@Autowired ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("select test")
    @Test
    void givenTestData_whenSelecting_thenWorkFine(){
        //givne

        //when
        List<Article> articles = articleRepository.findAll();

        // then
        assertThat(articles)
                .isNotNull()
                .hasSize(123);

    }

    @DisplayName("insert test")
    @Test
    void givenTestData_whenInserting_thenWorkFine(){
        //givne
        long previousCount =articleRepository.count();
        //when
       Article savedArticle = articleRepository.save(Article.of("new article","new content","#spring"));

        // then
        assertThat(articleRepository.count()).isEqualTo(previousCount+1);

    }

    @DisplayName("update test")
    @Test
    void givenTestData_whenUpdating_thenWorkFine(){
        //givne
        Article article = articleRepository.findById(1L).orElseThrow();
        String updateHashtag = "#springboot";
        article.setHashtag(updateHashtag);
        long previousCount =articleRepository.count();

        //when
        Article savedArticle = articleRepository.saveAndFlush(article);


        // then
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag",updateHashtag);
    }

    @DisplayName("Delete test")
    @Test
    void givenTestData_whenDeleting_thenWorkFine(){
        //givne
        Article article = articleRepository.findById(1L).orElseThrow();
        long previousArticleCount = articleRepository.count();
        long previousArticleCommentCount = articleCommentRepository.count();
        int deletedCommnetSize = article.getArticleCommentSet().size();

        //when
        articleRepository.delete(article);


        // then
        assertThat(articleRepository.count()).isEqualTo(previousArticleCount-1);
        assertThat(articleCommentRepository.count()).isEqualTo(previousArticleCommentCount-deletedCommnetSize);
    }
}