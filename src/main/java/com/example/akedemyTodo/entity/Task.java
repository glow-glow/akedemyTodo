package com.example.akedemyTodo.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;
/**
 * сущность задач
 * определяет данные, передающиеся в JSON
 *автор алкесей
 */

@Entity
@Table(name = "task")
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Task {
    private Long id;
    private String title;
    private Integer completed;
    private Date date;

    private Boolean stat = false;
    private Integer priority;
    private Category category;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {return id; }


    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }


    @Basic
    @Column(name = "completed")
    public Integer getCompleted() {
        return completed;
    }


    @Basic
    @Column(name = "date")
    public Date  getDate() {
        return date;
    }

    @Basic
    @Column(name = "priority")
    public Integer getPriority(){return priority;}

    @Basic
    @Column(name = "stat")
    public Boolean getStat(){return stat;}




    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    public Category getCategory() {
        return category;
    }


    public boolean isStat() {
        return stat;
    }


}
