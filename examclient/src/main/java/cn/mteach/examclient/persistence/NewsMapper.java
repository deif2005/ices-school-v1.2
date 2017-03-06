package cn.mteach.examclient.persistence;

import cn.mteach.common.domain.news.News;
import cn.mteach.common.util.Page;

import java.util.List;

public interface NewsMapper {

	public List<News> getNewsList(Page<News> page);
	
	public News getNewsById(int newsId);
}
