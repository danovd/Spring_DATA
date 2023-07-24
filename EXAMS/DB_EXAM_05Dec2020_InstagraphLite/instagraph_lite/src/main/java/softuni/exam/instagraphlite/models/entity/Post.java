package softuni.exam.instagraphlite.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String caption;


    @ManyToOne(optional = false)
    private Picture picture;

    @ManyToOne(optional = false)
    private User user;



























}
