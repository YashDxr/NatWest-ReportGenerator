package com.yash.natwestassignmentreportgenerator.Services;

import com.yash.natwestassignmentreportgenerator.Models.ReportData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

@Service
public class SchedulingService {

    @Autowired
    private ReportGenerationService reportGenerationService;

    @Autowired
    @Qualifier("customTaskScheduler")
    private TaskScheduler taskScheduler;

    private List<ScheduledFuture<?>> scheduledFutures = new ArrayList<>();
    private List<ReportData> lastGeneratedReport = new ArrayList<>();


    public void scheduleReportGeneration(LocalDateTime datetime) {
        Date date = Date.from(datetime.atZone(ZoneId.systemDefault()).toInstant());
        ScheduledFuture<?> scheduledFuture = taskScheduler.schedule(() -> {
            lastGeneratedReport = reportGenerationService.generateReport();
            lastGeneratedReport.forEach(report -> System.out.println(report.toString()));
        }, date);

        scheduledFutures.add(scheduledFuture);
    }

    public List<ReportData> getLastGeneratedReport() {
        return lastGeneratedReport;
    }
}
