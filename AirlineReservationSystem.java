package com.ARS;
import java.util.ArrayList;
import java.util.Scanner;

class Flight {
    String flightNumber;
    String destination;
    String departure;
    int totalSeats;
    int availableSeats;

    public Flight(String flightNumber, String destination, String departure, int totalSeats) {
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.departure = departure;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    @Override
    public String toString() {
        return "Flight Number: " + flightNumber + ", Destination: " + destination +
                ", Departure: " + departure + ", Total Seats: " + totalSeats +
                ", Available Seats: " + availableSeats;
    }
}

class Reservation {
    String passengerName;
    String flightNumber;
    int seatsBooked;

    public Reservation(String passengerName, String flightNumber, int seatsBooked) {
        this.passengerName = passengerName;
        this.flightNumber = flightNumber;
        this.seatsBooked = seatsBooked;
    }

    @Override
    public String toString() {
        return "Passenger: " + passengerName + ", Flight Number: " + flightNumber +
                ", Seats Booked: " + seatsBooked;
    }
}

public class AirlineReservationSystem {
    static ArrayList<Flight> flights = new ArrayList<>();
    static ArrayList<Reservation> reservations = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Airline Reservation System ===");
            System.out.println("1. Add Flight");
            System.out.println("2. View Flights");
            System.out.println("3. Book Ticket");
            System.out.println("4. View Reservations");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addFlight();
                case 2 -> viewFlights();
                case 3 -> bookTicket();
                case 4 -> viewReservations();
                case 5 -> {
                    System.out.println("Exiting system. Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    static void addFlight() {
        System.out.print("Enter Flight Number: ");
        String flightNumber = scanner.nextLine();
        System.out.print("Enter Destination: ");
        String destination = scanner.nextLine();
        System.out.print("Enter Departure Time: ");
        String departure = scanner.nextLine();
        System.out.print("Enter Total Seats: ");
        int totalSeats = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        flights.add(new Flight(flightNumber, destination, departure, totalSeats));
        System.out.println("Flight added successfully.");
    }

    static void viewFlights() {
        System.out.println("\n=== Flights List ===");
        for (Flight flight : flights) {
            System.out.println(flight);
        }
    }

    static void bookTicket() {
        System.out.print("Enter Passenger Name: ");
        String passengerName = scanner.nextLine();
        System.out.print("Enter Flight Number: ");
        String flightNumber = scanner.nextLine();
        System.out.print("Enter Number of Seats to Book: ");
        int seats = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Flight selectedFlight = null;
        for (Flight flight : flights) {
            if (flight.flightNumber.equals(flightNumber)) {
                selectedFlight = flight;
                break;
            }
        }

        if (selectedFlight == null) {
            System.out.println("Flight not found!");
            return;
        }

        if (selectedFlight.availableSeats < seats) {
            System.out.println("Not enough seats available!");
            return;
        }

        selectedFlight.availableSeats -= seats;
        reservations.add(new Reservation(passengerName, flightNumber, seats));
        System.out.println("Ticket booked successfully.");
    }

    static void viewReservations() {
        System.out.println("\n=== Reservations List ===");
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }
}
