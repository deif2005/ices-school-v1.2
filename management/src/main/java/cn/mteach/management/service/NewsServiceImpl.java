package cn.mteach.management.service;

import cn.mteach.common.domain.news.News;
import cn.mteach.common.util.Page;
import cn.mteach.management.persistence.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsMapper newsMapper;
	@Override
	public List<News> getNewsList(Page<News> page) {
		// TODO Auto-generated method stub
		return newsMapper.getNewsList(page);
	}

	@Override
	public News getNewsById(int newsId) {
		// TODO Auto-generated method stub
		return newsMapper.getNewsById(newsId);
	}

	@Override
	public void addNews(News news) {
		// TODO Auto-generated method stub
		newsMapper.addNews(news);
	}

	@Override
	public void deleteNew(String newId) {
		newsMapper.deleteNew(newId);
	}

	@Override
	public void updateNew(News news){
		newsMapper.updateNew(news);
	}
}
