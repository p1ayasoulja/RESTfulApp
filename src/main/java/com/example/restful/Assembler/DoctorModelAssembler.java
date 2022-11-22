package com.example.restful.Assembler;

import com.example.restful.Controller.DoctorController;
import com.example.restful.domain.Doctors;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class DoctorModelAssembler implements RepresentationModelAssembler<Doctors, EntityModel<Doctors>> {
    @Override
    public EntityModel<Doctors> toModel(Doctors doctors) {

        return EntityModel.of(doctors, //
                linkTo(methodOn(DoctorController.class).one(doctors.getId())).withSelfRel(),
                linkTo(methodOn(DoctorController.class).all()).withRel("doctors"));
    }
}
