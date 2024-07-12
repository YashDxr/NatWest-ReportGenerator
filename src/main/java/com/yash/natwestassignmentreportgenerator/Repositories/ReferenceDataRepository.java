package com.yash.natwestassignmentreportgenerator.Repositories;


import com.yash.natwestassignmentreportgenerator.Models.ReferenceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReferenceDataRepository extends JpaRepository<ReferenceData, Long> {

    ReferenceData findByRefkey1(String refkey1);

    List<ReferenceData> findByRefkey1In(List<String> refkeys);
}

