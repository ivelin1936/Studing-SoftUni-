package app.retake.services.impl;

import app.retake.domain.dto.ProcedureWrapperXMLExportDTO;
import app.retake.domain.dto.ProcedureXMLImportDTO;
import app.retake.domain.models.Animal;
import app.retake.domain.models.AnimalAid;
import app.retake.domain.models.Procedure;
import app.retake.domain.models.Vet;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.ProcedureRepository;
import app.retake.services.api.AnimalAidService;
import app.retake.services.api.AnimalService;
import app.retake.services.api.ProcedureService;
import app.retake.services.api.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProcedureServiceImpl implements ProcedureService {

    private final ProcedureRepository procedureRepository;
    private final ModelParser modelParser;
    private final VetService vetService;
    private final AnimalService animalService;
    private  final AnimalAidService animalAidService;

    @Autowired
    public ProcedureServiceImpl(ProcedureRepository procedureRepository,
                                ModelParser modelParser,
                                VetService vetService,
                                AnimalService animalService,
                                AnimalAidService animalAidService) {
        this.procedureRepository = procedureRepository;
        this.modelParser = modelParser;
        this.vetService = vetService;
        this.animalService = animalService;
        this.animalAidService = animalAidService;
    }

    @Override
    public void create(ProcedureXMLImportDTO dto) {
        Animal animal = this.animalService.getByPassportSerialNumber(dto.getAnimal());
        Vet vet = this.vetService.getByName(dto.getVet());

        if (animal != null && vet != null) {

            Set<AnimalAid> animalAids = dto.getAnimalAids()
                    .stream()
                    .map(animalAidDto -> {
                        AnimalAid animalAid = this.animalAidService.getByName(animalAidDto.getName());
                        if (animalAid == null) {
                            throw new IllegalArgumentException();
                        }
                        return animalAid;
                    }).collect(Collectors.toSet());

            Procedure procedure = new Procedure();
            procedure.setAnimal(animal);
            procedure.setVet(vet);
            procedure.setDate(dto.getDate());
            procedure.setServices(animalAids);

            this.procedureRepository.saveAndFlush(procedure);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public ProcedureWrapperXMLExportDTO exportProcedures() {
        return null;
    }
}

