package com.acme.r3b2.journal.article.expansion.internal.resource.v1_0;

import com.acme.r3b2.journal.article.expansion.dto.v1_0.Article;
import com.acme.r3b2.journal.article.expansion.resource.v1_0.ArticleResource;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

import java.util.List;

/**
 * @author Liferay
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/article.properties",
	scope = ServiceScope.PROTOTYPE, service = ArticleResource.class
)
public class ArticleResourceImpl extends BaseArticleResourceImpl {

    @Override
    public Article getFirstArticleByUser(Integer userId) {
        List<JournalArticle> journalArticles = JournalArticleLocalServiceUtil.getArticles();

        for (JournalArticle journalArticle : journalArticles) {
            if (journalArticle.getUserId() == userId.longValue()) {
                Article article = new Article();

                article.setUserId((int)journalArticle.getUserId());
                article.setId(journalArticle.getArticleId());
                article.setTitle(journalArticle.getTitle());
                article.setContent(journalArticle.getContent());

                return article;
            }
        }

        return null;
    }
}