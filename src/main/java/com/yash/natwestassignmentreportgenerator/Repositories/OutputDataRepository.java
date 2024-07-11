package com.yash.natwestassignmentreportgenerator.Repositories;


import com.yash.natwestassignmentreportgenerator.Models.OutputData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutputDataRepository extends JpaRepository<OutputData, Long> {

}

