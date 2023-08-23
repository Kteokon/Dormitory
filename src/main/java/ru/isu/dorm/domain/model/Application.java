package ru.isu.dorm.domain.model;

import java.time.LocalDate;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "type")
    private String type;
    
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;
    
    @Column(name = "date")
    private LocalDate date;
}
