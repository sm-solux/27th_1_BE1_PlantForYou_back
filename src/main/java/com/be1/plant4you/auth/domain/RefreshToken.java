package com.be1.plant4you.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RefreshToken {

    @Id
    @Column(name = "token_key")
    private Long key;

    @MapsId
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "token_key")
    private User user; //FK이면서 PK

    @Column(name = "token_value")
    private String value;

    public void updateValue(String value) {
        this.value = value;
    }
}
