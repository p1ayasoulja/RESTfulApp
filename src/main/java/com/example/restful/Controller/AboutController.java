package com.example.restful.Controller;

import com.example.restful.Assembler.AboutModelAssembler;
import com.example.restful.Enum.Salary;
import com.example.restful.Exceptions.DoctorNotFoundException;
import com.example.restful.Repositorys.AboutRepo;
import com.example.restful.domain.AboutDoctor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/about")
public class AboutController {
    private final AboutRepo aboutRepo;
    private final AboutModelAssembler assembler;

    public AboutController(AboutRepo aboutRepo, AboutModelAssembler assembler) {
        this.aboutRepo = aboutRepo;
        this.assembler = assembler;
    }

    @GetMapping()
    public CollectionModel<EntityModel<AboutDoctor>> allInfo() {

        List<EntityModel<AboutDoctor>> orders = aboutRepo.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(orders, //
                linkTo(methodOn(AboutController.class).allInfo()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<AboutDoctor> oneInfo(@PathVariable Long id) {

        AboutDoctor aboutDoctor = aboutRepo.findById(id) //
                .orElseThrow(() -> new DoctorNotFoundException(id));

        return assembler.toModel(aboutDoctor);
    }

    @PostMapping("/{id}")
    ResponseEntity<EntityModel<AboutDoctor>> newAboutDoctor(@RequestBody AboutDoctor aboutDoctor) {

        aboutDoctor.setSalary(Salary.LOW);
        AboutDoctor newAboutDoctor = aboutRepo.save(aboutDoctor);

        return ResponseEntity //
                .created(linkTo(methodOn(AboutController.class).oneInfo(newAboutDoctor.getId())).toUri()) //
                .body(assembler.toModel(newAboutDoctor));
    }

    @DeleteMapping("/{id}/up")
   public ResponseEntity<?> upgrade(@PathVariable Long id) {

        AboutDoctor aboutDoctor = aboutRepo.findById(id) //
                .orElseThrow(() -> new DoctorNotFoundException(id));

        if (aboutDoctor.getSalary() == Salary.LOW) {
            aboutDoctor.setSalary(Salary.MIDDLE);
            return ResponseEntity.ok(assembler.toModel(aboutRepo.save(aboutDoctor)));
        }
        if (aboutDoctor.getSalary() == Salary.MIDDLE) {
            aboutDoctor.setSalary(Salary.HIGH);
            return ResponseEntity.ok(assembler.toModel(aboutRepo.save(aboutDoctor)));
        }

        return ResponseEntity //
                .status(HttpStatus.METHOD_NOT_ALLOWED) //
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE) //
                .body(Problem.create() //
                        .withTitle("Method not allowed") //
                        .withDetail("We cant make ur salary higher! Its already on " + aboutDoctor.getSalary() + "level"));
    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteOne(@PathVariable Long id) {
        aboutRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

