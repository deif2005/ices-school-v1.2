package cn.mteach.common.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.mteach.common.SystemConfig;
import cn.mteach.common.UploadFileResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * 上传文件
 * @author scar
 *
 */
public class FileUploadUtil {

    private static Log log = LogFactory.getLog(FileUploadUtil.class);
	public static List<String> uploadFile(HttpServletRequest request, 
			HttpServletResponse response, String username) throws FileNotFoundException{
		List<String> filePathList = new ArrayList<String>();
		
		String strPath = ",webapps,files,training," + username;
		
		String filepath = System.getProperty("catalina.base") + strPath.replace(',', File.separatorChar);
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		String fileName = null;
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {

			MultipartFile mf = entity.getValue();
			fileName = mf.getOriginalFilename();
			String fileType = fileName.substring(fileName.lastIndexOf('.'));
			try {
				String newFileName = MD5FileUtil.getMD5String(mf.getBytes());
				String newfilepath;
				newfilepath = filepath + File.separatorChar + newFileName + fileType;
				String filepathUrl = "files" + File.separatorChar + "training" + File.separatorChar + username + File.separatorChar + newFileName + fileType;
				
				System.out.println("newfilepath=" + newfilepath);
				File dest = new File(filepath);
				if(!dest.exists()){
					dest.mkdirs();
				}
				File uploadFile = new File(newfilepath);
				if(uploadFile.exists()){
					uploadFile.delete();
				}
				log.info("start upload file: " + fileName);
				FileCopyUtils.copy(mf.getBytes(), uploadFile);
				filePathList.add(filepathUrl);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.info("upload failed. filename: " + fileName + e.getMessage());
				return null;
			}
			
			
		}
		
		return filePathList;
	}
	
	public static List<String> uploadQuestionFile(HttpServletRequest request, 
			HttpServletResponse response, String username) throws FileNotFoundException{
		List<String> filePathList = new ArrayList<String>();
		
		String strPath = ",webapps,files,question," + username + ",tmp";
		
		String filepath = System.getProperty("catalina.base") + strPath.replace(',', File.separatorChar);
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		String fileName = null;
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {

			MultipartFile mf = entity.getValue();
			fileName = mf.getOriginalFilename();
			String fileType = fileName.substring(fileName.lastIndexOf('.'));
			try {
				//String filepathUrl = "files" + File.separatorChar + "question" + File.separatorChar + username + File.separatorChar + "tmp" + File.separatorChar + newFileName + fileType;
				
				File dest = new File(filepath);
				if(!dest.exists()){
					dest.mkdirs();
				}
				File uploadFile = new File(filepath + File.separatorChar + fileName);
				if(uploadFile.exists()){
					uploadFile.delete();
				}
				log.info("start upload file: " + fileName);
				FileCopyUtils.copy(mf.getBytes(), uploadFile);
				filePathList.add(filepath + File.separatorChar + fileName);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.info("upload failed. filename: " + fileName + e.getMessage());
				return null;
			}
			
			
		}
		
		return filePathList;
	}
	
	public static List<String> uploadImg(HttpServletRequest request, 
			HttpServletResponse response, String username) throws Exception{
		List<String> filePathList = new ArrayList<String>();

		String strPath = ",webapps,files,question," + username;
		
		String filepath = System.getProperty("catalina.base") + strPath.replace(',', File.separatorChar);
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		String fileName = null;
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {

			MultipartFile mf = entity.getValue();
			fileName = mf.getOriginalFilename();
			String file_type = fileName.substring(fileName.indexOf('.'));
			if(!".jpg".equals(file_type.toLowerCase()) && !".png".equals(file_type.toLowerCase()))
				throw new Exception("文件类型错误");
			fileName = String.valueOf(new Date().getTime()) + file_type;
			String newfilepath;
			newfilepath = filepath + File.separatorChar + fileName;
			String filepathUrl = "files" + File.separatorChar + "question" + File.separatorChar + username + File.separatorChar + fileName;

			System.out.println("newfilepath=" + newfilepath);
			File dest = new File(filepath);
			if(!dest.exists()){
				dest.mkdirs();
			}
			File uploadFile = new File(newfilepath);
			if(uploadFile.exists()){
				uploadFile.delete();
			}
			try {

				log.info("start upload file: " + fileName);
				FileCopyUtils.copy(mf.getBytes(), uploadFile);
			} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
				log.info("upload failed. filename: " + fileName + e.getMessage());
				return null;
			}
			filePathList.add(filepathUrl);
			
		}
		
