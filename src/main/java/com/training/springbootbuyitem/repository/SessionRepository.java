package com.training.springbootbuyitem.repository;

import com.training.springbootbuyitem.entity.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

}