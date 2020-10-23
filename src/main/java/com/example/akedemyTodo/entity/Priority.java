package com.example.akedemyTodo.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Table(name="priority")
public class Priority {
    private UUID id;
    private Integer title;
    private String color;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public UUID getId() {
        return id;
    }


    @Basic
    @Column(name = "title")
    public Integer getTitle() {
        return title;
    }


    @Basic
    @Column(name = "color")
    public String getColor() {
        return color;
    }






}
