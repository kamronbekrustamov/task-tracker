package com.kamronbek.tasktracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "task")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "time")
    private String time;

    @Column(name = "important")
    private Boolean important;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false)
    private User user;
}
