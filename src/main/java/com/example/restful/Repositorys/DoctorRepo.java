package com.example.restful.Repositorys;

import com.example.restful.domain.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepo extends JpaRepository<Doctors,Long> {
}
