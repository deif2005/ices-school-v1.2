package cn.mteach.management.service;

import cn.mteach.common.domain.training.Training;
import cn.mteach.common.domain.training.TrainingSection;
import cn.mteach.common.domain.training.TrainingSectionProcess;
import cn.mteach.common.domain.training.UserTraining;
import cn.mteach.common.util.Page;
import cn.mteach.management.persistence.TrainingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("trainingService")
public class TrainingServiceImpl implements TrainingService {

	@Autowired
	private TrainingMapper trainingMapper;

	/**
	 *
	 * @param userId 为-1时，获取所有培训，否则获取userId发布的培训
	 * @param page
	 * @return
	 */
	@Override
	public List<Training> getTrainingList(int userId, Page<Training> page) {
		// TODO Auto-generated method stub
		return trainingMapper.getTrainingList(userId, page);
	}

	/**
	 *
	 * @param training
	 */
	@Override
	public void addTraining(Training training) {
		// TODO Auto-generated method stub
		trainingMapper.addTraining(training);
	}

	/**
	 *
	 * @param trainingId
	 * @param userId 不等于-1则为该用户发布的培训
	 * @param page
	 * @return
	 */
	@Override
	public List<TrainingSection> getTrainingSectionByTrainingId(int trainingId, int userId, Page<TrainingSection> page) {
		// TODO Auto-generated method stub
		return trainingMapper.getTrainingSectionByTrainingId(trainingId, userId, page);
	}

	/**
	 *
	 * @param sectionId
	 * @param userId 不等于-1则为该用户发布的培训
	 * @param page
	 * @return
	 */
	@Override
	public List<TrainingSection> getTrainingSectionById(int sectionId, int userId, Page<TrainingSection> page) {
		// TODO Auto-generated method stub
		return trainingMapper.getTrainingSectionById(sectionId, userId, page);
	}

	/**
	 * 删除培训内容
	 * @param trainingId
	 * @param userId
	 * @return
	 */
	@Override
	@Transactional
	public void deleteTraining(int trainingId, int userId){
		trainingMapper.deleteTrainingSectionByTrainingId(trainingId, userId);
		trainingMapper.deleteTraining(trainingId, userId);
	}

	/**
	 *
	 * @param sectionId
	 * @param userId 不等于-1则为该用户发布的培训
	 */
	@Override
	public void deleteTrainingSection(int sectionId, int userId) {
		// TODO Auto-generated method stub
		trainingMapper.deleteTrainingSection(sectionId, userId);
	}

	/**
	 *
	 * @param section
	 */
	@Override
	public void addTrainingSection(TrainingSection section) {
		// TODO Auto-generated method stub
		trainingMapper.addTrainingSection(section);
	}

	/**
	 *
	 * @param trainingId
	 * @param idList
	 * @param searchStr
	 * @return
	 */
	@Override
	public Map<Integer, List<TrainingSectionProcess>> getTrainingSectionProcessMapByTrainingId(int trainingId,
														List<Integer> idList, String searchStr) {
		// TODO Auto-generated method stub
		if(idList.size() == 0)
			idList = null;
		List<TrainingSectionProcess> processList = trainingMapper.getTrainingSectionProcessListByTrainingId(trainingId, idList,searchStr, null);
		HashMap<Integer,List<TrainingSectionProcess>> map = new HashMap<Integer,List<TrainingSectionProcess>>();
		for(TrainingSectionProcess process : processList){
			List<TrainingSectionProcess> tmpList = new ArrayList<TrainingSectionProcess>();
			if(map.containsKey(process.getUserId()))
				tmpList = map.get(process.getUserId());
			tmpList.add(process);
			map.put(process.getUserId(), tmpList);
		}
		return map;
	}
	@Override
	public List<UserTraining> getUserTrainingList(int trainingId, int userId,String searchStr, Page<UserTraining> page) {
		// TODO Auto-generated method stub
		return trainingMapper.getUserTrainingList(trainingId, userId,searchStr, page);
	}

}
