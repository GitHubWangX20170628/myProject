package com.example.demo.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="ay_mood")
public class AyMood implements Serializable{
  @Id
  private String id;//主键
  private String content;//说说内容
  private String userId;//用户id
  private Integer praiseNum;//点赞数量
  private Date publishTime;//发表时间


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public Integer getPraiseNum() {
    return praiseNum;
  }

  public void setPraiseNum(Integer praiseNum) {
    this.praiseNum = praiseNum;
  }

  public Date getPublishTime() {
    return publishTime;
  }

  public void setPublishTime(Date publishTime) {
    this.publishTime = publishTime;
  }
}
