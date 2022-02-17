package com.example.demo.githubtrending.rest;

import com.example.demo.aspect.Limiter;
import com.example.demo.githubtrending.model.Developer;
import com.example.demo.githubtrending.model.GithubRepository;
import com.example.demo.githubtrending.service.GitHubTrendingService;
import com.example.demo.githubtrending.service.HotDeveloperService;
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
    @Limiter(value = 2.0, timeout = 300)
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
