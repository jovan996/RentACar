package com.example.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.server.model.Komentar;

public interface KomentarRepository extends JpaRepository<Komentar, Long> {

}
