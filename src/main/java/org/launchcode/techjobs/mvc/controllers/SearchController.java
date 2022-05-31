package org.launchcode.techjobs.mvc.controllers;

import org.launchcode.techjobs.mvc.models.Job;
import org.launchcode.techjobs.mvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.launchcode.techjobs.mvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }
    //this controller allows us to use the column choice data in our search.html so that each column choice is displayed as a radio button
//requestparam is used to read the html form data provided by user and bind it to the request parameter
    @PostMapping(value="results")
    public String displaySearchResults(Model model, @RequestParam  String searchType, @RequestParam(required=false) String searchTerm ){
    ArrayList<Job> jobs;
    if(searchType.equals("all") && !searchTerm.equals("")){
        jobs=JobData.findByColumnAndValue(searchType, searchTerm);
        model.addAttribute("title", "Jobs with " + searchType +": " + searchTerm);
    }else if(searchType.equals("all") && searchTerm.isEmpty()){
        jobs=JobData.findAll();
        model.addAttribute("title", "All available jobs");
    }else{
        jobs=JobData.findByColumnAndValue(searchType, searchTerm);
        model.addAttribute("title", "Jobs with " + searchType +": " + searchTerm);
    }
    model.addAttribute("jobs", jobs);
    model.addAttribute("columns", columnChoices);
    return "list-jobs";
    }}
//else{
//            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
//
//        }
//        model.addAttribute("jobs", jobs);
//        model.addAttribute("columns", columnChoices);
//        return "list-jobs";