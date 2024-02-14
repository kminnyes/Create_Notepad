package com.example.Create.Notepad.dto;

import com.example.Create.Notepad.entity.Notepad;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class NotepadForm {

    private Long id;
    private String title;
    private String content;

    public Notepad toEntity() {
        return new Notepad(id, title, content);
    }
}

