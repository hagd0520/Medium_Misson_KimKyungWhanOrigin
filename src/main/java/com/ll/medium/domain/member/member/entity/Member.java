package com.ll.medium.domain.member.member.entity;

import com.ll.medium.domain.article.article.entity.Article;
import com.ll.medium.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import static lombok.AccessLevel.PROTECTED;

@Entity
@SuperBuilder
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
@Getter
@Setter
@ToString
public class Member extends BaseEntity {
    private Article article;
    private String username;
    private String password;
}
