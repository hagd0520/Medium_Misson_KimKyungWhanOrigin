package com.ll.medium.global.initData;

import com.ll.medium.domain.article.article.service.ArticleService;
import com.ll.medium.domain.base.system.SystemService;
import com.ll.medium.domain.member.member.service.MemberService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotProd {
    @Bean
    public ApplicationRunner initNotProd(
            SystemService systemService,
            MemberService memberService,
            ArticleService articleService
    ) {
        return args -> {
            if (systemService.isSampleDataCreated()) return;
        };
    }
}
