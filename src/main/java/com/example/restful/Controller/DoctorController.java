package com.example.restful.Controller;

import com.example.restful.Assembler.DoctorModelAssembler;
import com.example.restful.Exceptions.DoctorNotFoundException;
import com.example.restful.Repositorys.DoctorRepo;
import com.example.restful.domain.Doctors;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorRepo doctorRepo;
    private final DoctorModelAssembler assembler;

    DoctorController(DoctorRepo doctorRepo, DoctorModelAssembler assembler) {
        this.doctorRepo = doctorRepo;
        this.assembler = assembler;
    }

    @GetMapping()
    public CollectionModel<EntityModel<Doctors>> all() {

        List<EntityModel<Doctors>> employees = doctorRepo.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(employees, linkTo(methodOn(DoctorController.class).all()).withSelfRel());
    }

    @PostMapping()
    ResponseEntity<?> newDoctor(@RequestBody Doctors newDoctor) {

        EntityModel<Doctors> entityModel = assembler.toModel(doctorRepo.save(newDoctor));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @GetMapping("/{id}")
    public EntityModel<Doctors> one(@PathVariable Long id) {

        Doctors doctors = doctorRepo.findById(id) //
                .orElseThrow(() -> new DoctorNotFoundException(id));

        return assembler.toModel(doctors);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> replaceDoctor(@RequestBody Doctors newDoctor, @PathVariable Long id) {

        Doctors updatedEmployee = doctorRepo.findById(id) //
                .map(employee -> {
                    employee.setName(newDoctor.getName());
                    employee.setPosition(newDoctor.getPosition());
                    return doctorRepo.save(employee);
                }) //
                .orElseGet(() -> {
                    newDoctor.setId(id);
                    return doctorRepo.save(newDoctor);
                });

        EntityModel<Doctors> entityModel = assembler.toModel(updatedEmployee);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteOne(@PathVariable Long id) {
        doctorRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
