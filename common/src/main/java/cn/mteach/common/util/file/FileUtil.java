package cn.mteach.common.util.file;


import cn.mteach.common.FileConstants;
import cn.mteach.common.util.EncryptUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 廖大剑
 * @version V1.0
 * @Description: 文件工具
 * @Company: 广东全通教育股份有限公司
 * @date 2015/8/20
 */
public class FileUtil {

    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 读取文件内容 ，一次读取多个字节
     *
     * @return 返回文件内容
     */
    public static String readerFileContent(String path) {
        return readerFileContent(path, FileConstants.FILE_ENCODING);
    }

    /**
     * 读取文件内容 ，一次读取多个字节
     * @return 返回文件内容
     */
    public static String readerFileContent(String path, String encoding) {
        String text = null;
        try {
            File file = new File(path);
            if (!file.exists()) {
                throw new RuntimeException("UPLOAD_FILE_NULL_ERROR");
            } else {
                text = FileUtils.readFileToString(file, StringUtils.isBlank(encoding) ? FileConstants.FILE_ENCODING : encoding);
            }
        } catch (Exception e) {
            logger.error("读取文件内容异常：{}，文件{}：", e, path);
        }
        return text;
    }

    /**
     * 读取文件字节内容，并以base64加密返回
     * @param path
     * @return base64加密内容
     * @throws java.io.IOException
     */
    public static String readerFileBytes(String path) throws IOException {

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        //一个字节一个节点读
        FileInputStream is = new FileInputStream(path);
        int len = 0;
        while ((len = is.read()) != -1) {
            bytes.write(len);
        }
        byte[] by = bytes.toByteArray();
        is.close();
        return Base64.encodeBase64String(by);
    }

    /**
     * 将文件字节数组转成原始文件存储在服务器本地
     * @param toFile 保存文件全路径, 包含文件名
     * @param str    Base64加密的文件字节数据
     * @return
     */

    public static String saveByteZipFile(String toFile, String str, String md5a) throws IOException {
        try {
            //存储路径
            String dir = toFile.substring(0, toFile.lastIndexOf(File.separator));
            if (!new File(dir).exists()) {
                FileUtils.forceMkdir(new File(dir));
            }

            //写zip文件
            byte[] bytes = Base64.decodeBase64(str);
            String bakFileName = dir + File.separator + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS") + ".zip";
            File targetFile = new File(bakFileName);
            FileUtils.writeByteArrayToFile(targetFile, bytes);

            //写完后检查写文件是否有数据丢失
            String md5v = EncryptUtil.fileMd5(targetFile);

            if (!md5a.equalsIgnoreCase(md5v)) {

                throw new RuntimeException("接收的文件md5值不正确，可能丢失数据");
            }
            return bakFileName;
        } catch (IOException ex1) {
            ex1.printStackTrace();
            throw ex1;
        }
    }

    /**
     * 将文件字节数组转成原始文件存储在服务器本地
     * @param toFile 保存文件全路径, 包含文件名
     * @param str    Base64加密的文件字节数据
     * @return
     */

    public static String saveByteFile(String toFile, String str, String md5a) throws IOException {
        try {
            //存储路径
            String dir = toFile.substring(0, toFile.lastIndexOf(File.separator));
            if (!new File(dir).exists()) {
                FileUtils.forceMkdir(new File(dir));
            }

            //写zip文件
            byte[] bytes = Base64.decodeBase64(str);
            // String bakFileName = dir + File.separator + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS") + ".zip";
            File targetFile = new File(toFile);
            FileUtils.writeByteArrayToFile(targetFile, bytes);

            // 是否验证md5
            if (StringUtils.isNotBlank(md5a)) {
                //写完后检查写文件是否有数据丢失
                String md5v = EncryptUtil.fileMd5(targetFile);
                if (!md5a.equalsIgnoreCase(md5v)) {
                    throw new RuntimeException("接收的文件md5值不正确，可能丢失数据");
                }
            }
            return toFile;
        } catch (IOException ex1) {
            ex1.printStackTrace();
            throw ex1;
        }
    }

    /**
     * 保存文件
     * @param toFile 文件地址
     * @param str    文件内容
     * @return
     * @throws java.io.IOException
     */
    public static String saveFile(String toFile, String str) throws IOException {
        try {
            //存储路径
            String dir = toFile.substring(0, toFile.lastIndexOf(File.separator));
            if (!new File(dir).exists()) {
                FileUtils.forceMkdir(new File(dir));
            }
            //写文件
            byte[] bytes = str.getBytes();
            File targetFile = new File(toFile);
            FileUtils.writeByteArrayToFile(targetFile, bytes);
            return toFile;
        } catch (IOException ex1) {
            ex1.printStackTrace();
            throw ex1;
        }
    }

