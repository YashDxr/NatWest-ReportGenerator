package com.yash.natwestassignmentreportgenerator.Repositories;


import com.yash.natwestassignmentreportgenerator.Models.ReferenceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReferenceDataRepository extends JpaRepository<ReferenceData, Long> {

    Optional<ReferenceData> findByRefkey1AndRefkey2(String refkey1, String refkey2);

    ReferenceData findByRefkey1(String refkey1);
}

