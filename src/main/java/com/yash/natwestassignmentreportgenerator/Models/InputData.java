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
}

