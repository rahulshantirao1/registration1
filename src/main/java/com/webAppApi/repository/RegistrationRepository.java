package com.webAppApi.repository;

import com.webAppApi.entity.Registraion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registraion, Long> {
}