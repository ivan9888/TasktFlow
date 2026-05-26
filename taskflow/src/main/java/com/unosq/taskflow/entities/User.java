package com.unosq.taskflow.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity // JPA knows that this class represent a database
@Table(name = "users") //  SQL table name is going to be users
@Getter // Lombok generate automatically the methods to read the data
@Setter // Lombok generate automatically the methods to modify the data
public class User {

    @Id // (Primary Key)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increase (1, 2, 3...)
    private Long id;

    @Column(nullable = false) // cannot be null SQL
    private String name;

    @Column(nullable = false, unique = true) // the email is unique in the table
    private String email;

    // Relation: A user can have multiple tasks
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Task> tasks;
}