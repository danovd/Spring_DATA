package com.example.football.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "towns")
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private int population;
    @Column(columnDefinition = "TEXT")
    private String travelGuide;




























}
