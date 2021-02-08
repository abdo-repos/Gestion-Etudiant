package com.ensa.services;

import com.ensa.repositories.AbsenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AbsenceService {

    private AbsenceRepository absenceRepository;

    @Autowired
    public AbsenceService(AbsenceRepository absenceRepository) {
        this.absenceRepository = absenceRepository;
    }


    
}
