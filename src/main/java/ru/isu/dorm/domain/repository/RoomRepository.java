package ru.isu.dorm.domain.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.isu.dorm.domain.model.Room;
import ru.isu.dorm.domain.model.Student;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{
    
    @Query("FROM Student AS st WHERE st.room.id IS :id")
    List<Student> findStudents(@Param("id") Long id, Sort sort);
    
    @Query("FROM Room AS r WHERE (r.gender IS :gender OR r.gender IS 'i') AND r.isFull IS 0") 
    List<Room> findGenderRooms(@Param("gender") String gender, Sort sort);
    
    public List<Room> findByIsFull(boolean isFull, Sort sort);
    
    @Query("FROM Room AS r WHERE r.gender IS :gender AND r.isFull IS 1") 
    List<Room> findFullGenderRooms(@Param("gender") String gender, Sort sort);
}
