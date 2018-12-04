package com.finki.wp.studentsapi;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tmp")
public class TmpController {

    private TmpService tmpService;

    public TmpController(TmpService tmpService) {
        this.tmpService = tmpService;
    }

    @GetMapping("/{id}")
    public Optional<Tmp> getTmpById(@PathVariable Long id){
        return this.tmpService.getTmpById(id);
    }

    @PostMapping("/")
    public void addTmp(@RequestBody Tmp tmp){
        this.tmpService.addTmp(tmp);
    }
}
