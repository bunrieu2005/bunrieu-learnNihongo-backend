package org.bunrieu.learningservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "character_cards")
@Data @NoArgsConstructor @AllArgsConstructor
public class CharacterCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "character_jp", nullable = false, length = 10)
    private String characterJp;

    @Column(nullable = false, length = 10)
    private String romaji;//a,u,i,e,o

    @Column(nullable = false, length = 20)
    private String type;//hiragana,katakana

    @Column(name = "row_group", nullable = false, length = 10)
    private String rowGroup;//a,ka,sa,ta
}