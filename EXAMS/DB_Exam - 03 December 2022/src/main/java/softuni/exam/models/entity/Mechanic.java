package softuni.exam.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "mechanics")
public class Mechanic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

@Column(name = "first_name", nullable = false, unique = true)
    private String firstName;
    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;
    @Column(unique = true)
    private String phone;


    public Mechanic() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
