package com.yash.natwestassignmentreportgenerator.Controllers;


import com.yash.natwestassignmentreportgenerator.Models.ReportData;
import com.yash.natwestassignmentreportgenerator.Services.SchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    SchedulingService schedulingService;

    @RequestMapping("/")
    public String getMainPage(){
        return "Home";
    }

    @RequestMapping("/report")
    public String getReport(Model model) {
        List<ReportData> reportDataList = schedulingService.findLastGeneratedReport();
        model.addAttribute("reportDataList", reportDataList);
        return "Report";
    }
}
