package com.example.thanh_toan_asm.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "position")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String positionName;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @OneToMany(mappedBy = "positions", cascade = CascadeType.ALL)
    private Set<Contract> histories;

    @OneToMany(mappedBy = "positions", cascade = CascadeType.ALL)
    private Set<Partner> partners;

    @OneToMany(mappedBy = "positions", cascade = CascadeType.ALL)
    private Set<Clue> clues;
}
