package TicketGeneration;

import java.io.FileWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Ticket {
	
	
	static int counter = 100;
	String pnr;
	Date travelDate;
	Train train;
	TreeMap<Passenger, Integer> passengers;
	
	
	public Ticket() {
		super();
	}
    public Ticket(int counter, String pnr, Date travelDate, Train train, TreeMap<Passenger, Integer> passengers) {
		super();
		this.counter = counter;
		this.pnr = pnr;
		this.travelDate = travelDate;
		this.train = train;
		this.passengers = passengers;
	}
    public int getCounter() {
		return counter;
	}
    public void setCounter(int counter) {
		this.counter = counter;
	}
    public String getPnr() {
		return pnr;
	}
    public void setPnr(String pnr) {
		this.pnr = pnr;
	}
    public Date getTravelDate() {
		return travelDate;
	}
    public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}
    public Train getTrain() {
		return train;
	}


	public void setTrain(Train train) {
		this.train = train;
	}
    public TreeMap<Passenger, Integer> getPassengers() {
		return passengers;
	}
    public void setPassengers(TreeMap<Passenger, Integer> passengers) {
		this.passengers = passengers;
	}
   @Override
	public String toString() {
		return "Ticket [counter=" + counter + ", pnr=" + pnr + ", travelDate=" + travelDate + ", train=" + train
				+ ", passengers=" + passengers + "]";
	}
	public static String generatePNR(List<Passenger> passengers, Train train, java.util.Date date) {
		String PNR = null;
		String source = Character.toString(train.getSource().charAt(0));
		String destination = Character.toString(train.getDestination().charAt(0));

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String dateTime = dateFormat.format(date);
		System.out.println("Date is "+dateTime);
		
		PNR = source+destination+"_"+dateTime+"_"+counter;
		counter++;
		System.out.println("PNR "+PNR);
				
		
		return PNR;
		
	}
	public static Double calPassengerFare(List<Passenger> passengers2, Train train2) {
		Double totalPriceDouble = 0.00;
		Double passengerFareDouble = 0.00;
		for (Passenger passenger : passengers2) {
			if (passenger.getAge()<=12) {
				passengerFareDouble = train2.getTicketPrice()*0.5;
				if (passenger.getGender()=="F" || passenger.getGender()=="f") {
					passengerFareDouble = passengerFareDouble-passengerFareDouble*0.25;
				}
			}
			if (passenger.getAge()>=60) {
				passengerFareDouble = train2.getTicketPrice()/0.6;
				if (passenger.getGender()=="F" || passenger.getGender()=="f") {
					passengerFareDouble =passengerFareDouble-passengerFareDouble*0.75;
				}
			}
			if (passenger.getGender()=="F" || passenger.getGender()=="f") {
				passengerFareDouble =passengerFareDouble-passengerFareDouble*0.75;
			}
			System.out.println("fare " +passengerFareDouble);
			totalPriceDouble = calculaTotalTicketPrice(passengerFareDouble , totalPriceDouble);
		}
		
		
		System.out.println("Total " +totalPriceDouble);
		return passengerFareDouble;
	}
	public static Double calculaTotalTicketPrice(Double passengerFareDouble, Double totalPriceDouble) {
		totalPriceDouble= totalPriceDouble+passengerFareDouble;
		return totalPriceDouble;
	}
	
	public static Map<String, String> generateTicket(Double passengerFare, java.util.Date date, Train train2, List<Passenger> passengers2, String pNRString, int noOfPassenger) {
		System.out.println("Here");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		String dateTime = dateFormat.format(date);
		Map<String, String> tickeMap = new HashMap<>();
		tickeMap.put("PNR", pNRString);
		tickeMap.put("Train No", Integer.toString(train2.getTrainNo()));
		tickeMap.put("Train Name", train2.getTrainName());
		tickeMap.put("From :", train2.getSource());
		tickeMap.put("To :", train2.getDestination());
		tickeMap.put("Travel Date :", dateTime);
		tickeMap.put("PNR", pNRString);
		tickeMap.put("Passengers", Integer.toString( noOfPassenger));
		while(noOfPassenger!=0) {
			for (Passenger passenger : passengers2) {
				tickeMap.put("Passenger Name :", passenger.getName());
				tickeMap.put("Passenger Age  ",Integer.toString(passenger.getAge()) );
				tickeMap.put("Passenger Gender :", passenger.getGender());
			}	
			noOfPassenger--;		}
		
		return tickeMap;
		
	}
	
	public static void writeTicket(Map<String, String> ticketMap) {
		try{    
	           FileWriter fw=new FileWriter("file");  
	           String string = ticketMap.toString();
	           fw.write(string);  
	           System.out.println("Successfully Written..");
	           fw.close();   
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	

	

}