		return filePathList;
	}

	/**
	 * 上传文件方法，支持多文件同时上传
	 * @param request
	 * @param updateDir 上传文件的相对路径，如（/paper/）
	 * @return (返回代码说明：0=上传成功；1=没有文件上传；2=上传失败；3=上传异常；4=上传文件类型错误)
	 */
	public static UploadFileResponse uploadFile(HttpServletRequest request, String updateDir) throws IOException {
		UploadFileResponse resp = new UploadFileResponse();
		//List<String> fileList = new ArrayList<>();
		List<Map<String,String>> newFileListMap =  new ArrayList<>();
		List<String> fileNameList = new ArrayList<>();
		//List<Map<String,String>> oldFileListMap = new ArrayList<>();
		try {
			String basePath = SystemConfig.getStaticResourcePath(request);
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			multipartResolver.setDefaultEncoding("utf-8");
			//判断 request 是否有文件上传,即多部分请求
			if (multipartResolver.isMultipart(request)) {
				//转换成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				//取得request中的所有文件名
				Iterator<String> iter = multiRequest.getFileNames();
				int count = 0;
				while (iter.hasNext()) {
					count++;
					//取得上传文件
					MultipartFile file = multiRequest.getFile(iter.next());
					if (file != null) {
						//取得当前上传文件的文件名称
						String myFileName = file.getOriginalFilename();
						//如果名称不为“”,说明该文件存在，否则说明该文件不存在
						if (myFileName.trim() != "") {
							//获取文件后缀
							String suffix = myFileName.substring(myFileName.lastIndexOf("."));
							//上传文件名使用uuid
							myFileName = UUID.randomUUID().toString() + suffix;//+ "-" + file.getOriginalFilename()
							//获取完整服务器上传文件路径(上传目录根路径+相对路径+文件名称)
							String uploadServerPath = basePath + updateDir +  myFileName;
							File localFile = new File(uploadServerPath);
							if (!localFile.exists()) {
								localFile.mkdirs();
							}
							if (localFile.isFile()) {
								localFile.delete();
							}
							try {
								file.transferTo(localFile);
							} catch (IOException e) {
								throw e;
							}
							//增加至返回文件列表中
							Map<String,String> newFileMap = new HashMap<>();
							newFileMap.put(myFileName,uploadServerPath);
							newFileListMap.add(newFileMap);
							//增加至返回文件名称列表中
							fileNameList.add(file.getOriginalFilename());
						}
					}
				}
				//没有文件上传
				if (count == 0) {
					return null;
				}
				log.debug("上传文件成功---------------[" + newFileListMap + "]");
			}
			//不存在文件流
			else {
				return null;
			}
		} catch (Exception e) {
			throw e;
		}
		resp.setFileList(newFileListMap);
		resp.setFileNameList(fileNameList);
		return resp;
	}
	
	public static void copyFile(String oldPath, String newPath) { 
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { //文件存在时 
				InputStream inStream = new FileInputStream(oldPath); //读入原文件 
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[2000];
				while ( (byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; //字节数 文件大小 
					fs.write(buffer, 0, byteread);
				}
				fs.close();
				inStream.close();
			}
		}catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();
			return;
		}
		File oldfile = new File(oldPath);
		oldfile.delete();
	}
}
