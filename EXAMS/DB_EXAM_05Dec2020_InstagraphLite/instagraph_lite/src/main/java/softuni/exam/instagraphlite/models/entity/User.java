package softuni.exam.instagraphlite.models.entity;

import javax.persistence.*;
import java.util.*;


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

    @OneToMany(mappedBy = "user", targetEntity = Post.class)
    private Set<Post> posts;

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public User() {
        posts = new HashSet<>();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Picture getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Picture profilePicture) {
        this.profilePicture = profilePicture;
    }


    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder(String.format("User: %s\nPost count: %d\n", this.username, this.getPosts().size()));

       // this.posts.stream().sorted(Comparator.comparingDouble(a -> a.getPicture().getSize()))

        this.posts.stream().sorted((a,b) -> Double.compare(a.getPicture().getSize(), b.getPicture().getSize()))
                .forEach(post -> {
                    sb.append(String.format("==Post Details:\n" +
                        "----Caption: %s\n" +
                        "----Picture Size: %.2f\n",  post.getCaption(), post.getPicture().getSize()));
        });


        return sb.toString();
    }

}
