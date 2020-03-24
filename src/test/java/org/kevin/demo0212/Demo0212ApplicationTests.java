package org.kevin.demo0212;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.kevin.demo0212.common.CommonUtils;
import org.kevin.demo0212.model.Article;
import org.kevin.demo0212.model.BlogUser;
import org.kevin.demo0212.model.SecretMoment;
import org.kevin.demo0212.model.SecretMomentExample;
import org.kevin.demo0212.service.ArticleService;
import org.kevin.demo0212.service.BlogUserService;
import org.kevin.demo0212.service.SecretMomentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Demo0212ApplicationTests {

	@Test
	void contextLoads() {
	}


	@Autowired
	private SecretMomentService secretMomentService;
	@Test
	void testDB(){
		SecretMoment record = new SecretMoment();
		record.setMoment("test my BaseModel");
		System.out.println(secretMomentService.insert(record));

		SecretMomentExample e = new SecretMomentExample();
		e.setOrderByClause("create_time DESC");
		List<SecretMoment> l = secretMomentService.findList(e);
		System.out.println(l.size());
		System.out.println(l.get(0).getId());
		System.out.println("done");
	}

	@Autowired
	private BlogUserService blogUserService;
	@Test
	void insertBlogUser(){
		BlogUser u1 = new BlogUser();
		u1.setUsername("edward");
		u1.setPassword(CommonUtils.securityPwdEncoder("fng"));

		BlogUser u2 = new BlogUser();
		u2.setUsername("kevin");
		u2.setPassword(CommonUtils.securityPwdEncoder("0322"));

		blogUserService.insert(u1);
		blogUserService.insert(u2);
	}

	@Autowired
	private ArticleService articleService;
	@Test
	void testArticleRowBounds(){
		List<Article> list = articleService.findList(7);
		System.out.println(list.size());
	}
}
