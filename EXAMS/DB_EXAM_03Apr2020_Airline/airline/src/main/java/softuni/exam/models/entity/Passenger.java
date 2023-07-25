package softuni.exam.models.entity;


import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Set;


@Entity
@Table(name = "passengers")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private int age;
    @Column(unique = true)
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @ManyToOne
    private Town town;

    public Passenger() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }


    @OneToMany(mappedBy = "passenger", targetEntity = Ticket.class)
        private Set<Ticket> tickets;

        public Set<Ticket> getTickets() {return tickets;}


    @Transactional
    @Override
    public String toString() {
        return String.format("Passenger %s  %s\n" +
                        "Email - %s\n" +
                        "Phone - %s\n" +
                        "Number of tickets - %d", this.getFirstName(), this.getLastName(), this.getEmail(),
                this.getPhoneNumber(), this.getTickets().size());
    }
}
