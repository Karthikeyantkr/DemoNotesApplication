package com.example.demo.note;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/** @author Karthikeyan.T K Ragunath tkrkarthikeyan@outlook.com */
@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {

  @Query("SELECT n FROM Note n WHERE n.content LIKE %?1%")
  public List<Note> search(String keyword);
}
