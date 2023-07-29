package com.example.football.models.entity;

import javax.persistence.*;


@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private int fanBase;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String history;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String stadiumName;




























}
