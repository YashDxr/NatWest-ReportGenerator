package com.yash.natwestassignmentreportgenerator.Services;

import com.yash.natwestassignmentreportgenerator.Models.OutputData;
import com.yash.natwestassignmentreportgenerator.Models.ReportData;
import com.yash.natwestassignmentreportgenerator.Repositories.OutputDataRepository;
import org.modelmapper.ModelMapper;
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
import java.util.stream.Collectors;

@Service
public class SchedulingService {

    @Autowired
    private ReportGenerationService reportGenerationService;

    @Autowired
    @Qualifier("customTaskScheduler")
    private TaskScheduler taskScheduler;

    @Autowired
    private OutputDataRepository outputDataRepository;

    @Autowired
    ModelMapper mapper;

    private List<ScheduledFuture<?>> scheduledFutures = new ArrayList<>();
    private List<ReportData> lastGeneratedReport = new ArrayList<>();


    public void scheduleReportGeneration(LocalDateTime datetime) {
        Date date = Date.from(datetime.atZone(ZoneId.systemDefault()).toInstant());
        ScheduledFuture<?> scheduledFuture = taskScheduler.schedule(() -> {
            lastGeneratedReport = reportGenerationService.generateReport();
            saveReportDataToOutputData(lastGeneratedReport);
            lastGeneratedReport.forEach(report -> System.out.println(report.toString()));
        }, date);

        scheduledFutures.add(scheduledFuture);
    }

    private void saveReportDataToOutputData(List<ReportData> reportDataList) {
        List<OutputData> outputDataList = reportDataList.stream()
                .map(reportData -> mapper.map(reportData, OutputData.class))
                .collect(Collectors.toList());
        outputDataRepository.saveAll(outputDataList);
    }

    public List<ReportData> findLastGeneratedReport() {
        return lastGeneratedReport;
    }
}
