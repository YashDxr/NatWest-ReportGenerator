package com.yash.natwestassignmentreportgenerator.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.batch.item.Chunk;

import java.util.function.Consumer;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "OUTPUTDATA")
public class OutputData extends Chunk<OutputData> {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String outfield1;
        private String outfield2;
        private String outfield3;
        private String outfield4;
        private Double outfield5;

        public OutputData(String str1, String str2 , String str3 , String str4 , Double str5) {
                this.outfield1 = str1;
                this.outfield2 = str2;
                this.outfield3 = str3;
                this.outfield4 = str4;
                this.outfield5 = str5;
        }

        @Override
        public void forEach(Consumer<? super OutputData> action) {
                super.forEach(action);
        }
}
