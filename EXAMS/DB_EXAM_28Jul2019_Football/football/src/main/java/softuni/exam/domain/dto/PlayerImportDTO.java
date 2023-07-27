package softuni.exam.domain.dto;
import softuni.exam.domain.entities.Position;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class PlayerImportDTO {

    @NotNull
    private String firstName;
    @NotNull
    @Size(min = 3, max = 15)
    private String lastName;

    @NotNull
    @Min(1)
    @Max(99)
    private int number;
    @NotNull
    @DecimalMin("0")
    private BigDecimal salary;
    @NotNull
    private Position position;

    @NotNull
    private ImportPictureDto picture;

    @NotNull
    private ImportTeamDto team;

    public PlayerImportDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getNumber() {
        return number;
    }

    public Position getPosition() {
        return position;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public ImportPictureDto getPicture() {
        return picture;
    }

    public ImportTeamDto getTeam() {
        return team;
    }

    @Override
    public String toString() {
        return "PlayerImportDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", number=" + number +
                ", position=" + position +
                ", salary=" + salary +
                ", picture=" + picture +
                ", team=" + team +
                '}';
    }
}
