package cn.mteach.management.service;

import cn.mteach.common.domain.news.News;
import cn.mteach.common.util.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NewsService {

	public List<News> getNewsList(Page<News> page);
	
	public News getNewsById(int newsId);
	
	public void addNews(News news);

	public void deleteNew(String newId);

	public void updateNew(News news);
}
