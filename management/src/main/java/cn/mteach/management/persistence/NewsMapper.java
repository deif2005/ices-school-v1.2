package cn.mteach.management.persistence;

import cn.mteach.common.domain.news.News;
import cn.mteach.common.util.Page;

import java.util.List;

public interface NewsMapper {

	public List<News> getNewsList(Page<News> page);
	
	public News getNewsById(int newsId);
	
	public void addNews(News news);

	public void deleteNew(String newId);

	public void updateNew(News news);
}
