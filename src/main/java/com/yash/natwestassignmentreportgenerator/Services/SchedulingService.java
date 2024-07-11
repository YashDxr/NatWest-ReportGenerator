package com.yash.natwestassignmentreportgenerator.Services;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SchedulingService {

    @Scheduled(cron = "0 0 * * * ?") // Example: runs every hour
    public void scheduledReportGeneration() {
        // Implement scheduled report generation logic here
    }
}

