package cn.mteach.examclient.service;

import cn.mteach.common.domain.training.Training;
import cn.mteach.common.domain.training.TrainingSection;
import cn.mteach.common.domain.training.TrainingSectionProcess;
import cn.mteach.common.domain.training.UserTrainingHistory;
import cn.mteach.common.util.Page;

import java.util.List;
import java.util.Map;

public interface TrainingService {

	/**
	 * 获取培训列表
	 * @param page
	 * @return
	 */
	public List<Training> getTrainingList(Page<Training> page);
	
	/**
	 * 获取培训章节
	 * @param trainingId
	 * @param page
	 * @return
	 */
	public List<TrainingSection> getTrainingSectionByTrainingId(int trainingId, Page<TrainingSection> page);
	
	/**
	 * 获取培训章节
	 * @param sectionId
	 * @param page
	 * @return
	 */
	public List<TrainingSection> getTrainingSectionById(int sectionId, Page<TrainingSection> page);
	
	/**
	 * 获取用户培训历史
	 * @param sectionId
	 * @param userId
	 * @return
	 */
	public UserTrainingHistory getTrainingHistBySectionId(int sectionId, int userId);
	
	/**
	 * 增加或更新用户培训历史
	 * @param hist
	 */
	public void setUserTrainingHistory(UserTrainingHistory hist);
	
	/**
	 * 获取用户培训进度清单
	 * @param userId
	 * @return
	 */
	public Map<Integer, List<TrainingSectionProcess>> getTrainingSectionProcessMapByUserId(int userId);
}