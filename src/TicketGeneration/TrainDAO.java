package TicketGeneration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class TrainDAO extends Train {
		public Train findTrain(Integer trainNumber, List<Train> trains) {
			// TODO Auto-generated method stub
			for (Train train : trains) {
				if (train.getTrainNo()==trainNumber ){
				return train;
				}
			}
			System.out.println("connected ");
            return null;		
		}
	}