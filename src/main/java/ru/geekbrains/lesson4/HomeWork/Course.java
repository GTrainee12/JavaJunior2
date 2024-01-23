package ru.geekbrains.lesson4.HomeWork;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;



@Setter
@Getter
@Entity
@Table(name = "Courses")
public class Course {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "duration")
    private int duration;

    public Course() {
    }

    public Course(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }
}