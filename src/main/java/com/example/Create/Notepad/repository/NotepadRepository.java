package com.example.Create.Notepad.repository;

import com.example.Create.Notepad.entity.Notepad;
import org.springframework.data.repository.CrudRepository;

public interface NotepadRepository extends CrudRepository<Notepad, Long> {
}
