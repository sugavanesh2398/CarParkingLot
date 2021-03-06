package main.java;

import java.util.Scanner;

public class CarParkingLot {
    public static CarParking[] slots;
    public static void main(String[] args) {
        boolean set=true;
        while(set){
            Scanner input=new Scanner(System.in);
            String inputString= input.nextLine();
            if(inputString.split(" ").length==1){
                if(inputString.equalsIgnoreCase("status")) {
                    System.out.println("Slot No.");
                    checkStatus("slot");
                    System.out.println("Registration No");
                    checkStatus("regNm");
                    System.out.println("Colour");
                    checkStatus("color");
                }else if(inputString.equalsIgnoreCase("exit")){
                    set=false;
                }
            }else if(inputString.split(" ").length==2){
                if(inputString.split(" ")[0].split("_").length==1){
                    slots[Integer.parseInt(inputString.split(" ")[1])-1]=null;
                    System.out.println("Slot number "+inputString.split(" ")[1]+" is free");
                }else if(inputString.split(" ")[0].split("_").length==3){
                    slots=new CarParking[Integer.parseInt(inputString.split(" ")[1])];
                    System.out.println("Created a parking lot with 6 slots");
                }else if(inputString.split(" ")[0].split("_").length==5){
                     checkStatus("checkByRegNm_"+inputString.split(" ")[1]);
                }else if(inputString.split(" ")[0].split("_").length==6){
                    checkStatus("checkByColor_"+inputString.split(" ")[1]);
                }
            }else if(inputString.split(" ").length==3){
                int availableSlot=checkAvailableSlot();
                if(availableSlot==-1){
                    System.out.println("Sorry, parking lot is full");
                }else{
                    CarParking create=new CarParking();
                    create.setCarColor(inputString.split(" ")[2]);
                    create.setRegNm(inputString.split(" ")[1]);
                    create.setSlotNumber(availableSlot+1);
                    slots[availableSlot]=create;
                    System.out.println("Allocated slot number: "+(availableSlot+1));
                }
            }
        }
    }
    private static int checkAvailableSlot(){
        for(int i=0;i<slots.length;i++){
            if(slots[i]==null){
                return i;
            }
        }
        return -1;
    }
    private static void checkStatus(String propertyNm){
        int flag=0;
        for(int i=0;i<slots.length;i++){
            if(slots[i]!=null){
                if(propertyNm.equals("slot")){
                    System.out.println(slots[i].getSlotNumber());
                }
                if(propertyNm.equals("regNm")){
                    System.out.println(slots[i].getRegNm());
                }
                if(propertyNm.equals("color")){
                    System.out.println(slots[i].getCarColor());
                }
                if(propertyNm.split("_")[0].equals("checkByColor")) {
                    if (propertyNm.split("_")[1].equals(slots[i].getCarColor()) ) {
                        if(flag==1){
                            System.out.print(", ");
                        }
                        flag = 1;
                        System.out.print(slots[i].getSlotNumber());
                    }
                    if(i==slots.length-1){
                        if(flag==0){
                            System.out.println("Not found");
                        }
                        System.out.println();
                    }
                }
                if(propertyNm.split("_")[0].equals("checkByRegNm")) {
                    if (propertyNm.split("_")[1].equals(slots[i].getRegNm())) {
                        if(flag==1){
                            System.out.print(", ");
                        }
                        flag = 1;
                        System.out.print(slots[i].getSlotNumber() );

                    }
                    if (i == slots.length - 1) {
                        if (flag == 0) {
                            System.out.println("Not found");
                        }
                        System.out.println();
                    }
                }
            }

        }
    }
}
