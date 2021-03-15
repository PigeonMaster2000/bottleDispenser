package com.example.bottledispensergame;
/*
 Tekijä: Jonne Myöhänen
Opiskelijanumero: 616251
Päivämäärä: 25.1.2021
Yhteistyö: Luennot
 */

import java.util.ArrayList;



public class BottleDispenser {
    private int bottles;
    private double money, lastBottlePrice;
    private String lastBottleName;


    private static BottleDispenser machine = new BottleDispenser();


    ArrayList<Bottle> bottleList = new ArrayList<Bottle>();

    private BottleDispenser() {
        bottles = 4;
        money = 0;
        lastBottleName ="Unknown bottle";
        lastBottlePrice = 0.0;



        for(int i = 0;i<bottles;i++) {
            // Use the default constructor to create new Bottles
            Bottle temp = new Bottle();
            if (i == 1){
                temp.setSize(1.5);
                temp.setPrice(2.2);
            }
            if (i == 2){
                temp.setName("Coca-Cola Zero");
                temp.setPrice(2.0);
                temp.setId(2);
            }
            if (i == 3){
                temp.setName("Coca-Cola Zero");
                temp.setSize(1.5);
                temp.setPrice(2.5);
                temp.setId(2);
            }
            if (i == 4){
                temp.setName("Fanta Zero");
                temp.setSize(0.5);
                temp.setPrice(1.95);
                temp.setId(4);
            }
            bottleList.add(temp);
        }
    }

    public static BottleDispenser getInstance(){
        return machine;
    }


    public void addMoney(int amount) {
        money += amount;
    }

    public String buyBottle(int id, double size) {
        String returnString = "ERROR: BuyBottle()";
        boolean found = false;
        int i = 0;
        if (bottles == 0){
            returnString ="No more bottles left in the machine.";
            return returnString;
        }
        else if (money < bottleList.get(bottles-1).getPrice()){
            returnString = "Add money first!";
            return returnString;
        }
        else{
            for (i = 0; i < bottleList.size(); i++){
                if ((id == bottleList.get(i).getId()) && (size == bottleList.get(i).getSize())){
                    found = true;
                    break;
                }
            }
            if (found == true){
                bottles -= 1;
                money -= bottleList.get(i).getPrice();
                money = Math.round(money * 100.0) / 100.0;
                returnString ="KACHUNK! "+bottleList.get(i).getName()+" came out of the dispenser!";
                lastBottlePrice = bottleList.get(i).getPrice();
                lastBottleName = bottleList.get(i).getName();
                deleteBottle(i);
            } else {
                returnString ="That particular beverage is unfortunately out of stock";
            }
        }
        return returnString;
    }

    public void deleteBottle(int n){
        bottleList.remove(n);
    }

    public String returnMoney() {
        String returnString = "ERROR: returnMoney()";
        returnString = "Klink klink. Money came out! You got "+ Double.toString(money)+"€ back";
        money = 0;
        return returnString;
    }

    public double getMoney(){
        return(money);
    }

    public double getLastBottlePrice(){
        return(lastBottlePrice);
    }
    public String getLastBottleName(){
        return(lastBottleName);
    }

}
