package com.example.bq.edmp.activity.apply;

import android.os.Parcel;
import android.text.TextUtils;

import java.io.Serializable;

/**
 * author：luck
 * project：PictureSelector
 * package：com.luck.picture.lib.entity
 * describe：for PictureSelector media entity.
 * email：893855882@qq.com
 * data：2017/5/24
 */

public class LocalNewMedia implements Serializable {
    private String path;
    private String compressPath;
    private String cutPath;
    private long duration;
    private boolean isChecked;
    private boolean isCut;
    public int position;
    private int num;
    private int mimeType;
    private String pictureType;
    private boolean compressed;
    private int width;
    private int height;

    public LocalNewMedia() {

    }

    public LocalNewMedia(String path, long duration, int mimeType, String pictureType) {
        this.path = path;
        this.duration = duration;
        this.mimeType = mimeType;
        this.pictureType = pictureType;
    }

    public LocalNewMedia(String path, long duration, int mimeType, String pictureType, int width, int height) {
        this.path = path;
        this.duration = duration;
        this.mimeType = mimeType;
        this.pictureType = pictureType;
        this.width = width;
        this.height = height;
    }

    public LocalNewMedia(String path, long duration,
                         boolean isChecked, int position, int num, int mimeType) {
        this.path = path;
        this.duration = duration;
        this.isChecked = isChecked;
        this.position = position;
        this.num = num;
        this.mimeType = mimeType;
    }

    public String getPictureType() {
        if (TextUtils.isEmpty(pictureType)) {
            pictureType = "image/jpeg";
        }
        return pictureType;
    }

    public void setPictureType(String pictureType) {
        this.pictureType = pictureType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCompressPath() {
        return compressPath;
    }

    public void setCompressPath(String compressPath) {
        this.compressPath = compressPath;
    }

    public String getCutPath() {
        return cutPath;
    }

    public void setCutPath(String cutPath) {
        this.cutPath = cutPath;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }


    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isCut() {
        return isCut;
    }

    public void setCut(boolean cut) {
        isCut = cut;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getMimeType() {
        return mimeType;
    }

    public void setMimeType(int mimeType) {
        this.mimeType = mimeType;
    }

    public boolean isCompressed() {
        return compressed;
    }

    public void setCompressed(boolean compressed) {
        this.compressed = compressed;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }



    protected LocalNewMedia(Parcel in) {
        this.path = in.readString();
        this.compressPath = in.readString();
        this.cutPath = in.readString();
        this.duration = in.readLong();
        this.isChecked = in.readByte() != 0;
        this.isCut = in.readByte() != 0;
        this.position = in.readInt();
        this.num = in.readInt();
        this.mimeType = in.readInt();
        this.pictureType = in.readString();
        this.compressed = in.readByte() != 0;
        this.width = in.readInt();
        this.height = in.readInt();
    }

}
