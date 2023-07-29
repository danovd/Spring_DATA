package com.example.football.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "stats")
public class Stat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
