package ru.isu.dorm.domain.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.isu.dorm.domain.model.Student;
import ru.isu.dorm.domain.model.Room;
import ru.isu.dorm.domain.model.StudentGroup;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
    @Query("FROM Student AS st WHERE st.room.id IS :id")
    List<Student> findRoomsStudents(@Param("id") Long id, Sort sort);
    
    @Query("SELECT st.room FROM Student AS st WHERE st.id IS :id")
    Room findRoom(@Param("id") Long id, Sort sort);
    
    public List<Student> findByGroupId(Long id, Sort sort);
    
    @Query("FROM Student AS st WHERE st.room IS NOT null")
    List<Student> findWithRooms(Sort sort);
    
    @Query("FROM Student AS st WHERE st.room IS NOT null AND st.group.id IS :id")
    List<Student> findWithRoomsAndGroup(@Param("id") Long id, Sort sort);
    
    @Query("FROM Student AS st WHERE st.name LIKE %:keyword%")
    List<Student> findByKeyword(@Param("keyword") String keyword, Sort sort);
    
    @Query("FROM Student AS st WHERE st.name LIKE %:keyword% AND st.group.id IS :id")
    List<Student> findByKeywordAndGroup(@Param("keyword") String keyword, @Param("id") Long id, Sort sort);
    
    @Query("FROM Student AS st WHERE st.name LIKE %:keyword% AND st.room IS NOT null")
    List<Student> findByKeywordWithRooms(@Param("keyword") String keyword, Sort sort);
    
    @Query("FROM Student AS st WHERE st.name LIKE %:keyword% AND st.room IS NOT null AND st.group.id IS :id")
    List<Student> findByKeywordWithRoomsAndGroup(@Param("keyword") String keyword, @Param("id") Long id, Sort sort);
}
