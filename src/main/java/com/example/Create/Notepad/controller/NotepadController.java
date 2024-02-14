package com.example.Create.Notepad.controller;

import ch.qos.logback.classic.Logger;
import com.example.Create.Notepad.dto.NotepadForm;
import com.example.Create.Notepad.entity.Notepad;
import com.example.Create.Notepad.repository.NotepadRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class NotepadController {

    @Autowired
    private NotepadRepository notepadRepository;

    //메모 생성
    @GetMapping("/notepad/write")
    public String writeMemo(){
        return "notepad/create";
    }

    @PostMapping("/notepad/create")
    public String createMemo(NotepadForm form){
        log.info(form.toString());
        // dto를 엔티티로 변환
        Notepad notepad = form.toEntity();
        log.info(notepad.toString());
        // 리포지토리로 엔티티를 DB에 저장
        Notepad saved = notepadRepository.save(notepad);
        log.info(saved.toString());
        return "";
    }

}
