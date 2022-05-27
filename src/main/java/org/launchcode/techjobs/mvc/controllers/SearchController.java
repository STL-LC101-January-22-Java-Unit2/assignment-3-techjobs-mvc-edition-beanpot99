package org.launchcode.techjobs.mvc.controllers;

import org.launchcode.techjobs.mvc.models.Job;
import org.launchcode.techjobs.mvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping(value="search/{searchType}/{searchTerm}")
    public String displaySearchResults(Model model, @PathVariable  String searchType, @PathVariable String searchTerm ){
    ArrayList<Job> jobs;
        if(searchTerm=="all" || searchTerm==""||searchTerm=="All"){
        jobs= JobData.findAll();
        model.addAttribute("title", "All Jobs");
    }else{
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);

        }
        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", columnChoices);
        return "search";
    }
}
