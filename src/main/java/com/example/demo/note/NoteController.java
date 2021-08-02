package com.example.demo.note;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for the end points related to note server
 *
 * @author Karthikeyan.T K Ragunath tkrkarthikeyan@outlook.com
 */
@RestController
@RequestMapping("/api/notes")
public class NoteController {
  private static final String REQUEST_NO_BODY = "Request does not contain a body";

  @Autowired private NoteRepository noteRepository;

  /**
   * To get all notes based on keyword or return all notes if keyword not found
   *
   * @return List of note object
   */
  @GetMapping()
  public List<Note> retrieveAllNotes(@Param("keyword") String keyword) {
    if (keyword != null) {
      return noteRepository.search(keyword);
    }

    return noteRepository.findAll();
  }

  /**
   * To get a note based on id
   *
   * @param id note id
   * @return ReponseEntity of note object
   */
  @GetMapping("{id}")
  public ResponseEntity<Note> retrieveNote(@PathVariable int id) {
    Optional<Note> note = noteRepository.findById(id);

    if (note.isPresent()) {
      return ResponseEntity.ok().body(note.get());
    } else {
      throw new NoteNotFoundException("Note not found, please try a different id");
    }
  }

  /**
   * To create a note
   *
   * @param note object of a note
   * @return Note object of a note
   */
  @PostMapping("")
  public String createNote(@Valid @RequestBody Note note) {
    if (!noteRepository.existsById(note.getId())) {
      noteRepository.save(note);
      return "Note created";
    } else {
      return "Note Id already exists";
    }
  }

  /**
   * To create multiple notes
   *
   * @param note object of a note
   * @return String size of note added
   */
  @PostMapping("bulk")
  public String createNote(@Valid @RequestBody List<Note> note) {
    if (note != null && !note.isEmpty()) {
      noteRepository.saveAll(note);
      return String.format("Added %d note", note.size());
    } else {
      return REQUEST_NO_BODY;
    }
  }

  /**
   * To update a note
   *
   * @param note object of Note
   * @return Note object of Note
   */
  @PutMapping("")
  public String updateNote(@Valid @RequestBody Note note) {
    if (noteRepository.existsById(note.getId())) {
      noteRepository.save(note);
      return "Note udpated";
    } else {
      return "Note Id does not exist";
    }
  }

  /**
   * To update one or more note(s)
   *
   * @param note object of Note
   * @return Note object of Note
   */
  @PutMapping("bulk")
  public String updateNote(@RequestBody List<Note> notes) {
    if (notes != null) {
      noteRepository.saveAll(notes);
      return "Updated note(s)";
    } else {
      return REQUEST_NO_BODY;
    }
  }

  /**
   * To delete one or more notes based on ids passed
   *
   * @param ids ids to delete note(s)
   */
  @DeleteMapping("{ids}")
  public String deleteNote(@PathVariable List<Integer> ids) {
    if (ids != null && !ids.isEmpty()) {
      try {
        noteRepository.deleteAllById(ids);
      } catch (Exception e) {
        return "Deletion Failed / Note id does not exist";
      }

      return "Deleted note(s)";
    } else {
      return REQUEST_NO_BODY;
    }
  }

  /**
   * To delete a note by entity
   *
   * @param note object of Note
   * @return Note object of Note
   */
  @DeleteMapping("")
  public String deleteNote(@Valid @RequestBody Note note) {
    if (noteRepository.existsById(note.getId())) {
      noteRepository.delete(note);
      return "Note deleted";
    } else {
      return "Note Id does not exist";
    }
  }
}
