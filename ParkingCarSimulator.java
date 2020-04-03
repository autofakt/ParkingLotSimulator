import java.util.Scanner;

class ParkedCar{
	private String make;
	private String model;
	private String color;
	private String license;
	private int minutesParked;
	
	ParkedCar(){
		make = "";
		model = "";
		color = "";
		license = "";
		minutesParked = 0;
	}

	ParkedCar(String make, String model, String color, String license, int minutesParked){
		this.make = make;
		this.model = model;
		this.color = color;
		this.license = license;
		this.minutesParked = minutesParked;
	}
	String getMake() {
		return this.make;
	}
	String getModel() {
		return this.model;
	}
	String getColor() {
		return this.color;
	}
	String getLicense() {
		return this.license;
	}
	int getMinutesParked() {
		return this.minutesParked;
	}
}

class ParkingMeter{
	private int minutesPurchased;
	ParkingMeter(){
		minutesPurchased = 0;
	}
	ParkingMeter(int minutes){
		this.minutesPurchased = minutes;
	}
	int getMinutesPurchased() {
		return this.minutesPurchased;
	}
}

class ParkingTicket{
	private ParkedCar car;
	private double fine;
	ParkingTicket(ParkedCar car){
		this.car = car;
		this.fine = 0;
	}
	double calculateFine(ParkedCar car,ParkingMeter meter) {
		if(car.getMinutesParked() <= meter.getMinutesPurchased())
			return 0;
		else if(car.getMinutesParked() > meter.getMinutesPurchased()) {
			int overtime = car.getMinutesParked() - meter.getMinutesPurchased();
				if (overtime <= 60)
					return 25.0;
				else {
					int hours = overtime/60;
					int minutes = 0;
					if(overtime%60 > 0)
						minutes = 1;
					return (hours*25.0) + (minutes*10);
				}		
		}	
		else
			return 0;
	}
	
	String getOfficerName(PoliceOfficer police) {
		return police.getOfficerName();
	}
	int getOfficerBadgeNumber(PoliceOfficer police) {
		return police.getOfficerBadgeNumber();
	}
	String getMake() {
		return this.car.getMake();
	}
	String getModel() {
		return this.car.getModel();
	}
	String getColor() {
		return this.car.getColor();
	}
	String getLicense() {
		return this.car.getLicense();
	}
}

class PoliceOfficer{
	private String name;
	private int badgeNumber;
	PoliceOfficer(){
		name = "";
		badgeNumber = 0;
	}
	PoliceOfficer(String name, int badgeNumber){
		this.name = name;
		this.badgeNumber = badgeNumber;
	}
	String getOfficerName() {
		return this.name;
	}
	int getOfficerBadgeNumber() {
		return this.badgeNumber;
	}
	boolean examine(ParkedCar car, ParkingMeter meter) {
		if(car.getMinutesParked() <= meter.getMinutesPurchased())
			return false; // No ticket needed
		else
			return true;  // Ticket needed
	}
	void issueTicket(ParkedCar car, ParkingMeter meter, ParkingTicket ticket) {
		if(examine(car,meter)) { //Ticket needed
			System.out.println("Car parking time has expired.");
			System.out.println();
			System.out.println("Ticket data: ");
			System.out.println();
			System.out.println("Make: " + ticket.getMake());
			System.out.println();
			System.out.println("Model: " + ticket.getModel());
			System.out.println();
			System.out.println("Color: " + ticket.getColor());
			System.out.println();
			System.out.println("Liscense Number: " + ticket.getLicense());
			System.out.println();
			System.out.println("Officer Name: " + getOfficerName());
			System.out.println();
			System.out.println("Badge Number: " + getOfficerBadgeNumber());
			System.out.println();
			System.out.println("Fine: " + ticket.calculateFine(car, meter));
			
		}
		else
			System.out.println("The car parking minutes are valid");
	}
}
public class ParkingCarSimulator {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		String officersName;
		System.out.println("Enter the officer's name");
		officersName = keyboard.nextLine();
		
		int badgeNumber;
		System.out.println("Enter officer's badge number");
		badgeNumber = keyboard.nextInt();
		keyboard.nextLine();
		
		String carMake;
		System.out.println("Enter the car's make");
		carMake = keyboard.nextLine();
		
		String carModel;
		System.out.println("Enter the car's model");
		carModel = keyboard.nextLine();
		
		String carColor;
		System.out.println("Enter the car's color");
		carColor = keyboard.nextLine();
		
		String carLicense;
		System.out.println("Enter the car's liscense number");
		carLicense = keyboard.nextLine();
		
		int carMinutes;
		System.out.println("Enter Minutes on car");
		carMinutes = keyboard.nextInt();
		while(carMinutes<0) {
			System.out.println("Invalid Entry. Please try again.");
			carMinutes = keyboard.nextInt();
		}
		//keyboard.nextLine();
		
		int meterMinutes;
		System.out.println("Enter the number of minutes purchased on the meter");
		meterMinutes = keyboard.nextInt();
		while(meterMinutes<1) {
			System.out.println("Invalid Entry. Please try again");
			meterMinutes = keyboard.nextInt();
		}
		//keyboard.nextLine();
		
		ParkedCar myCar = new ParkedCar(carMake, carModel, carColor, carLicense,carMinutes);
		ParkingMeter myMeter = new ParkingMeter(meterMinutes);
		ParkingTicket myTicket = new ParkingTicket(myCar); //Used object here to not retype the same fields again.
		PoliceOfficer myOfficer = new PoliceOfficer(officersName, badgeNumber);
		myOfficer.issueTicket(myCar, myMeter, myTicket);
		
		
		
		
		

	}

}
