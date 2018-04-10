package json.processing;

import json.processing.model.dto.json.ExportPersonDto;
import json.processing.model.dto.json.PersonDto;
import json.processing.model.dto.json.PhoneNumberDto;
import json.processing.model.entity.Address;
import json.processing.model.entity.Person;
import json.processing.model.entity.PhoneNumber;
import json.processing.serialize.Serializer;
import json.processing.service.personService.PersonService;
import json.processing.util.DtoConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Transactional
public class CmdRunner implements CommandLineRunner {

    private static final String PERSON_INPUT_JSON = "/files/input/json/person.json";
    private static final String PERSONS_INPUT_JSON = "/files/input/json/persons.json";
    private static final String PERSONS_OUTPUT_JSON = "src/main/resources/files/output/json/personsByCountry.json";

    @Autowired
    @Qualifier(value = "JsonSerializer")
    private Serializer serializerJson;

    @Autowired
    @Qualifier(value = "XmlSerializer")
    private Serializer serializerXml;

    private PersonService personService;

    @Autowired
    public CmdRunner(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public void run(String... args) throws Exception {
        importPersonJson();
        importPersons();

        exportPersonByCountry();
    }

    private void exportPersonByCountry() {
        List<Person> bulgarians = personService.findByCountry("Bulgaria");
        List<ExportPersonDto> exportPersonDtos = DtoConvertUtil.convert(bulgarians, ExportPersonDto.class);
        serializerJson.serialize(exportPersonDtos, PERSONS_OUTPUT_JSON);
    }

    private void importPersons() {
        PersonDto[] personDtos = serializerJson.deserialize(PersonDto[].class, PERSONS_INPUT_JSON);
        for (PersonDto personDto : personDtos) {
            importPerson(personDto);
        }
    }

    private void importPersonJson() {
        PersonDto personDto = serializerJson.deserialize(PersonDto.class, PERSON_INPUT_JSON);
        importPerson(personDto);
    }

    private void importPerson(PersonDto personDto) {

        Person person = new Person();
        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());
        Address address = new Address();
        address.setCity(personDto.getAddressImportDto().getCity());
        address.setStreet(personDto.getAddressImportDto().getStreet());
        address.setCountry(personDto.getAddressImportDto().getCountry());
        person.setAddress(address);

        Set<PhoneNumber> phoneNumberSet = new HashSet<>();
        for (PhoneNumberDto pn : personDto.getPhoneJsonDtos()) {
            PhoneNumber phoneNumber = new PhoneNumber();
            phoneNumber.setNumber(pn.getNumber());
            phoneNumber.setPerson(person);
            phoneNumberSet.add(phoneNumber);
        }
        person.setPhoneNumbers(phoneNumberSet);

        personService.create(person);
        String debug = "";
    }
}
