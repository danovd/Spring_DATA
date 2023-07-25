package softuni.exam.models.dto;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class PassengerImportDTO {
    @Positive
    @NotNull
    private int age;
    @Email
    @NotNull
    private String email;
    @Size(min = 2)
    @NotNull
    private String firstName;
    @NotNull
    @Size(min = 2)
    private String lastName;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String town;


    public PassengerImportDTO() {
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getTown() {
        return town;
    }
}
