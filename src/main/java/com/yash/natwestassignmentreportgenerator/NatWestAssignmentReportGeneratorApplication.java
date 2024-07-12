package com.yash.natwestassignmentreportgenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NatWestAssignmentReportGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(NatWestAssignmentReportGeneratorApplication.class, args);
    }

}
