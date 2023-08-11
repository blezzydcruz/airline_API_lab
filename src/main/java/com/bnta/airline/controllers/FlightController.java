package com.bnta.airline.controllers;

import com.bnta.airline.models.Flight;
import com.bnta.airline.models.FlightDTO;
import com.bnta.airline.models.Passenger;
import com.bnta.airline.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("flights")
public class FlightController {

    @Autowired
    FlightService flightService;

//    SHOW
    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Flight>> getFlight(@PathVariable Long id) {
        Flight foundFlight = flightService.findFlight(id);
        return new ResponseEntity(foundFlight, HttpStatus.OK);
    }

//    CREATE
    @PostMapping
    public ResponseEntity<List<Passenger>> postPassenger(@RequestBody FlightDTO flightDTO) {
        flightService.saveFlight(flightDTO);
        return new ResponseEntity(flightService.findAllFlights(), HttpStatus.CREATED);
    }

//    UPDATE OR ADD
    @PutMapping(value = "/{id}")
    public ResponseEntity<Flight> addFlight(@RequestBody FlightDTO flightDTO, @PathVariable Long id) {
        Flight addFlight = flightService.addFlight(flightDTO, id);
        return new ResponseEntity<>(addFlight, HttpStatus.OK);
    }

//    DELETE
    public ResponseEntity<Long> cancelFlight(@PathVariable Long id) {
        flightService.cancelFlight(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }



}
