package cn.mteach.management.controller.action;

import cn.mteach.common.FileConstants;
import cn.mteach.common.SystemConfig;
import cn.mteach.common.UploadFileResponse;
import cn.mteach.common.util.file.FileUploadUtil;
import cn.mteach.management.security.UserInfo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TrainingAction {

	@RequestMapping(value = "/secure/upload-uploadify-file", method = RequestMethod.POST)
	public @ResponseBody String uploadFile(HttpServletRequest request, HttpServletResponse response) {
		UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<String> filePathList = new ArrayList<String>();
		try {
			filePathList = FileUploadUtil.uploadFile(request, response, userInfo.getUsername());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (filePathList.size() == 0) {
			return "系统错误";
		}

		return filePathList.get(0);
	}

	/**
	 * 文件上传
	 * @param request
	 * @param fileType
	 * @return
	 */
	@RequestMapping(value = "/secure/upload-train-file", method = RequestMethod.POST)
	public @ResponseBody List<String> uploadTrainFile(HttpServletRequest request,String fileType) {
		List<String> resultList = new ArrayList<>();
		String fileName;
		try {
			if (!FileConstants.UPLOAD_FILE_SUFFIX.contains(fileType)) {
				throw new Exception("上传的文件类型错误");
			}
			String relPath = SystemConfig.getQuestionUploadPath(fileType);
			UploadFileResponse uploadFileResponse = FileUploadUtil.uploadFile(request, relPath);
			String originalFilename = uploadFileResponse.getFirstOriginalFileName();
//			String strUrl = "http://" + request.getServerName() // 服务器地址
//					+ ":" + request.getServerPort() + "/school-static" + "/";
			//这里的资源路径是服务端上传的路径
			String showPath = SystemConfig.getHttpStaticResourcePath(request);
			fileName = uploadFileResponse.getFirstNewFileName();
			//fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
			resultList.add(originalFilename);
			resultList.add(showPath + relPath + fileName);
			resultList.add("." + fileType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultList;
	}

}
