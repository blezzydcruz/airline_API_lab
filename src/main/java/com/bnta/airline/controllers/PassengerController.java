package com.bnta.airline.controllers;

import com.bnta.airline.models.Flight;
import com.bnta.airline.models.FlightDTO;
import com.bnta.airline.models.Passenger;
import com.bnta.airline.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("passengers")
public class PassengerController {

    @Autowired
    PassengerService passengerService;

//    INDEX
    @GetMapping
    public ResponseEntity<List<Flight>> getAllPassengers() {
        return new ResponseEntity(passengerService.findAllPassenger(), HttpStatus.OK);
    }

//    SHOW
    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Passenger>> getPassenger(@PathVariable Long id) {
        Passenger foundPassenger = passengerService.findPassenger(id);
        return new ResponseEntity(foundPassenger, HttpStatus.OK);
    }

//    CREATE
    @PostMapping
    public ResponseEntity<Passenger> postPassenger(@RequestBody Passenger passenger) {
        passengerService.savePassenger(passenger);
        return new ResponseEntity(passengerService.findAllPassenger(), HttpStatus.CREATED);
    }

//    DELETE
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deletePassenger(@PathVariable Long id) {
        passengerService.removePassengerFromFlight(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

//    UPDATE OR ADD
    @PutMapping(value = "/{id}")
    public ResponseEntity<Passenger> addPassenger(@RequestBody Passenger passenger, @PathVariable Long id) {
        passengerService.addPassenger(passenger, id);
        return new ResponseEntity<>(passenger, HttpStatus.OK);
    }






}
