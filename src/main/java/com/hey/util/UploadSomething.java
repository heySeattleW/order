package com.hey.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created by Administrator on 2016/12/7.
 */
public class UploadSomething {

    //上传单张图片
    public static String uploadImg(String path,MultipartFile img, String dir){
        File dirs = new File(path);
        String name = "";
        System.out.print(img.getContentType()+"格式啊啊啊");
        if(!dirs.exists()){
            dirs.mkdirs();
        }else {
                String imgName = img.getOriginalFilename();
                String imgMD5 = Md5Util.MD5(imgName + UUIDUtil.uuid());
                String suffix = img.getOriginalFilename().substring(img.getOriginalFilename().lastIndexOf("."));
                File file = new File(path + "/" + imgMD5 + suffix);
                if(!file.exists()){
                    try {
                        file.createNewFile();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                try {
                    img.transferTo(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                name = imgMD5 + suffix;
            }
        return name;//返回图片存放的相对路径
    }

    //上传多张图片,返回一个图片相对路径的数组
    public static String[] uploadImgs(String path, MultipartFile[] imgs, String dir){
        File dirs = new File(path);
        String[] name={};
        if (!dirs.exists()) {
            dirs.mkdirs();

        }
        int i=0;
        for (MultipartFile img : imgs) {
            String imgName = img.getOriginalFilename();
            String imgMD5 = Md5Util.MD5(imgName + UUIDUtil.uuid());
            String suffix = img.getOriginalFilename().substring(img.getOriginalFilename().lastIndexOf("."));
            File file = new File(path + "/" + imgMD5 + suffix);
            file.exists();
            try {
                img.transferTo(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
            name[i] = dir + "/"+ imgMD5 + suffix+",";
            i++;
        }
        return name;//返回图片存放的相对路径
    }

    //上传视频(目前只支持上传MP4)
    public static String uploadVideo(String path, MultipartFile video, String dir)throws Exception{
        File dirs = new File(path);
        String name="";
        if(!dirs.exists()){
            dirs.mkdirs();
        }else {
            String imgName = video.getOriginalFilename();
            String imgMD5 = Md5Util.MD5(imgName + UUIDUtil.uuid());
            String suffix = video.getOriginalFilename().substring(video.getOriginalFilename().lastIndexOf("."));
            File file = new File(path + "/" + imgMD5 + suffix);
            file.exists();
            try {
                video.transferTo(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
            name = dir + imgMD5 + suffix;
        }
        return name;//返回视频存放的相对路径
    }


    //上传录音
    public static String uploadMusic(String path, MultipartFile img, String dir)throws Exception{
        File dirs = new File(path);
        String name = "";
        String imgMD5 = "";
        String xx = img.getContentType().toString();
        System.out.print("音乐格式"+xx);
        if(!dirs.exists()){
            dirs.mkdirs();
        }else {
            String imgName = img.getOriginalFilename();
            imgMD5 = Md5Util.MD5(imgName + UUIDUtil.uuid());
            String suffix = img.getOriginalFilename().substring(img.getOriginalFilename().lastIndexOf("."));
            File file = new File(path + "/" + imgMD5 + suffix);
            if(!file.exists()){
                file.createNewFile();
            }
            try {
                img.transferTo(file);
            } catch (Exception e) {
                e.printStackTrace();
            }

            name = dir + imgMD5 + suffix;
        }
        return name+"#"+xx+"#"+imgMD5;//返回图片存放的相对路径
    }

    //上传歌词文件
    public static String uploadLrc(String path, MultipartFile img, String dir)throws Exception{
        File dirs = new File(path);
        String name = "";
        if(!dirs.exists()){
            dirs.mkdirs();
        }else {
            String imgName = img.getOriginalFilename();
            String imgMD5 = Md5Util.MD5(imgName + UUIDUtil.uuid());
            String suffix = img.getOriginalFilename().substring(img.getOriginalFilename().lastIndexOf("."));
            File file = new File(path + "/" + imgMD5 + suffix);
            file.exists();
            try {
                img.transferTo(file);
            } catch (Exception e) {
                e.printStackTrace();
            }

            name = dir + imgMD5 + suffix;
        }
        return name;//返回图片存放的相对路径
    }

}