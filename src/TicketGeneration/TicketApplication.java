package TicketGeneration;

import java.util.Date;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.naming.AuthenticationException;

public class TicketApplication extends Ticket {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner myScanner = new Scanner(System.in);

		List<Train> trains = new ArrayList<>();
		trains.add(new Train(1001, "Shatabdi Express", "Bangalore", "Delhi", 2500.00));
		trains.add(new Train(1002, "Shatabdi Express", "Delhi", "Bangalore", 2500.00));
		trains.add(new Train(1003, "Udyan Express", "Bangalore", "Mumbai", 1500.00));
		trains.add(new Train(1004, "Udyan Express", "Mumbai", "Bangalore", 1500.00));
		trains.add(new Train(1005, "Brindavan Express", "Bangalore", "Chennai", 1000.00));
		trains.add(new Train(1006, "Brindavan Express", "Chennai", "Bangalore", 1000.00));

		System.out.println("Trains " + trains);

		System.out.println("Enter the Train Number");
		Integer trainNumber = myScanner.nextInt();
		System.out.println("Train No : " + trainNumber);
		TrainDAO trainDAO = new TrainDAO();
		Train train = trainDAO.findTrain(trainNumber, trains);
		if (train!=null) {
			System.out.println("Enter Travel Date");
			try {
				java.util.Date date = readDate();
				System.out.println("Date : " + date);
				Date now = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
				String str = formatter.format(date);
				System.out.print("Current date: " + str);

				if (date.after(now)) {
					System.out.println("Enter Number Of Passenger");
					int noOfPassenger = myScanner.nextInt();
					System.out.println(noOfPassenger);
					if (noOfPassenger != 0) {
					List<Passenger> passengers = createPassenger(myScanner, noOfPassenger);
					System.out.println("Final Passenger List " + passengers);
					String PNRString = Ticket.generatePNR(passengers,train, date);
					System.out.println("Ticket Booked with PNR :" +PNRString);
					
					Double passengerFare= Ticket.calPassengerFare(passengers,train);
					
					Map<String, String> ticketMap = Ticket.generateTicket(passengerFare , date, train, passengers,PNRString, noOfPassenger);
					System.out.println("The generated ticket is "+ ticketMap);
					
					Ticket.writeTicket(ticketMap);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	private static List<Passenger> createPassenger(Scanner myScanner, Integer noOfPassenger) {
		// TODO Auto-generated method stub
		List<Passenger> passengers = new ArrayList<>();
		while(noOfPassenger!=0) {
			System.out.println(" \n Enter Passenger Name");
			String name = myScanner.next();
			if (name!=null) {
				System.out.println("Enter Age");
				int age = myScanner.nextInt();
				if (name!=null && age != 0) {
					System.out.println("Enter Gender");
					String gender = myScanner.next();
					passengers.add(new Passenger(name, age, gender));
				}
			}
			
			noOfPassenger--;
		}
			System.out.println("Passengers " + passengers);
				return passengers;
	}
	public static java.util.Date readDate() throws Exception {
		String dateFormat = "dd/MM/yyyy";
		Scanner scanner = new Scanner(System.in);
		return (new SimpleDateFormat(dateFormat).parse(scanner.nextLine()));
	}

}