package com.TheTallestOfMidgets.UTIL;

import java.util.ArrayList;

public class Array2ArrayList {

    public static ArrayList<Byte> byteArray(byte[] byteArray){
        ArrayList<Byte> arrayList = new ArrayList<>();
        for(byte _byte : byteArray){
            arrayList.add(_byte);
        }
        return arrayList;
    }
}
