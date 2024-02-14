package com.example.Create.Notepad.repository;

import com.example.Create.Notepad.entity.Notepad;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface NotepadRepository extends CrudRepository<Notepad, Long> {
    @Override
    ArrayList<Notepad> findAll(); // Iterable -> ArrayList 로 수정
}
