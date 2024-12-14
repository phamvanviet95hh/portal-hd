package com.example.thanh_toan_asm.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "clue")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Clue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nameClue;
    private String phoneClue;
    private String emailClue;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position positions;

    @OneToMany(mappedBy = "clue", cascade = CascadeType.ALL)
    private Set<PartnerToClue> partnerToClues;

}
