package com.TheTallestOfMidgets.UTIL;

import java.util.ArrayList;

public class ArrayList2Array {
    public static byte[] byteArray(ArrayList<Byte> arrayList){
        byte[] array = new byte[arrayList.size()];
        for(int i = 0; i < array.length; i++){
            array[i] = arrayList.get(i);
        }
        return array;
    }
}
