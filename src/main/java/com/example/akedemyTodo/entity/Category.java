package com.example.akedemyTodo.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;


@Entity
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Table(name="category")
public class Category {
    private UUID id;
    private String title;
    private Date date;

    private Long completedCount;
    private Long uncompletedCount;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public UUID getId() {
        return id;
    }


    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }


    @Basic
    @Column(name = "completed_count")
    public Long getCompletedCount() {
        return completedCount;
    }


    @Basic
    @Column(name = "uncompleted_count")
    public Long getUncompletedCount() {
        return uncompletedCount;
    }





}
