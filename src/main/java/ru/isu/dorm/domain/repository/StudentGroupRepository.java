package ru.isu.dorm.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.isu.dorm.domain.model.StudentGroup;

public interface StudentGroupRepository  extends JpaRepository<StudentGroup, Long>{
    
}
