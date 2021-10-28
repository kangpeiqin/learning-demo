package com.example.demo.githubTrending.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.githubTrending.config.CacheConfig;
import com.example.demo.githubTrending.model.Contributor;
import com.example.demo.githubTrending.model.GithubRepository;
import com.example.demo.util.HttpUtil;
import com.example.demo.util.Result;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author KPQ
 * @date 2021/10/26
 */
@Service
public class GitHubTrendingService {

    private static final String GITHUB_TRENDING_URL = "https://github.com/trending/";
    private static final String GITHUB_URL = "https://github.com";

    @Cacheable(cacheNames = CacheConfig.CacheName.TRENDING_HOT, key = "#language+':'+#since")
    public List<GithubRepository> getGitHubTrending(String language, String since) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("language", language);
        jsonObject.put("since", since);
        Result result = HttpUtil.get(GITHUB_TRENDING_URL, jsonObject);
        return getTrendingRepositories((String) result.getData());
    }

    private List<GithubRepository> getTrendingRepositories(String html) {
        List<GithubRepository> repositoryList = new ArrayList<>();
        Document doc = Jsoup.parse(html);
        Elements articles = doc.getElementsByTag("article");
        articles.forEach(article -> {
            Element head = article.getElementsByTag("h1").first().getElementsByTag("a").first();
            GithubRepository repository = new GithubRepository();
            setRepositoryInfo(head, repository);
            //中间为描述
            Element description = article.getElementsByTag("p").first();
            Optional.ofNullable(description).ifPresent(t -> repository.setDescription(t.text()));
            Element footer = article.getElementsByTag("div").last();
            setStarsAndForks(footer, repository);
            repository.setProgrammingLanguage(getProgrammingLanguage(footer));
            repository.setContributors(getContributors(footer));
            repositoryList.add(repository);
        });
        return repositoryList;
    }

    private void setStarsAndForks(Element footer, GithubRepository repository) {
        Elements links = footer.getElementsByTag("a");
        repository.setStars(links.get(0).text());
        repository.setForks(links.get(1).text());
    }

    /**
     * @param footer 尾部html 元素
     * @return 编程语言
     */
    private String getProgrammingLanguage(Element footer) {
        Element span = footer.getElementsByTag("span").first();
        if (span != null) {
            Element language = span.getElementsByTag("span").last();
            return language.text();
        }
        return "";
    }

    /**
     * 获取仓库作者、项目、项目链接
     *
     * @param head
     * @param repository
     */
    private void setRepositoryInfo(Element head, GithubRepository repository) {
        repository.setUrl(GITHUB_URL + head.attr("href"));
        String[] authorAndTitle = head.attr("href").trim().split("/");
        repository.setAuthor(authorAndTitle[1]);
        repository.setTitle(authorAndTitle[2]);
    }

    private List<Contributor> getContributors(Element footer) {
        List<Contributor> contributorsList = new ArrayList<>();
        Elements links = footer.getElementsByTag("a");
        for (Element link : links) {
            Element element = link.getElementsByClass("avatar mb-1 avatar-user").first();
            if (element != null) {
                String avatar = element.attr("src");
                String accountLink = link.attr("href");
                Contributor contributor = new Contributor(avatar, GITHUB_URL + accountLink);
                contributorsList.add(contributor);
            }
        }
        return contributorsList;
    }

}
