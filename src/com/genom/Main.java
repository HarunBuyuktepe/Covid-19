package com.genom;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Korona alacağımız önlemlerden daha güçlü değildir.");
        String NepalString = new String(Files.readAllBytes(Paths.get(("Covids\\NEPAL.txt"))));	//Input File read
        String WuhanString = new String(Files.readAllBytes(Paths.get(("Covids\\WUHAN.txt"))));	//Input File read
        NepalString=NepalString.replaceAll(" ","");
        WuhanString=WuhanString.replaceAll(" ","");
        String pureNepal="";
        for (int i=0;i<NepalString.length();i++){
            if(NepalString.charAt(i)=='a' || NepalString.charAt(i)=='t' ||NepalString.charAt(i)=='c' ||NepalString.charAt(i)=='g' ){
                pureNepal=pureNepal+NepalString.charAt(i);
            }
        }
        String pureWuhan="";
        for (int i=0;i<WuhanString.length();i++){
            if(WuhanString.charAt(i)=='a' || WuhanString.charAt(i)=='t' ||WuhanString.charAt(i)=='c' ||WuhanString.charAt(i)=='g' ){
                pureWuhan=pureWuhan+WuhanString.charAt(i);
            }
        }
        System.out.println("Nepal RNA's lenght is "+pureNepal.length()+"\n"+pureNepal);
        System.out.println("Wuhan RNA's lenght is "+pureWuhan.length()+"\n"+pureWuhan);
    }
}
