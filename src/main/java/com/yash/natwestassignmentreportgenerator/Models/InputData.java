package com.yash.natwestassignmentreportgenerator.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "INPUTDATA")
public class InputData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String field1;
    private String field2;
    private String field3;
    private String field4;
    private Double field5;
    private String refkey1;
    private String refkey2;

    public InputData(String test, String test1, String test2, String test3, double v, String test4, String test5) {
        this.field1 = test;
        this.field2 = test1;
        this.field3 = test2;
        this.field4 = test3;
        this.field5 = v;
        this.refkey1 = test4;
        this.refkey2 = test5;
    }
}

