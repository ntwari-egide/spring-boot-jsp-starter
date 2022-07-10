package com.schoolintegrated.system.controllers;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PatientFrontController {

//    @Autowired
//    private JournalEntryRepository repository;

    @RequestMapping("/home")
    public String home(){
        return "home";
    }

//    @GetMapping("/home")
//    public String viewHome(Model model) {
//        return "home";
//    }
    //******************** Basic CRUD operations are here ***************************

    // Create and update

//    @RequestMapping("/addEntry")
////    public String addJournalEntry(JournalEntry entry) {
//
//        repository.save(entry);
//
//        return "home";
////    }

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
