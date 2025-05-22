package com.L1.Parking_Lot;
import java.util.*;
public class main {
    public static void main(String[] args) {

    }
    static void solve() {

    }
}
class PL {
    int cnt_floor,cnt_slots;
    String id;
    Pair[][] arr;
    PL(String s) {
        String[] temp=s.split(" ");
        cnt_floor=Integer.parseInt(temp[2]);
        cnt_slots=Integer.parseInt(temp[3]);
        arr=new Pair[cnt_floor][cnt_slots];
        id=temp[0];
        System.out.println("create_parking_lot "+temp[1]+" "+cnt_floor+" "+cnt_slots);
    }
    void park_vehicle(String s) {
        String[] temp=s.split(" ");
        int st=2,ed=cnt_slots-1;
        if(temp[1].equals("TRUCK")) {
            st=0;ed=0;
        } else if(temp[1].equals("BIKE")) {
            st=1;ed=2;
        }
        for(int i=0;i<cnt_floor;i++) {
            for(int j=st;j<=ed&&j<cnt_slots;j++) {
                if(arr[i][j]==null) {
                    System.out.println("Parked vehicle. Ticket ID: "+id+"_"+(i+1)+"_"+(j+1));
                    arr[i][j]=new Pair(temp[3],temp[4]);
                    return;
                }
            }
        }
        System.out.println("Parking Lot Full");
    }
    void unpark_vehicle(String s) {
        String[] temp=s.split(" ");
        String[] reg=temp[1].split("_");
        int floor=Integer.parseInt(reg[1]);
        int slot=Integer.parseInt(reg[2]);
        if(floor>cnt_floor ||slot>cnt_slots ||!reg[0].equals(id) ||arr[floor][slot]==null) {
            System.out.println("Invalid Ticket");
            return;
        }
        System.out.println("Unparked vehicle with Registration Number: "+arr[floor][slot].reg_no+" and Color: "+arr[floor][slot].color);
        arr[floor][slot]=null;
    }
    void free_count(String s) {
        String[] temp=s.split(" ");
        int st=2,ed=cnt_slots-1;
        if(temp[2].equals("TRUCK")) {
            st=0;ed=0;
        } else if(temp[2].equals("BIKE")) {
            st=1;ed=2;
        }
        for(int i=0;i<cnt_floor;i++) {
            int c=0;
            for(int j=st;j<=ed&&j<cnt_slots;j++) {
                if(arr[i][j]==null) c++;
            }
            System.out.println("No. of free slots for "+temp[2]+" on Floor "+(i+1)+": "+c);
        }
    }
    void free_slots(String s) {
        String[] temp=s.split(" ");
        int st=2,ed=cnt_slots-1;
        if(temp[2].equals("TRUCK")) {
            st=0;ed=0;
        } else if(temp[2].equals("BIKE")) {
            st=1;ed=2;
        }
        for(int i=0;i<cnt_floor;i++) {
            ArrayList<Integer> list=new ArrayList<>();
            for(int j=st;j<=ed&&j<cnt_slots;j++) {
                if(arr[i][j]==null) list.add(j+1);
            }
            System.out.print("Free slots for "+temp[2]+" on Floor "+(i+1)+": ");
            for(int j=0;j<list.size();j++) System.out.print(list.get(j)+" ");
            System.out.println();
        }
    }
    void occupied_slots(String s) {
        String[] temp=s.split(" ");
        int st=2,ed=cnt_slots-1;
        if(temp[2].equals("TRUCK")) {
            st=0;ed=0;
        } else if(temp[2].equals("BIKE")) {
            st=1;ed=2;
        }
        for(int i=0;i<cnt_floor;i++) {
            ArrayList<Integer> list=new ArrayList<>();
            for(int j=st;j<=ed&&j<cnt_slots;j++) {
                if(arr[i][j]!=null) list.add(j+1);
            }
            System.out.print("Occupied slots for "+temp[2]+" on Floor "+(i+1)+": ");
            for(int j=0;j<list.size();j++) System.out.print(list.get(j)+" ");
            System.out.println();
        }
    }
}
class Pair{
    String reg_no,color;
    Pair(String reg_no,String color) {
        this.reg_no=reg_no;
        this.color=color;
    }
}