    /**
     * 创建目录或文件
     * @param fileOrDirPath 文件绝对路径
     * @param overlay       存在是否覆盖
     * @return 0正常创建文件，1文件已存在
     */
    public static boolean createDirOrFile(String fileOrDirPath, boolean overlay) throws IOException {
        File file = new File(fileOrDirPath);
        //存在覆盖
        if (file.exists() && overlay) {
            file.delete();
        }
        //存在不覆盖，返回false
        else if (file.exists() && !overlay) {
            return false;
        }
        try {
            //创建文件或目录
            String dir = fileOrDirPath.substring(0, fileOrDirPath.lastIndexOf("/"));
            File dirs = new File(dir);
            if (!dirs.isDirectory()) {
                //目录不存在，需创建
                dirs.mkdirs();
            }
            //创建文件
            file.createNewFile();
        } catch (Exception e) {
            logger.error("创建文件或目录失败：{}，文件：{}", e,fileOrDirPath);
            throw e;
        }
        return true;
    }

    /**
     * 创建文件目录
     * @param filePath
     */
    public static boolean createFilePath(String filePath) {
        boolean isCreated = true;
        File file = new File(filePath);
        File fileParent = file.getParentFile();
        if (fileParent != null && !fileParent.exists()) {
            isCreated = fileParent.mkdirs();
        }
        return isCreated;
    }

