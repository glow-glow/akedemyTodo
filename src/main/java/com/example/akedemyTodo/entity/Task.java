package com.example.akedemyTodo.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

/**
 * JAVA-doc
 */
@Entity
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Table(name="task")
public class Task {
    private UUID id;
    private String title;
    private Integer completed; // TODO(Шайдуко): тут же булев признак -выполнено или нет
    private Date date;

    private Priority priority;
    private Category category;


    // TODO(Шайдуко): мы же используем lombok!!! у него есть аннотации @Getter/@Setter

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
    @Column(name = "completed")
    public Integer getCompleted() {
        return completed;
    }


    @Basic
    @Column(name = "date")
    public Date  getDate() {
        return date;
    }



    @ManyToOne
    @JoinColumn(name = "priority_id", referencedColumnName = "id")
    public Priority getPriority() {
        return priority;
    }



    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    public Category getCategory() {
        return category;
    }


}
