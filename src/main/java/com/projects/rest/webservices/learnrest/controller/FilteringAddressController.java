package com.projects.rest.webservices.learnrest.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.projects.rest.webservices.learnrest.entity.Address;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringAddressController {

    @GetMapping(path = "/addresses")
    public MappingJacksonValue getAllAddresses(){
        List<Address> addressList = List.of(
                new Address(7, "Langholm Ct", "Edison", "NJ", "08817"),
                new Address(1106, "Aspen Dr", "Plainsboro", "NJ", "08536")
        );
        // Dynamic Filtering
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(addressList);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("street","zip" );
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("AddressDynamicFilter", filter);
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }

    @GetMapping(path = "/address")
    public MappingJacksonValue getAddress(){
        Address address = new Address(7, "Langholm Ct", "Edison","NJ","08817");


        // Dynamic Filtering
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(address);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("city","state");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("AddressDynamicFilter", filter);

        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }
}
