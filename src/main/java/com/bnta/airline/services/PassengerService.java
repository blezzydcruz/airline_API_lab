package com.bnta.airline.services;

import com.bnta.airline.models.Flight;
import com.bnta.airline.models.FlightDTO;
import com.bnta.airline.models.Passenger;
import com.bnta.airline.repositories.FlightRepository;
import com.bnta.airline.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {

    @Autowired
    PassengerRepository passengerRepository;

//        DISPLAY ALL PASSENGERS
    public List<Passenger> findAllPassenger() {
        return passengerRepository.findAll();
    }

    public Passenger findPassenger(Long id) {
        return passengerRepository.findById(id).get();
    }

    public void savePassenger(Passenger passenger) {
        passengerRepository.save(passenger);
    }

    public void removePassengerFromFlight(Long id) {
        Passenger foundPassenger = passengerRepository.getById(id);
        for (Flight flight : foundPassenger.getFlights()) {
            flight.removePassenger(foundPassenger);
        }
    }

    public void deletePassenger(Long id) {
        passengerRepository.deleteById(id);
    }

//    ADD NEW PASSENGER
    public void addPassenger(Passenger passenger, Long id) {
        Passenger newPassenger = passengerRepository.findById(id).get();
        newPassenger.setName(passenger.getName());
        newPassenger.setEmailAddress(passenger.getEmailAddress());
        passengerRepository.save(newPassenger);
    }




}
