package com.yash.natwestassignmentreportgenerator.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "REFERENCEDATA")
public class ReferenceData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String refkey1;
    private String refkey2;
    private String refdata1;
    private String refdata2;
    private String refdata3;
    private Double refdata4;

    public ReferenceData(String test, String test1, String test2, String test3, double v) {
        this.refkey1 = test;
        this.refkey2 = test1;
        this.refdata1 = test2;
        this.refdata2 = test3;
        this.refdata3 = test3;
        this.refdata4 = v;
    }


}

