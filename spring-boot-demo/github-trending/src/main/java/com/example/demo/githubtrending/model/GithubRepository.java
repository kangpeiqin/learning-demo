package com.example.demo.githubtrending.model;

import lombok.Data;

import java.util.List;

/**
 * @author KPQ
 * @date 2021/10/26
 */
@Data
public class GithubRepository {
    private String author;
    private String title;
    private String url;
    private String description;
    private String programmingLanguage;
    private String stars;
    private String forks;
    private List<Contributor> contributors;
}