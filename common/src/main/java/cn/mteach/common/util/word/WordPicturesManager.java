package cn.mteach.common.util.word;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;

import java.io.*;
import java.util.List;

/**
 * 保存图片，并返回图片的相对路径
 * Created by Sugior on 2016/12/5.
 */
public class WordPicturesManager implements PicturesManager {
    /**
     * imageURL 图片在html文件中的相对路径
     */
    private String imageURL = "images";
    /**
     * ImageBasePath 图片保存的基本目录
     */
    private String ImageBasePath = "";

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImageBasePath() {
        return ImageBasePath;
    }

    public void setImageBasePath(String imageBasePath) {
        ImageBasePath = imageBasePath;
    }


    public WordPicturesManager(String imagesSavePath,String imageURLResolver) {
        this.ImageBasePath = imagesSavePath;
        this.imageURL =imageURLResolver;
    }

    public WordPicturesManager(String imagesSavePath) {
        this.ImageBasePath = imagesSavePath;
    }

    @Override
    public String savePicture(byte[] content, PictureType pictureType,
                              String suggestedName, float widthInches, float heightInches) {
        String imageSaveFile = ImageBasePath + File.separator +imageURL+File.separator+ suggestedName;
        savePicetureToFile(content,imageSaveFile);
        return imageURL +"/"+ suggestedName;
    }

    public static void savePicetureToFile(byte[] picContent,String picFile){
        try {
            FileUtils.writeByteArrayToFile( new File(picFile),picContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void saveDocPictureList(List<Picture> pics,String imageSavePath){
        if(pics!=null){
            for (Picture pic : pics) {
                byte[] bytePictureContent = pic.getContent();
                if(bytePictureContent != null) {
                    String separatorStr=(imageSavePath.endsWith("/")||imageSavePath.endsWith("\\"))?"":File.separator;
                    String picOutFile = imageSavePath + separatorStr + pic.suggestFullFileName();
                    savePicetureToFile(bytePictureContent,picOutFile);
                }
            }
        }
    }

    public static void saveDocxPictureList(List<XWPFPictureData> pics,String imageSavePath){
       if(pics != null){
           for (XWPFPictureData pic : pics) {
               byte[] bytePictureContent = pic.getData();
               if(bytePictureContent != null) {
                   String separatorStr=(imageSavePath.endsWith("/")||imageSavePath.endsWith("\\"))?"":File.separator;
                   String picOutFile = imageSavePath + separatorStr + pic.getFileName();
                   savePicetureToFile(bytePictureContent,picOutFile);
               }
           }
       }
    }
}
