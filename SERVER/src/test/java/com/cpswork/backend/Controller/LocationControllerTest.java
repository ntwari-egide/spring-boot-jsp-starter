//package com.cpswork.backend.Controller;
//import com.cpswork.backend.controllers.LocationController;
//import com.cpswork.backend.models.Location;
//import com.cpswork.backend.repositories.LocationRepository;
//import com.cpswork.backend.serviceImpl.LocationServiceImpl;
//import com.cpswork.backend.services.LocationServices;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Arrays;
//
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(LocationController.class)
//public class LocationControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private LocationServices locationServices;
//
//
//    @Test
//    public void getAll_test() throws Exception {
//        when(locationServices.findAll()).thenReturn(Arrays.asList(new Location("East","Kayonza","Rukara","Rukara","kawangire","Kawangire")));
//
//        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/v1/locations").accept(MediaType.APPLICATION_JSON);
//
//        mockMvc.perform(request).andExpect(status().isOk()).andReturn();
//    }
//
//}
