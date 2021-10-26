package com.example.demo.githubTrending.rest;

import com.example.demo.githubTrending.model.GithubRepository;
import com.example.demo.githubTrending.service.GitHubTrendingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author KPQ
 * @date 2021/10/26
 */
@RestController
@Slf4j
public class GithubTrendingController {

    @Resource
    private GitHubTrendingService gitHubTrendingService;

    @GetMapping({"/test/trend/{language}", "/test/trend"})
    public List<GithubRepository> getTrending(@PathVariable(required = false) String language, @RequestParam(value = "since", required = false) String since) {
        List<GithubRepository> repositoryList = gitHubTrendingService.getGitHubTrending(language, since);
        return repositoryList;
    }


}
