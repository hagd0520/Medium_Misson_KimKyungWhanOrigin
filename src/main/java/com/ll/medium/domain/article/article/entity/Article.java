package com.ll.medium.domain.article.article.entity;

import com.ll.medium.domain.member.member.entity.Member;
import com.ll.medium.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@SuperBuilder
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
@Getter
@ToString
public class Article extends BaseEntity {
    @ManyToOne(fetch = LAZY)
    private Member author;
    private String title;
    private String body;
}
