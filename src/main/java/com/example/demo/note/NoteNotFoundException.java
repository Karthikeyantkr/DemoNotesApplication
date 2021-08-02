package com.example.demo.note;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/** @author Karthikeyan.T K Ragunath tkrkarthikeyan@outlook.com */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoteNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public NoteNotFoundException(String message) {
    super(message);
  }
}
