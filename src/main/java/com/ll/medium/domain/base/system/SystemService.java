package com.ll.medium.domain.base.system;

import com.ll.medium.domain.article.article.repository.ArticleRepository;
import com.ll.medium.domain.member.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SystemService {
    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;
    // TODO articleService.write 만들고 나서 isSampleDataCreated 내 주석 활성화하기

    public boolean isSampleDataCreated() {
        return (memberRepository.count() > 0 /*&& articleRepository.count() > 0*/);
    }
}
