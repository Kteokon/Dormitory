package ru.isu.dorm.domain.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.isu.dorm.domain.model.Building;
import ru.isu.dorm.domain.model.Room;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long>{
    
    @Query("FROM Room AS r WHERE r.building.id IS :id")
    List<Room> findAllRooms(@Param("id") Long id, Sort sort);
}
