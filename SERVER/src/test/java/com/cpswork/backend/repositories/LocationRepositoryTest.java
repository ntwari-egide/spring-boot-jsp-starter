//package com.cpswork.backend.repositories;
//import com.cpswork.backend.models.Location;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//import java.util.Optional;
//
//import static junit.framework.TestCase.*;
//
//@DataJpaTest
//@RunWith(SpringRunner.class)
//public class LocationRepositoryTest {
//
//    @Autowired
//    private LocationRepository locationRepository;
//
//    @Test
//    public void findAll_test() {
//        List<Location> locations = locationRepository.findAll();
//
//        assertEquals(locations.size(), 1);
//    }
//    @Test
//    public void findById_test() {
//        Optional<Location> locationOptional = locationRepository.findById(1l);
//
//        if (!locationOptional.isPresent())
//            fail("User with this id is not found");
//
//        assertEquals(locationOptional.get().getProvince(), "East");
//    }
//
//    @Test
//    public void removeLocation_success(){
//
//        locationRepository.deleteById(1l);
//
//        List<Location> contacts = locationRepository.findAll();
//
//        assertEquals(contacts.size(), 0);
//    }
//
//    @Test
//    public void saveLocation_success(){
//
//        locationRepository.save(new Location("East","Kayonza","Rukara","Rukara","kawangire","Kawangire"));
//
//        List<Location> contacts = locationRepository.findAll();
//
//        assertEquals(contacts.size(), 2);
//    }
//
//
//
//}
