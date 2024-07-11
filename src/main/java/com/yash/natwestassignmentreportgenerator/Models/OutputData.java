package com.yash.natwestassignmentreportgenerator.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.batch.item.Chunk;

import java.util.function.Consumer;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutputData extends Chunk<OutputData> {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String outfield1;
        private String outfield2;
        private String outfield3;
        private String outfield4;
        private String outfield5;

        @Override
        public void forEach(Consumer<? super OutputData> action) {
                super.forEach(action);
        }
}
