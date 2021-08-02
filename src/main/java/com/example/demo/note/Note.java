package com.example.demo.note;

import javax.persistence.Entity;
import javax.persistence.Id;

/** @author Karthikeyan.T K Ragunath tkrkarthikeyan@outlook.com */
@Entity
public class Note {

  @Id private Integer id;

  private String content;

  protected Note() {}

  public Note(Integer id, String content) {
    super();
    this.id = id;
    this.content = content;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
