package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

interface ChatRepository extends JpaRepository<Entity, Long> {

}
