package com.unosq.taskflow.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tasks")
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private String status; //

    // Relation
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // column (Foreign Key)
    private User user;
}