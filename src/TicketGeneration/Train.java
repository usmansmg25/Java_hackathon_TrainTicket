package TicketGeneration;

public class Train {
	int trainNo;
	String trainName;
	String source;
	String destination;
	Double ticketPrice;
	public Train(int trainNo, String trainName, String source, String destination, Double ticketPrice) {
		super();
		this.trainNo = trainNo;
		this.trainName = trainName;
		this.source = source;
		this.destination = destination;
		this.ticketPrice = ticketPrice;
	}
	public Train() {
		super();
	}
	public int getTrainNo() {
		return trainNo;
	}
	public void setTrainNo(int trainNo) {
		this.trainNo = trainNo;
	}
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Double getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(Double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	@Override
	public String toString() {
		return "Train [trainNo=" + trainNo + ", trainName=" + trainName + ", source=" + source + ", destination="
				+ destination + ", ticketPrice=" + ticketPrice + "]";
	}
	
	

}