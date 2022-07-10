package com.schoolintegrated.system.controllers;


import com.schoolintegrated.system.dtos.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Properties;

@Controller
public class PatientFrontController {

//    @Autowired
//    private JournalEntryRepository repository;

    @RequestMapping("/home")
    public String home(){
        return "home";
    }


    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/add-patient")
    public String addPatient(){
        return "AddPatient";
    }

    @RequestMapping("/signUpRequest")
    public String signUpRequest(UserDto user) {


        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<?> requestEntity = new HttpEntity<>(user,headers);

        ResponseEntity<?> usersResponse = restTemplate.exchange(
                "http://localhost:8000/api/v1/auths/register",
                HttpMethod.POST,
                requestEntity,
                Object.class
        );

        System.out.println("response: " + usersResponse.getBody());

        return "login";
    }

    @RequestMapping("/addPatient")
    public ModelAndView addPatient(HttpServletRequest request,PatientDto patient) {
        ModelAndView mav = new ModelAndView("redirect:/dashboard");

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.setBearerAuth(request.getSession().getAttribute("token").toString());

        HttpEntity<?> requestEntity = new HttpEntity<>(patient,headers);

        ResponseEntity<JwtResponse> usersResponse = restTemplate.exchange(
                "http://localhost:8000/api/v1/patients/create",
                HttpMethod.POST,
                requestEntity,
                JwtResponse.class
        );

        System.out.println("response: " + usersResponse.getBody());


        return mav;
    }


    // edit rout
    @RequestMapping("/delete-patient")
    public String deletePatient(HttpServletRequest request,@RequestParam("id") String id) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.setBearerAuth(Objects.requireNonNull(request.getSession().getAttribute("token")).toString());
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<?> usersResponse = restTemplate.exchange(
                "http://localhost:8000/api/v1/patients/delete/" + id,
                HttpMethod.DELETE,
                requestEntity,
                Object.class
        );

        System.out.println("response: " + usersResponse.getBody());

        return "redirect:/dashboard";
    }

    // edit patient
    @RequestMapping("/edit-patient")
    public ModelAndView editPatient(HttpServletRequest request,@RequestParam("id") String id) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.setBearerAuth(Objects.requireNonNull(request.getSession().getAttribute("token")).toString());
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<Patient> usersResponse = restTemplate.exchange(
                "http://localhost:8000/api/v1/patients/" + id,
                HttpMethod.GET,
                requestEntity,
                Patient.class
        );

        ModelAndView mav = new ModelAndView("EditPatient");
        mav.addObject("patient",usersResponse.getBody());
        return mav;
    }


    @RequestMapping("/editPatient")
    public ModelAndView editPatient(HttpServletRequest request,@RequestParam("id") String id, PatientDto patient) {
        ModelAndView mav = new ModelAndView("redirect:/dashboard");

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.setBearerAuth(Objects.requireNonNull(request.getSession().getAttribute("token")).toString());
        HttpEntity<?> requestEntity = new HttpEntity<>(patient,headers);

        ResponseEntity<JwtResponse> usersResponse = restTemplate.exchange(
                "http://localhost:8000/api/v1/patients/update/"+id,
                HttpMethod.PUT,
                requestEntity,
                JwtResponse.class
        );

        System.out.println("response: " + usersResponse.getBody());

        return mav;
    }

    @RequestMapping("/logInRequest")
    public ModelAndView logInRequest(HttpServletRequest request, JwtRequest user) {


        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            HttpEntity<?> requestEntity = new HttpEntity<>(user,headers);

            ResponseEntity<JwtResponse> usersResponse = restTemplate.exchange(
                    "http://localhost:8000/api/v1/auths/login",
                    HttpMethod.POST,
                    requestEntity,
                    JwtResponse.class
            );

            // set token

            request.getSession().setAttribute("token", usersResponse.getBody().getToken());

            ModelAndView md = new ModelAndView("redirect:/dashboard");

            return md;
        }
        catch (Exception e) {
            ModelAndView mav = new ModelAndView("login");
            mav.addObject("error", "Invalid username or password");
            return mav;
        }
    }

    @RequestMapping("/dashboard")
    public ModelAndView dashboard(HttpServletRequest request){

        ModelAndView modelAndView = new ModelAndView("dashboard");

//        System.out.println(" token in dashboard: " + request.getSession().getAttribute("token"));
        if(request.getSession().getAttribute("token").toString().isEmpty()){
            return new ModelAndView("login");
        }

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(request.getSession().getAttribute("token").toString());

        HttpEntity<?> requestEntity = new HttpEntity<>(null,headers);

        ResponseEntity<Patient[]> patientResponse = restTemplate.exchange(
                "http://localhost:8000/api/v1/patients/get-all",
                HttpMethod.GET,
                requestEntity,
                Patient[].class
        );
        System.out.println(" response: " + patientResponse.getBody());
        modelAndView.addObject("patients", patientResponse.getBody());

        return modelAndView;
    }



    // Retrieving all data

    @RequestMapping("/getAllEntries")
    public ModelAndView getAllJournalEntries() {
        ModelAndView modelAndView = new ModelAndView();
//        List<JournalEntry> journalEntries = repository.findAll();

        // getting the api response

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authozition", "");

        HttpEntity<?> requestEntity = new HttpEntity<>(null,headers);

        ResponseEntity<?> usersResponse = restTemplate.exchange("https://voting-apis.herokuapp.com/api/user/get-all", HttpMethod.GET, requestEntity, Object.class);


        System.out.println("Response:  "+ usersResponse.getBody());

//        modelAndView.addObject("entries", journalEntries);
        modelAndView.setViewName("getAllEntries");

        return modelAndView;
    }

    @RequestMapping("/getEntry")
    public ModelAndView getJournalEntry(@RequestParam int id) {

        ModelAndView modelAndView = new ModelAndView();
//        JournalEntry entryFound = repository.findById(id).orElse(new JournalEntry());

//        modelAndView.addObject("entry", entryFound);
        modelAndView.setViewName("getEntry");

        return modelAndView;

    }

    // Delete data

    @RequestMapping("/deleteEntry")
    public String deleteJournalEntry( @RequestParam int id) {
//        repository.deleteById( id);

        return "home";
    }

    //**************** MORE COMPLEX QUERIES **********************

    // Find by category

    @RequestMapping("/getEntriesByCategory")
    public ModelAndView getEntriesByCategory ( @RequestParam String  category) {

        ModelAndView modelAndView = new ModelAndView();

//        List<JournalEntry> entries = repository.findByCategory(category);

//        modelAndView.addObject("entries", entries);

        modelAndView.setViewName("getEntriesByCategory");

        return modelAndView;
    }

    // Find by Greater than

    @RequestMapping("/getEntriesByIdGT")
    public ModelAndView getEntriesByIdGT(@RequestParam int id) {

        ModelAndView modelAndView = new ModelAndView();
//        List<JournalEntry> entries = repository.findByIdGreaterThan(id);

//        modelAndView.addObject("entries",entries);
        modelAndView.setViewName("getEntriesByIdGT");

        return modelAndView;
    }

    //Find By Category but sorted by title

    @RequestMapping("/getEntriesByCategorySorted")
    public ModelAndView getEntriesByCategorySorted(@RequestParam String category) {

        ModelAndView mv = new ModelAndView();
//        List<JournalEntry> entries = repository.findByCategorySorted(category);

//        mv.addObject("entries",entries);
        mv.setViewName("getEntriesByCategorySorted");

        return mv;

    }

}
