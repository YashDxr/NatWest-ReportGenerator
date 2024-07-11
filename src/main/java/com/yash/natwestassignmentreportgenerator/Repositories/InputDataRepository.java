package com.yash.natwestassignmentreportgenerator.Repositories;


import com.yash.natwestassignmentreportgenerator.Models.InputData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InputDataRepository extends JpaRepository<InputData, Long> {

}

