package com.example.demo.note;

import static org.hamcrest.CoreMatchers.is;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

/** @author Karthikeyan.T K Ragunath tkrkarthikeyan@outlook.com */
@RunWith(SpringRunner.class)
@DataJpaTest
public class JPAUnitTest {

  @Autowired private TestEntityManager entityManager;

  @Autowired NoteRepository repository;

  @Test
  public void should_create_a_note() {
    Note note = repository.save(new Note(1, "Note1"));
    Assert.assertNotNull(note.getId());
    Assert.assertEquals("Note1", note.getContent());
  }

  @Test
  public void should_find_all_notes() {
    Note note1 = new Note(1, "note1");
    entityManager.persist(note1);

    Note note2 = new Note(2, "note2");
    entityManager.persist(note2);

    Note note3 = new Note(3, "note3");
    entityManager.persist(note3);

    List<Note> notes = repository.findAll();

    Assert.assertEquals(3, notes.size());
  }

  @Test
  public void should_find_note_by_id() {
    Note note1 = new Note(1, "note1");
    entityManager.persist(note1);

    Note note2 = new Note(2, "note2");
    entityManager.persist(note2);

    Note foundNote = repository.findById(note2.getId()).get();

    Assert.assertEquals(foundNote, note2);
  }

  @SuppressWarnings("deprecation")
  @Test
  public void should_search_notes_by_keyword() {
    Note note1 = new Note(1, "note1");
    entityManager.persist(note1);

    Note note2 = new Note(2, "note2");
    entityManager.persist(note2);

    Note note3 = new Note(3, "three");
    entityManager.persist(note3);

    List<Note> notes = repository.search("note");

    Assert.assertThat(notes.size(), is(2));
  }

  @Test
  public void should_update_note_by_id() {
    Note note1 = new Note(1, "note1");
    entityManager.persist(note1);

    Note note2 = new Note(2, "note2");
    entityManager.persist(note2);
  }

  @SuppressWarnings("deprecation")
  @Test
  public void should_delete_note_by_id() {
    Note note1 = new Note(1, "note1");
    entityManager.persist(note1);

    repository.deleteById(note1.getId());

    List<Note> notes = repository.findAll();

    Assert.assertThat(notes.size(), is(0));
  }
}
