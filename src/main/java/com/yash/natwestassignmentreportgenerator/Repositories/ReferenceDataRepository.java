package com.yash.natwestassignmentreportgenerator.Repositories;


import com.yash.natwestassignmentreportgenerator.Models.ReferenceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReferenceDataRepository extends JpaRepository<ReferenceData, Long> {


    List<ReferenceData> findByRefkey1InOrRefkey2In(List<String> refkey1List, List<String> refkey2List);
}

