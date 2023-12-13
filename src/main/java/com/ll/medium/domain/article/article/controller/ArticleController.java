package com.ll.medium.domain.article.article.controller;

import com.ll.medium.domain.article.article.entity.Article;
import com.ll.medium.domain.article.article.entity.ArticleWriteForm;
import com.ll.medium.domain.article.article.service.ArticleService;
import com.ll.medium.global.rq.Rq;
import com.ll.medium.global.rsData.RsData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final Rq rq;

    @GetMapping("/list")
    public String showList(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Article> paging = articleService.getList(page);
        model.addAttribute("paging", paging);
        return "article/article/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/myList")
    public String showMyList(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Article> paging = articleService.getMyList(page);
        model.addAttribute("paging", paging);
        return "article/article/myList";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/write")
    public String showWrite() {
        return "article/article/write";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/write")
    public String write(@Valid ArticleWriteForm writeForm, BindingResult bindingResult) {
        RsData<Article> writeRs = articleService.write(
                writeForm.getTitle(),
                writeForm.getBody(),
                writeForm.getMember(),
                bindingResult
        );
        return rq.redirectOrBack("/", writeRs);
    }

    @GetMapping("/detail/{id}")
    public String showDetail(Model model, @PathVariable long id) {
        Article article = articleService.findById(id).get();

        model.addAttribute("article", article);

        return "article/article/detail";
    }
}
