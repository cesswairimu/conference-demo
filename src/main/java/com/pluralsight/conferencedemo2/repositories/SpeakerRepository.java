package com.pluralsight.conferencedemo2.repositories;

import com.pluralsight.conferencedemo2.models.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakerRepository extends JpaRepository<Speaker, Long> {
}
