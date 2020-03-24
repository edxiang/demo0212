package org.kevin.demo0212.controller;

import com.alibaba.fastjson.JSON;
import org.kevin.demo0212.model.Article;
import org.kevin.demo0212.model.Finance;
import org.kevin.demo0212.model.SecretMoment;
import org.kevin.demo0212.model.dto.DataTables;
import org.kevin.demo0212.service.ArticleService;
import org.kevin.demo0212.service.FinanceService;
import org.kevin.demo0212.service.SecretMomentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Kevin.Z
 * @version 2020-03-15
 */
@RestController
public class RstController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private SecretMomentService secretMomentService;
    @Autowired
    private FinanceService financeService;

    @PostMapping("/saveArticle")
    public int saveArticle(Article record) {
        return articleService.insert(record);
    }

    @PostMapping("/saveSecretMoment")
    public int saveSecretMoment(@RequestParam(value = "content") String content) {
        return secretMomentService.insertNewRecord(content);
    }

    @PostMapping("/saveSpending")
    public int saveSpending(Finance record) {
        return financeService.insert(record);
    }

    @PostMapping("/spending/list")
    public String spendingList(@RequestParam(value = "start") Integer start,
                               @RequestParam(value = "length") Integer length,
                               @RequestParam(value = "draw") Integer draw) {
        List<Finance> finances = financeService.findList(start / length, length);
        long count = financeService.count(null);

        DataTables<Finance> dt = new DataTables<>((int) count, draw);
        dt.setData(finances);

        return JSON.toJSONString(dt);
    }
}
