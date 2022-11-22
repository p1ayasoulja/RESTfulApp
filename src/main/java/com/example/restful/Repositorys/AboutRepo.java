package com.example.restful.Repositorys;

import com.example.restful.domain.AboutDoctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AboutRepo extends JpaRepository<AboutDoctor, Long> {
}
