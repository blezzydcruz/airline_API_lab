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

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    PassengerRepository passengerRepository;

//    ADD NEW FLIGHT
    public Flight addFlight(FlightDTO flightDTO, Long id) {
        Flight flight = new Flight(flightDTO.getDestination(), flightDTO.getCapacity(), flightDTO.getDepartureDate(), flightDTO.getDepartureTime());
            return flightRepository.save(flight);
    }

//    DISPLAY AVAILABLE FLIGHTS
    public List<Flight> findAllFlights() {
        return flightRepository.findAll();
    }

//    DISPLAY SPECIFIC FLIGHT
    public Flight findFlight(Long id) {
        return flightRepository.findById(id).get();
    }

//    CANCEL FLIGHT
    public void cancelFlight(Long id) {
        flightRepository.deleteById(id);
    }

    public void saveFlight(FlightDTO flightDTO) {
        Flight flight = new Flight(flightDTO.getDestination(), flightDTO.getCapacity(), flightDTO.getDepartureDate(), flightDTO.getDepartureTime());

        for (Long passengerId : flightDTO.getPassengerIds()) {
            Passenger passenger = passengerRepository.findById(passengerId).get();
            flight.addPassenger(passenger);
        }
        flightRepository.save(flight);
    }

//    BOOK PASSENGERS ON TO FLIGHT
    public Flight addPassengerToFlight(FlightDTO flightDTO, Long id) {
        Flight passengerAddedToFlight = flightRepository.findById(id).get();
        passengerAddedToFlight.setDestination(flightDTO.getDestination());
        passengerAddedToFlight.setCapacity(flightDTO.getCapacity());
        passengerAddedToFlight.setDepartureDate(flightDTO.getDepartureDate());
        passengerAddedToFlight.setDepartureTime(flightDTO.getDepartureTime());

        passengerAddedToFlight.setPassengers(new ArrayList<Passenger>());

        for (Long passengerId : flightDTO.getPassengerIds()) {
            Passenger passenger = passengerRepository.findById(passengerId).get();
            passengerAddedToFlight.addPassenger(passenger);
        }
        flightRepository.save(passengerAddedToFlight);
        return passengerAddedToFlight;
    }


}
