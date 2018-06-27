package com.hey.entity;

import java.sql.Timestamp;

/**
 * Created by heer on 2018/6/20.
 */
public class Image extends BaseEntity {

    private String imageUrl;

    private String imagePath;

    private String imageMd5;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageMd5() {
        return imageMd5;
    }

    public void setImageMd5(String imageMd5) {
        this.imageMd5 = imageMd5;
    }

    public Image(String imageUrl, String imagePath, String imageMd5) {
        this.imageUrl = imageUrl;
        this.imagePath = imagePath;
        this.imageMd5 = imageMd5;
    }

    public Image(Long id, String update_time, String imageUrl, String imagePath, String imageMd5) {
        super(id, update_time);
        this.imageUrl = imageUrl;
        this.imagePath = imagePath;
        this.imageMd5 = imageMd5;
    }

    public Image() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"imageUrl\":\"")
                .append(imageUrl).append('\"');
        sb.append(",\"imagePath\":\"")
                .append(imagePath).append('\"');
        sb.append(",\"imageMd5\":\"")
                .append(imageMd5).append('\"');
        sb.append(",\"id\":")
                .append(id);
        sb.append(",\"update_time\":\"")
                .append(update_time).append('\"');
        sb.append('}');
        return sb.toString();


    }

}
