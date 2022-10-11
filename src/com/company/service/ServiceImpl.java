package com.company.service;


import com.company.entities.Driver;
import com.company.entities.State;
import com.company.entities.Truck;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.company.Main.*;

public class ServiceImpl implements Service{
    static Scanner sc=new Scanner(System.in);
    List<Truck> trucks = new ArrayList<>(List.of(GSON.fromJson(readTtuck(), Truck[].class)));
    List<Driver> drivers = new ArrayList<>(List.of(GSON.fromJson(readDriver(),Driver[].class)));


    public List<Truck> getTrucks() {
        return trucks;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }


    @Override
    public void changeDriver(int id) {

        for (Truck t : trucks) {
            if (t.getId() == id && t.getState() == State.BASE) {
                for (Driver d:drivers) {
                    if (d.getTruckName().equals(t.getTruckName())){
                        d.setTruckName("null");
                    }
                    if (d.getTruckName().equals("")) {
                        d.setTruckName(t.getTruckName());
                        t.setDriver(d.getName());
                        System.out.println("Теперь грузовик" + " " + t.getTruckName() + ", ведёт водитель "
                                + d.getName());
                        break;
                    }
                    if (d.getTruckName().equals("null")){
                        d.setTruckName("");
                    }
                }
            } else if (t.getId() == id && t.getState() == State.ROUTE) {
                System.out.println("Грузовик в пути, невозможно сменить водителя");

            } else if (t.getId() == id && t.getState() == State.REPAIR) {
                System.out.println("Нельзя сменить водителя");
            }
        }

        }
    @Override
    public void startDriving(int id) {
        for (Truck t : trucks) {
            if (t.getId() == id && t.getState() != State.ROUTE && !t.getDriver().equals(" ")) {
                t.setState(State.ROUTE);
                System.out.println("успешно вышли на маршрут");
                break;
            }
        }
    }



    @Override
    public void startRepair(int id) {
        for (Truck t : trucks) {
            if(t.getId()==id && t.getState() !=State.ROUTE){
               t.setState(State.REPAIR);
            }

        }
    }


    @Override
    public void changeTruckState() {
        System.out.println("Enter truck id");
        int truckId = sc.nextInt();
        for (Truck t : trucks) {
            if(t.getId()==truckId && t.getState() ==State.BASE && !t.getDriver().equals(" ")){
                t.setState(State.ROUTE) ;
            } else if (t.getId()==truckId && t.getState()==State.ROUTE) {
                t.setState(State.BASE );
            }else if(t.getId()==truckId && t.getState()==State.REPAIR && !t.getDriver().equals(" ")){
                t.setState(State.BASE);

            }
        }
    }
}












