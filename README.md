## 메모장 만들기(Notepad)
+ 메모장 환경 제공 웹애플리케이션
+ 2024.01.28 ~ 2024.02.11


## 사용 기술 및 환경
+ JAVA 17
+ Spring Boot 3
+ JPA
+ H2 Database
+ Mustache
+ HTML
+ Bootstrap
+ IntelliJ
+ Window


## 구현 기능
+ CRUD 기능 구현


## 주요 코드
#### MVC 패턴 적용

+ Controller

```
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
        // 리포지토리로 엔티티 DB에 저장
        Notepad saved = notepadRepository.save(notepad);
        log.info(saved.toString());
        return "redirect:/notepad/" + saved.getId();
```

+ Model
```
@GetMapping("/notepad/{id}")
    public String detail(@PathVariable Long id, Model model){
        log.info("id =" + id);
        Notepad notepadEntity = notepadRepository.findById(id).orElse(null);
        model.addAttribute("notepad", notepadEntity);
        // 모델에 데이터 등록
        return "notepad/detail";
    }
```

+ View
```
<body>
<h1>
    <div class="mb-3"></div>
    <div style="text-align:center">메모</div>
</h1>

<table class="table">
    <thead>
    <tr>
        <th scope="col">Id</th>
        <th scope="col">Title</th>
        <th scope="col">Content</th>
    </tr>
    </thead>
    <tbody>
    {{#notepad}}
        <tr>
            <th>{{id}}</th>
            <td>{{title}}</td>
            <td>{{content}}</td>
        </tr>
    {{/notepad}}
    </tbody>
</table>
    <a href="/notepad/{{notepad.id}}/edit" class="btn btn-dark">수정</a>
    <a href="/notepad/{{notepad.id}}/delete" class="btn btn-dark">삭제</a>
<div style="text-align:right">
    <a href="/notepad">목록으로 돌아가기</a>
</div>
</body>
```
## 구현 화면
+ 메모 작성
  ![스크린샷 2024-02-14 214042](https://github.com/kminnyes/Create_Notepad/assets/129060022/bd1ef26a-2809-4011-948a-92fce2404df3)
+ 메모 수정
  ![스크린샷 2024-02-14 214158](https://github.com/kminnyes/Create_Notepad/assets/129060022/07fb4afc-423c-448a-883c-a31bd13ba432)
+ 메모 목록
  ![스크린샷 2024-02-14 214126](https://github.com/kminnyes/Create_Notepad/assets/129060022/2f342067-86a6-44b8-b418-43599775b6ac)
+ 메모 상세
  ![스크린샷 2024-02-14 214148](https://github.com/kminnyes/Create_Notepad/assets/129060022/0b38decb-9147-421c-8cf5-36a88f01c285)