    /**
     * 删除单个文件
     * @param sPath 被删除文件的路径+文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        Boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     * @param sPath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String sPath) {
        // 如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        Boolean flag = true;
        // 删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            } // 删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag)
            return false;
        // 删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 是否存在目标文件夹
     * @param filePath
     * @return
     */
    public static boolean isExistFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists() && !file.isDirectory()) {
            return false;
        }
        return true;
    }

    /**
     * 复制单个文件
     * @param srcFileName  待复制的文件名
     * @param destFileName 目标文件名
     * @param overlay      如果目标文件存在，是否覆盖
     * @return 如果复制成功返回true，否则返回false
     */
    public static boolean copyFile(String srcFileName, String destFileName, boolean overlay) {
        File srcFile = new File(srcFileName);

        // 判断源文件是否存在
        if (!srcFile.exists()) {
            throw new RuntimeException("源文件：" + srcFileName + "不存在！");
        } else if (!srcFile.isFile()) {
            throw new RuntimeException("复制文件失败，源文件：" + srcFileName + "不是一个文件！");
        }

        // 判断目标文件是否存在
        File destFile = new File(destFileName);
        if (destFile.exists()) {
            // 如果目标文件存在并允许覆盖
            if (overlay) {
                // 删除已经存在的目标文件，无论目标文件是目录还是单个文件
                new File(destFileName).delete();
            }
        } else {
            // 如果目标文件所在目录不存在，则创建目录
            if (!destFile.getParentFile().exists()) {
                // 目标文件所在目录不存在
                if (!destFile.getParentFile().mkdirs()) {
                    // 复制文件失败：创建目标文件所在目录失败
                    return false;
                }
            }
        }

        // 复制文件
        int byteread = 0; // 读取的字节数
        InputStream in = null;
        OutputStream out = null;

        try {
            in = new FileInputStream(srcFile);
            out = new FileOutputStream(destFile);
            byte[] buffer = new byte[1024];

            while ((byteread = in.read(buffer)) != -1) {
                out.write(buffer, 0, byteread);
            }
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        } finally {
            try {
                if (out != null)
                    out.close();
                if (in != null)
                    in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 复制整个目录的内容
     *
     * @param srcDirName  待复制目录的目录名
     * @param destDirName 目标目录名
     * @param overlay     如果目标目录存在，是否覆盖
     * @return 如果复制成功返回true，否则返回false
     */
    public static boolean copyDirectory(String srcDirName, String destDirName, boolean overlay) {
        // 判断源目录是否存在
        File srcDir = new File(srcDirName);
        if (!srcDir.exists()) {
            throw new RuntimeException("复制目录失败：源目录" + srcDirName + "不存在！");
        } else if (!srcDir.isDirectory()) {
            throw new RuntimeException("复制目录失败：" + srcDirName + "不是目录！");
        }

        // 如果目标目录名不是以文件分隔符结尾，则加上文件分隔符
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        File destDir = new File(destDirName);
        // 如果目标文件夹存在
        if (destDir.exists()) {
            // 如果允许覆盖则删除已存在的目标目录
            if (overlay) {
                new File(destDirName).delete();
            } else {
               throw new RuntimeException("复制目录失败：目的目录" + destDirName + "已存在！");
            }
        } else {
            // 创建目的目录
            if (!destDir.mkdirs()) {
                System.out.println("复制目录失败：创建目的目录失败！");
                return false;
            }
        }

        boolean flag = true;
        File[] files = srcDir.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 复制文件
            if (files[i].isFile()) {
                flag = copyFile(files[i].getAbsolutePath(),
                        destDirName + files[i].getName(), overlay);
                if (!flag)
                    break;
            } else if (files[i].isDirectory()) {
                flag = copyDirectory(files[i].getAbsolutePath(),
                        destDirName + files[i].getName(), overlay);
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            throw new RuntimeException("复制目录" + srcDirName + "至" + destDirName + "失败！");
        } else {
            return true;
        }
    }

    /**
     * 获取文件大小
     *
     * @param filePath
     * @return
     * @throws java.io.IOException
     */
    public static String getFileSize(String filePath) throws IOException {
        String fileSize;
        FileInputStream fis = null;
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                logger.error("获取文件大小异常，文件不存在：" + filePath);
                throw new RuntimeException("获取文件大小异常，文件不存在：" + filePath);
            }
            fis = new FileInputStream(f);
            fileSize = formetFileSize(fis.available());
        } catch (Exception e) {
            throw e;
        } finally {
            if (null != fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    logger.error("获取文件大小异常：", e);
                }
            }
        }
        return fileSize;
    }

    /**
     * 文件大小转换
     *
     * @param fileS
     * @return
     */
    private static String formetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString;
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * 获取文件md5值
     *
     * @param filePath 文件路径
     * @return
     */
    public static String getMd5ByFile(String filePath) throws FileNotFoundException {
        String value = null;
        File file = new File(filePath);
        if (!file.exists()) {
            logger.error("文件大小转换，文件不存在：" + filePath);
            throw new RuntimeException("获取文件大小异常，文件不存在：" + filePath);
        }
        FileInputStream in = new FileInputStream(file);
        try {
            MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

    /**
     * 获取网络文件并保存到本机指定目录(文件名加－时间戳yyyyMMddkkmmss)
     *
     * @param url      网络文件路径，文件名为中文需要调用此方法前进行urlEncode
     * @param fileName 文件名称(带后缀)
     * @param saveDir  保存地址，不加文件名
     * @return
     */
    public static void downloadFromUrl(String url, String fileName, String saveDir) {
        try {
            URL httpUrl = new URL(url);
            File f = new File(saveDir + URLDecoder.decode(fileName, FileConstants.FILE_ENCODING));
            FileUtils.copyURLToFile(httpUrl, f);
        } catch (FileNotFoundException nfe) {
            logger.error("获取网络文件异常，文件不存在：", nfe);
            throw new RuntimeException("UPLOAD_FILE_NULL_ERROR");
        } catch (Exception e) {
            logger.error("获取网络文件异常：", e);
            throw new RuntimeException("UPLOAD_FILE_NULL_ERROR");
        }
    }

    /**
     * 遍历目录及其子目录下的所有文件并保存
     *
     * @param dir    目录全路径
     * @param myfile 列表：保存文件路径
     */
    public static void listDirectory(File dir, List<String> myfile) {
        if (!dir.exists()) {
            System.out.println("文件名称不存在!");
        } else {
            if (dir.isFile()) {
                myfile.add(dir.getPath());
            } else {
                File[] files = dir.listFiles();
                for (int i = 0; i < files.length; i++) {
                    listDirectory(files[i], myfile);
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            String a = "AA";
            if ("AA".equals(a)){
                throw new RuntimeException("test runtimeexception");
            }
            System.out.println(a);
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 文件下载
     *
     * @param response
     * @param file
     */
    public static void download(HttpServletRequest request ,HttpServletResponse response, String file, String fileName) {
        try {
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            OutputStream fos = null;
            InputStream fis = null;

            File uploadFile = new File(file);
            fis = new FileInputStream(uploadFile);
            bis = new BufferedInputStream(fis);
            fos = response.getOutputStream();
            bos = new BufferedOutputStream(fos);
            // 这个就就是弹出下载对话框的关键代码
            String userAgent = request.getHeader("User-Agent");
            if (userAgent.toUpperCase().indexOf("MSIE") > 0 || userAgent.toUpperCase().indexOf("LIKE GECKO") > 0) {
                fileName = URLEncoder.encode(fileName, "UTF-8");
            } else {
                fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
            }
            response.setHeader("Content-disposition", "attachment;filename="+ fileName);
            int bytesRead = 0;
            // 这个地方的同上传的一样。我就不多说了，都是用输入流进行先读，然后用输出流去写，唯一不同的是我用的是缓冲输入输出流
            byte[] buffer = new byte[8192];
            while ((bytesRead = bis.read(buffer, 0, 8192)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            bos.flush();
            fis.close();
            bis.close();
            fos.close();
            bos.close();
        } catch (Exception e) {
            logger.error("下载文件[" + fileName + "]", e);
        }
    }

}
