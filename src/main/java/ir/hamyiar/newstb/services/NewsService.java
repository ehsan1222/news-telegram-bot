package ir.hamyiar.newstb.services;

import ir.hamyiar.newstb.dao.News;

import java.util.List;
import java.util.Optional;

public interface NewsService {

    void saveNews(News news);

    List<News> getAllNews();

    Optional<News> getNews(String newsId);

    void deleteNews(String newsId);

}
