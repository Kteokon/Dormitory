package ru.isu.dorm.domain.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "number")
    private String number;
    
    @Column(name = "size")
    private Integer size;
    
    @Column(name = "is_full")
    private Boolean isFull;
    
    @Column(name = "gender")
    private String gender;
    
    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;
}
