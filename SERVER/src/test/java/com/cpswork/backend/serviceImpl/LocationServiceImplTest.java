//package com.cpswork.backend.serviceImpl;
//
//import com.cpswork.backend.models.Location;
//import com.cpswork.backend.repositories.LocationRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.Arrays;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//
//public class LocationServiceImplTest {
//
//    @Mock
//    private LocationRepository locationRepository;
//
//    @InjectMocks
//    private LocationServiceImpl locationService;
//
//    @Test
//    public void getAll_success() {
//        when(locationRepository.findAll()).thenReturn(Arrays.asList(new Location("East","Kayonza","Rukara","Rukara","kawangire","Kawangire")));
//
//        assertEquals("East", locationService.findAll().get(0).getProvince());
//    }
//
//    @Test
//    public void findById_test() {
//        when(locationRepository.findById(anyUUID())).thenReturn(Optional.of(new Location("East","Kayonza","Rukara","Rukara","kawangire","Kawangire")));
//
//        assertEquals("Kayonza", locationService.findById(1l).getDistrict());
//    }
//
//    @Test
//    public void createLocation_success() {
//        when(locationRepository.save(any(Location.class))).thenReturn(new Location("East","Kayonza","Rukara","Rukara","kawangire","Kawangire"));
//
//        assertEquals("Rukara", locationService.add(new Location()).getCell());
//    }
//
//    @Test
//    public void updateLocation_test() {
//        when(locationRepository.save(any(Location.class))).thenReturn(new Location("East","Kayonza","Rukara","Rukara","kawangire","Kawangire"));
//        when(locationRepository.findById(anyUUID())).thenReturn(Optional.of(new Location("East","Kayonza","Rukara","Rukara","kawangire","Kawangire")));
//
//        Location updated = locationService.update(new Location("East","Kayonza","Rukara","Rukara","kawangire","Kawangire"), 1l);
//
//        assertEquals("Kawangire", updated.getCommonName());
//    }
//
//
//}
