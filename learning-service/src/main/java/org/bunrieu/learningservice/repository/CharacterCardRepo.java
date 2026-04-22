package org.bunrieu.learningservice.repository;

import org.bunrieu.learningservice.entity.CharacterCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterCardRepo extends JpaRepository<CharacterCard, Long> {
    List<CharacterCard> findByType(String type);
    // select number of letter
    @Query(value = "SELECT * FROM character_cards WHERE type = :type ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<CharacterCard> findRandomQuestions(@Param("type") String type, @Param("limit") int limit);
    //ramdom select n wwrong answers
    @Query(value = "SELECT romaji FROM character_cards WHERE type = :type AND romaji != :correctRomaji ORDER BY RAND() LIMIT 3", nativeQuery = true)
    List<String> findWrongAnswers(@Param("type") String type, @Param("correctRomaji") String correctRomaji);
    
    // Get 1 random character by rowName
    @Query(value = "SELECT * FROM character_cards WHERE row_name = :rowName ORDER BY RAND() LIMIT 1", nativeQuery = true)
    CharacterCard findRandomByRowName(@Param("rowName") String rowName);
}
