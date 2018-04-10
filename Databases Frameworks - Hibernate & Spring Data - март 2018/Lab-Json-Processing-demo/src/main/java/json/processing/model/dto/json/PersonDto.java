package json.processing.model.dto.json;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class PersonDto implements Serializable {

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private AddressDto addressImportDto;

    @Expose
    private Set<PhoneNumberDto> phoneJsonDtos;

    public PersonDto() {
        phoneJsonDtos = new HashSet<>();
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

    public AddressDto getAddressImportDto() {
        return addressImportDto;
    }

    public void setAddressImportDto(AddressDto addressImportDto) {
        this.addressImportDto = addressImportDto;
    }

    public Set<PhoneNumberDto> getPhoneJsonDtos() {
        return phoneJsonDtos;
    }

    public void setPhoneJsonDtos(Set<PhoneNumberDto> phoneJsonDtos) {
        this.phoneJsonDtos = phoneJsonDtos;
    }
}
