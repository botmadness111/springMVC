package org.example.repositories;

import org.example.models.Human;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HumanRepository extends JpaRepository<Human, Integer> {
    public Optional<Human> findByFio(String fio);
}
