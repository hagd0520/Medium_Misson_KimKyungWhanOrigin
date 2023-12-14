package com.ll.medium.domain.article.article.service;

import com.ll.medium.domain.article.article.entity.Article;
import com.ll.medium.domain.article.article.repository.ArticleRepository;
import com.ll.medium.domain.member.member.entity.Member;
import com.ll.medium.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {
    private final ArticleRepository articleRepository;

    public Page<Article> getList(
            @RequestParam(defaultValue = "1") int page
    ) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return articleRepository.findAll(pageable);
    }

    public Page<Article> searchListByUsername(String username,
                                           @RequestParam int page
    ) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));

        return articleRepository.findByAuthorUsernameLike(username, pageable);
    }

    public Page<Article> getMyList(
            @RequestParam(defaultValue = "1") int page
    ) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return articleRepository.findByAuthorUsername(username, pageable);
    }

    @Transactional
    public RsData<Article> write(
            String title,
            String body,
            Member author
    ) {
        Article article = Article.builder()
                .author(author)
                .title(title)
                .body(body)
                .build();

        articleRepository.save(article);

        return RsData.of("200",
                "글이 작성되었습니다.",
                articleRepository.save(article));
    }

    public RsData<Article> write(
            String title,
            String body,
            Member author,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return RsData.of(
                    "400",
                    bindingResult.getFieldError().getDefaultMessage(),
                    null
            );
        }
        return write(title, body, author);
    }

    public Optional<Article> findLatest() {
        return articleRepository.findFirstByOrderByIdDesc();
    }

    public Optional<Article> findById(long id) {
        return articleRepository.findById(id);
    }

    public boolean canDelete(Member actor, Article article) {
        if (actor == null) return false;
        if (actor.isAdmin()) return true;

        return article.getAuthor().equals(actor);
    }

    @Transactional
    public void delete(Article article) {
        articleRepository.delete(article);
    }

    @Transactional
    public boolean canModify(Member actor, Article article) {
        if (actor == null) return false;

        return article.getAuthor().equals(actor);
    }

    @Transactional
    public void modify(Article article, String title, String body) {
        article.setTitle(title);
        article.setBody(body);
    }

    public Optional<Article> findByUsername(String username) {
        return articleRepository.findByAuthorUsername(username);
    }
}
