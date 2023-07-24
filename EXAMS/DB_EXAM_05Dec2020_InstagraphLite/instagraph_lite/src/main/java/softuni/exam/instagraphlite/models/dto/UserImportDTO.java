package softuni.exam.instagraphlite.models.dto;

import softuni.exam.instagraphlite.models.entity.Picture;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserImportDTO {




    @NotNull
    @Size(min = 4)
    private String password;

    @NotNull
    @Size(min = 2, max = 18)
    private String username;

    @NotNull
    private String profilePicture;

    public UserImportDTO() {
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getProfilePicture() {
        return profilePicture;
    }
}
