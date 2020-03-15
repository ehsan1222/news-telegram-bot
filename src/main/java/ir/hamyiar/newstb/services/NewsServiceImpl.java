package ir.hamyiar.newstb.services;

import ir.hamyiar.newstb.dao.News;
import ir.hamyiar.newstb.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService{

    @Autowired
    private NewsRepository newsRepository;

    @Override
    @Transactional
    public void saveNews(News news) {
        newsRepository.save(news);
    }

    @Override
    @Transactional
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<News> getNews(String newsId) {
        return newsRepository.findById(newsId);
    }

    @Override
    @Transactional
    public void deleteNews(String newsId) {
        newsRepository.deleteById(newsId);
    }
}
