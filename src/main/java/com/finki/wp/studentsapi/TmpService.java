package com.finki.wp.studentsapi;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TmpService {

    private TmpRepository tmpRepository;

    public TmpService(TmpRepository tmpRepository) {
        this.tmpRepository = tmpRepository;
    }

    public Optional<Tmp> getTmpById(Long id) {
        return this.tmpRepository.findById(id);
    }

    public void addTmp(Tmp tmp) {
        this.tmpRepository.save(tmp);
    }
}
