package com.be1.plant4you.domain.plant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Entity
public class PlantDict {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "plant_dict_id")
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "plant_score_id")
    private PlantScore plantScore;

    @Column(name = "plant_name", nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private Byte viabilityLevel;

    @Column(nullable = false, length = 500)
    private String viabilityDesc;

    @Column(nullable = false)
    private Byte waterFreqLevel;

    @Column(nullable = false, length = 500)
    private String waterFreqDesc;

    @Column(nullable = false)
    private Byte sunAmtLevel;

    @Column(nullable = false, length = 500)
    private String sunAmtDesc;

    @Column(nullable = false, length = 50)
    private String funcHead;

    @Column(nullable = false, length = 1000)
    private String funcDesc;

    @Column(nullable = false, length = 1000)
    private String plantDesc;

    @Column(name = "plant_img_url", nullable = false)
    private String imgUrl;
}
