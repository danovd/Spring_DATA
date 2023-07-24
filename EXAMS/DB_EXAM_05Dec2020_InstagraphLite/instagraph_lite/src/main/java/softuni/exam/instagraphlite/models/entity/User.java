package softuni.exam.instagraphlite.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String password;


    @Column(nullable = false, unique = true)
    private String username;


    @ManyToOne(optional = false)
    private Picture profilePicture;

































}
