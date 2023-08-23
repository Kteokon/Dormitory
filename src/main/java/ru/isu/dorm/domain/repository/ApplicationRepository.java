package ru.isu.dorm.domain.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.isu.dorm.domain.model.Application;
import ru.isu.dorm.domain.model.Student;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long>{
    
    public Application findByStudent(Student student);
    
    @Query("SELECT a.student FROM Application AS a WHERE a.id is :id")
    Student findStudent(@Param("id") Long id, Sort sort);
    
    public List<Application> findByType(String type);
}
