package com.example.Create.Notepad.controller;

import ch.qos.logback.classic.Logger;
import com.example.Create.Notepad.dto.NotepadForm;
import com.example.Create.Notepad.entity.Notepad;
import com.example.Create.Notepad.repository.NotepadRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

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
        return "redirect:/notepad/" + saved.getId();
    }

    @GetMapping("/notepad/{id}")
    public String detail(@PathVariable Long id, Model model){
        log.info("id =" + id);
        //id 조회 데이터 가져오기
        Notepad notepadEntity = notepadRepository.findById(id).orElse(null);
        // 모델에 데이터 등록
        model.addAttribute("notepad", notepadEntity);
        // 뷰 페이지 반환
        return "notepad/detail";
    }

    @GetMapping("/notepad")
    public String index(Model model){
        ArrayList<Notepad> notepadEntityList = notepadRepository.findAll();
        model.addAttribute("notepadList", notepadEntityList);
        return "notepad/index";
    }

    @GetMapping("/notepad/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        // 수정할 데이터 가져오기
        Notepad notepadEntity = notepadRepository.findById(id).orElse(null);
        //데이터 등록
        model.addAttribute("notepad", notepadEntity);
        return "notepad/edit";
    }

    @PostMapping("/notepad/update")
    public String update(NotepadForm form){
        log.info(form.toString());
        Notepad notepadEntity = form.toEntity();
        log.info(form.toString());

        Notepad target = notepadRepository.findById(notepadEntity.getId()).orElse(null);
        if(target != null){
            notepadRepository.save(notepadEntity);
        }
        return "redirect:/notepad/" + notepadEntity.getId();
    }

    @GetMapping("/notepad/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes tr){
        log.info("삭제 요청!");
        Notepad target = notepadRepository.findById(id).orElse(null);
        log.info(target.toString());

        //엔티티 삭제
        if(target != null){
            notepadRepository.delete(target);
            tr.addFlashAttribute("msg", "삭제되었습니다.");
        }

        return "redirect:/notepad";
    }
}
