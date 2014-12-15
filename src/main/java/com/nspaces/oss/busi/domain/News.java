package com.nspaces.oss.busi.domain;

import java.io.Serializable;
import java.util.Date;

import javax.xml.soap.Text;


public class News implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String title;// 标题
	private String author;// 作者
	private String digest;// 摘要
	private String img;// 图片
	private String publishDate;// 发布日期
	private String keyword;// 关键字
	private String content;// 内容
	private String publishStatus;// 发布状态 0 发布 1 关闭


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(String publishStatus) {
        this.publishStatus = publishStatus;
    }
}
