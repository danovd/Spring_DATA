package softuni.exam.models.dto;


import javax.validation.constraints.Size;

public class CountryImportDTO {
    @Size(min = 2, max = 60)
    private String countryName;
    @Size(min = 2, max = 20)
    private String currency;

    public CountryImportDTO() {
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCurrency() {
        return currency;
    }
}
