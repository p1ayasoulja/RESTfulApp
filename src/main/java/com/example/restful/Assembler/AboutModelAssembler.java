package com.example.restful.Assembler;

import com.example.restful.Controller.AboutController;
import com.example.restful.Enum.Status;
import com.example.restful.domain.AboutDoctor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AboutModelAssembler implements RepresentationModelAssembler<AboutDoctor, EntityModel<AboutDoctor>> {
    @Override
    public EntityModel<AboutDoctor> toModel(AboutDoctor aboutDoctor) {


        EntityModel<AboutDoctor> aboutDoctorEntityModelModel = EntityModel.of(aboutDoctor,
                linkTo(methodOn(AboutController.class).oneInfo(aboutDoctor.getId())).withSelfRel(),
                linkTo(methodOn(AboutController.class).allInfo()).withRel("infos"));

        if (aboutDoctor.getStatus() == Status.NOT_ACCEPTED) {
            aboutDoctorEntityModelModel.add(linkTo(methodOn(AboutController.class).upgrade(aboutDoctor.getId())).withRel("cancel"));
        }

        return aboutDoctorEntityModelModel;
    }
}

