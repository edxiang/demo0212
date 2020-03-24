package org.kevin.demo0212.controller;

import org.kevin.demo0212.common.Constant;
import org.kevin.demo0212.model.Article;
import org.kevin.demo0212.model.ArticleType;
import org.kevin.demo0212.model.BlogUser;
import org.kevin.demo0212.model.SecretMoment;
import org.kevin.demo0212.model.dto.PageModel;
import org.kevin.demo0212.service.ArticleService;
import org.kevin.demo0212.service.ArticleTypeService;
import org.kevin.demo0212.service.SecretMomentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Kevin.Z
 * @version 2020-03-12
 */
@Controller
public class IndexController extends BaseController{
    @Autowired
    private ArticleService articleService;
    @Autowired
    private SecretMomentService secretMomentService;
    @Autowired
    private ArticleTypeService articleTypeService;

    @RequestMapping({"/", "", "/index"})
    public String index(ModelMap modelMap) {
        List<Article> articles = articleService.findList(8);
        modelMap.addAttribute("articles", articles);

        return "index";
    }

    @RequestMapping("/signin")
    public String signin(ModelMap modelMap) {
        return "signin";
    }

    @GetMapping("/newArticle")
    public String newArticle(ModelMap modelMap) {
        List<ArticleType> articleTypes = articleTypeService.findList();
        modelMap.addAttribute("articleTypes", articleTypes);

        return "newArticle";
    }

    @GetMapping("/article")
    public String article(@RequestParam("id") String id, ModelMap modelMap) {
        List<ArticleType> articleTypes = articleTypeService.findList();
        modelMap.addAttribute("articleTypes", articleTypes);

        Article article = articleService.selectById(id);
        modelMap.addAttribute("article", article);
        return "blog";
    }

    @GetMapping("/secretMoment")
    public String secretMoment(@RequestParam(value = "pageNum", required = false) Integer pageNum,
                               @RequestParam(value = "pageSize", required = false) Integer pageSize,
                               @RequestParam(value = "moment", required = false) String moment,
                               ModelMap modelMap) {
        BlogUser blogUser = getCurrentBlogUser();
        System.err.println("the current BlogUser's name:" + blogUser.getUsername());

        List<SecretMoment> secretMoments = secretMomentService.findList(moment, pageNum, pageSize);
        long count = secretMomentService.count(moment);
        PageModel pageModel = new PageModel(pageNum, pageSize, count);

        modelMap.addAttribute("pageModel", pageModel);
        modelMap.addAttribute("secretMoments", secretMoments);
        return "secretMoment";
    }

    @GetMapping("/articles")
    public String articles(@RequestParam("articleType") Integer type,
                           @RequestParam(value = "pageNum", required = false) Integer pageNum,
                           @RequestParam(value = "pageSize", required = false) Integer pageSize,
                           ModelMap modelMap) {
        List<Article> articles = articleService.selectByTypes(type, pageNum, pageSize);
        long count = articleService.count(type);
        PageModel pageModel = new PageModel(pageNum, pageSize, count);

        modelMap.addAttribute("pageModel", pageModel);
        modelMap.addAttribute("articles", articles);
        modelMap.addAttribute("articleType", type);

        return "blogWithType";
    }

    @GetMapping("/spending")
    public String spending() {
        return "spending";
    }
}
