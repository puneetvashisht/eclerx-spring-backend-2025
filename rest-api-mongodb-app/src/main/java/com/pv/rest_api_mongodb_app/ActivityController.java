package com.pv.rest_api_mongodb_app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ActivityController {

    @Autowired
    private ActivityRepository activityRepository;

    //track user activities
    @GetMapping("/activities")
    public List<Activity> getActivities() {
        return activityRepository.findAll();

    }

    @PostMapping("/activities")
    public void addActivity(@RequestBody String description) {
        
        Activity activity = new Activity();
        activity.setDescription(description);
        activityRepository.save(activity);
    }

    
}
