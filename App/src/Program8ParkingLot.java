// Parking lot using open addressing with linear probing

import java.util.*;

public class Program8ParkingLot {

    static String[] spots = new String[500];

    public static int hash(String plate) {
        return Math.abs(plate.hashCode()) % spots.length;
    }

    public static void parkVehicle(String plate) {

        int index = hash(plate);
        int probes = 0;

        while (spots[index] != null) {
            index = (index + 1) % spots.length;
            probes++;
        }

        spots[index] = plate;

        System.out.println(plate + " parked at spot " + index + " (" + probes + " probes)");
    }

    public static void exitVehicle(String plate) {

        for (int i = 0; i < spots.length; i++) {

            if (plate.equals(spots[i])) {
                spots[i] = null;
                System.out.println("Vehicle exited from spot " + i);
                return;
            }
        }
    }

    public static void main(String[] args) {

        parkVehicle("ABC1234");
        parkVehicle("ABC1235");

        exitVehicle("ABC1234");
    }
}