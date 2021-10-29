package com.example.demo.githubTrending.rest;

import com.example.demo.githubTrending.model.Developer;
import com.example.demo.githubTrending.model.GithubRepository;
import com.example.demo.githubTrending.service.GitHubTrendingService;
import com.example.demo.githubTrending.service.HotDeveloperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @Resource
    private HotDeveloperService hotDeveloperService;

    @GetMapping({"/test/trend/{language}", "/test/trend"})
    @CrossOrigin({"*"})
    public List<GithubRepository> getTrending(@PathVariable(required = false) String language, @RequestParam(value = "since", required = false) String since) {
        List<GithubRepository> repositoryList = gitHubTrendingService.getGitHubTrending(language, since);
        return repositoryList;
    }

    @GetMapping({"/hot/develops/{language}", "/hot/develops"})
    @CrossOrigin({"*"})
    public List<Developer> getHotDevelops(@PathVariable(required = false) String language, @RequestParam(value = "since", required = false) String since) {
        List<Developer> developers = hotDeveloperService.getDevelopers(language, since);
        return developers;
    }

}
