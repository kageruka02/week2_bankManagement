package com.bank;

import java.util.Arrays;

public class javaDsa {
    public static void main(String[] args){
        int[] a = {1,2,3,4,5,6};
        int[] b = new int[9];

        System.arraycopy(a,2,b, 7, 2);
        System.out.println(Arrays.toString(b));
        try{
            getDown();
        }
        catch( Exception e){
            System.out.println(e.getCause().toString());
        }



    }
    public static void getDown(){
        try{
            throw new IllegalArgumentException("damn");
        }
        catch(Exception e){
           NullPointerException pointerException = new  NullPointerException(e.getMessage());
           pointerException.initCause(e);
           throw pointerException;


        }

    }
}
