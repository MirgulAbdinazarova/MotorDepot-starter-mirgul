package com.company;

import com.company.service.Service;
import com.company.service.ServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Main {

    public static final GsonBuilder BUILDER = new GsonBuilder();
    public static final Gson GSON = BUILDER.setPrettyPrinting().create();
    public static final Path WRITE_PATH = Paths.get("./truck.json");
    public static final Path WRITE_PATH1 = Paths.get("./driver.json");

    public static void main(String[] args) throws Exception {

       Scanner ss = new Scanner(System.in);
       ServiceImpl service = new ServiceImpl();

        while (true) {
            System.out.println(service.getTrucks());
//            System.out.println();
//            System.out.println(service.getDrivers());
            System.out.println("Choose one of the truck");
            int truckId = ss.nextInt();
            System.out.println("-------Trucke in------");
            for (int i = 0; i < service.getTrucks().size(); i++) {
                if (service.getTrucks().get(i).getId() == truckId) {
                    System.out.println(" № :" + service.getTrucks().get(i).getId() + "\n"
                            + "Truck:" + service.getTrucks().get(i).getTruckName() + "\n"
                            + "Driver:" + service.getTrucks().get(i).getDriver() + "\n"
                            + "State:" + service.getTrucks().get(i).getState());

                }
            }
            System.out.println();
            buttons();
            System.out.println("Choose a command");
            ss.nextLine();
            String choose = ss.nextLine();
            switch (choose) {
                case "1" -> service.changeDriver(truckId);
                case "2" -> service.startDriving(truckId);
                case "3" -> service.startRepair(truckId);
                case "4" -> service.changeTruckState();
                default -> System.out.println("No such choose");
            }

        }

    }











    public static void buttons(){
        System.out.println("Press 1 to change Driver\n" +
        "Press 2 to send to the Route\n" +
        "Press 3 to send to the Repairing\n"+
         "Press 4 to chang truck state\n");
    }


   public static String readTtuck() {
       return getString(WRITE_PATH);
   }

   public static String readDriver() {
       return getString(WRITE_PATH1);
   }

    private static String getString(Path writePath1) {
        StringBuilder json = new StringBuilder();
        try (FileReader fr = new FileReader(String.valueOf(writePath1))){
            int a;
            while ((a = fr.read()) != -1) {
                json.append((char) a);
            }
            return json.toString();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return json.toString();
    }
}