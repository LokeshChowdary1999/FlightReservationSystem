import java.util.*;
public class CentralFlightReservationSystem {

    public static void addFlights(List<Flight> flightList) {
        listOfFlights.addAll(flightList);
    }

    public static void addPassengerTicket(Map<Passenger, List<Ticket>> map) {
        passengerTicket.putAll(map);

    }

    public static void storeTicketDetails(Passenger passenger, Ticket ticket) {
        List<Ticket> list = passengerTicket.get(passenger);
        if (list != null) {
            list.add(ticket);
        }

        else {
            list = new ArrayList<>();
            list.add(ticket);
        }
        passengerTicket.put(passenger, list);
    }

    /**
     * Print all the flights which have vacant seats
     */
    public static void printAvailableFlights() {
        List<Flight> availableFlights = getAvailableFlights();

        System.out.println("These are available flights ");

        for (int i = 0; i < availableFlights.size(); i++) {
            System.out.println("serial number " + i + ", flight: " + availableFlights.get(i).getAirlineName()
                    + ", seats remaining:  " + availableFlights.get(i).getCurrentCapacity());
        }
    }

    /**
     *
     * @return list of all flights with vacant seats
     */
    public static List<Flight> getAvailableFlights() {

        List<Flight> availableFlights = new ArrayList<>();

        for (int i = 0; i < listOfFlights.size(); i++) {
            if (listOfFlights.get(i).getNoOfSeatsBooked() < listOfFlights.get(i).getCapacity()) {
                availableFlights.add(listOfFlights.get(i));
            }
        }

        return availableFlights;
    }

    public static String checkStatus(long PNRNumber, Passenger p) {
        List<Ticket> ticket = CentralFlightReservationSystem.getAllTickets(p);
        for (Ticket t : ticket) {
            if (t.getPNRNumber() == PNRNumber) {
                return t.getStatus();
            }
        }
        return "Not found";
    }

    public static void updateServices(int op2, long pNRNumber, Passenger p) {

        System.out.println("enter service");
        String service = sc.next();
        List<Ticket> ticket = CentralFlightReservationSystem.getAllTickets(p);
        for (Ticket t : ticket) {
            if (t.getPNRNumber() == pNRNumber) {
                if (t instanceof RegularTicket) {
                    switch (op2) {

                        case 1:
                            ((RegularTicket) t).addService(service);
                            break;

                        case 2:
                            System.out.println("enter index of updation..");
                            int index = sc.nextInt();
                            ((RegularTicket) t).setServices(index, service);
                            break;

                        case 3:
                            ((RegularTicket) t).deleteService(service);
                            break;
                    }
                }

                else {
                    System.out.println("Not a regular ticket");
                }

            }
        }
    }
    public static void main(String[] args){
        Scanner Sc=new Scanner(System.in);
        int op2=sc.nextInt();
        long pNRNumber=sc.nextLong();
        String Passenger=sc.nextPassenger();
        updateServices(int op2, long pNRNumber, Passenger p);

    }

}
