package com.finki.wp.studentsapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TmpRepository extends JpaRepository<Tmp, Long> {
}
