package org.bunrieu.learningservice.repository;

import org.bunrieu.learningservice.entity.CharacterCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterCardRepo extends JpaRepository<CharacterCard, Long> {

    List<CharacterCard> findByType(String type);

}
