package com.example.bottledispensergame;

/*
 Tekijä: Jonne Myöhänen
Opiskelijanumero: 616251
Päivämäärä: 25.1.2021
Yhteistyö: Luennot
 */
public class Bottle {
    private String name = "Pepsi Max";
    private String manufacturer = "Pepsi";
    private double total_energy = 0.3;
    private double size = 0.5;
    private double price = 1.80;
    private int id = 1;

    public Bottle(){}
    public Bottle(String name, String manuf, float totE){}

    public String getName(){
        return name;
    }
    public String getManufacturer(){
        return manufacturer;
    }
    public double getEnergy(){
        return total_energy;
    }

    public double getPrice(){
        return price;
    }

    public double getSize(){
        return size;
    }

    public int getId(){
        return id;
    }

    public String setName(String n){
        name = n;
        return name;
    }

    public String setSize(double n){
        size = n;
        return name;
    }

    public double setPrice(double n){
        price = n;
        return price;
    }

    public int setId(int n){
        id = n;
        return id;
    }
}
