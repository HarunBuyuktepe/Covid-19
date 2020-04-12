package com.genom;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/*
* 1- 5 ten baslayarak artan tekrarlari bul
* 2-  En cok tekrar eden en uzun stringler
* 3- 2 txt dosyasinda ortak olan tekrarlar
* 4- 1 karakter farkli olan tekrarlari yazdir
* */

public class Main {

    String ExampleString;
    String covidString = "";
    String NepalString = "";
    String WuhanString = "";
    HashMap<String, Integer> repetitionNumberHash;
    HashMap<String, String> oneCharacterDifferenceHash;
    HashMap<String, Integer> WuhanHash;
    HashMap<String, Integer> NepalHash;


    public Main() throws IOException {
        ExampleString = "1234544466123456767681234587678612346767612345";
        NepalString = readTxtFile("Covids\\NEPAL.txt");
        WuhanString = readTxtFile("Covids\\WUHAN.txt");
        repetitionNumberHash = new HashMap<String, Integer>();
        WuhanHash = new HashMap<String, Integer>();
        NepalHash = new HashMap<String, Integer>();
        oneCharacterDifferenceHash = new HashMap<String, String>();

    }

    public HashMap<String, Integer> getRepetitionNumberHash() {
        return repetitionNumberHash;
    }

    public void repetition(String searchText, int length){
        int numberOfRepetition = 0;
        for (int i=0; i<getCovidString().length()-length+1; i++){
                if (  searchText.compareTo(getCovidString().substring(0+i,length+i)) == 0  )
                numberOfRepetition++;
        }
        if (numberOfRepetition > 1){
            repetitionNumberHash.put(searchText,numberOfRepetition);
        }
    }

    public void findSearchTextForRepetition(int length){

        for (int i=0; i<getCovidString().length()-length+1; i++){
            repetition(getCovidString().substring(0+i,length+i),length);
        }
    }

    public void findSearchTextForDiffenceChar(int length){

        for (int i=0; i<getCovidString().length()-length+1; i++){
            searchDifference(getCovidString().substring(0+i,length+i),length);
        }
    }

    public void searchDifference(String searchText, int length){
        for (int i=0; i<getCovidString().length()-length+1; i++){
            findDifference(searchText,getCovidString().substring(0+i,length+i));
        }

    }

    public int getRepetationSize(){
        return getRepetitionNumberHash().size();
    }

    public HashMap<String, Integer> getWuhanHash() {
        return WuhanHash;
    }

    public void setWuhanHash(HashMap<String, Integer> wuhanHash) {
        WuhanHash = wuhanHash;
    }

    public HashMap<String, Integer> getNepalHash() {
        return NepalHash;
    }

    public void setNepalHash(HashMap<String, Integer> nepalHash) {
        NepalHash = nepalHash;
    }

    public void setNepalString(String nepalString) {
        NepalString = nepalString;
    }

    public void setWuhanString(String wuhanString) {
        WuhanString = wuhanString;
    }

    public String getNepalString() {
        return NepalString;
    }

    public String getWuhanString() {
        return WuhanString;
    }
    public HashMap<String, String> getOneCharacterDifferenceHash() {
        return oneCharacterDifferenceHash;
    }

    public String getCovidString() {
        return covidString;
    }

    public void setCovidString(String covidString) {
        this.covidString = covidString;
    }

    public static void main(String[] args) throws IOException {
        int length ;
        int previousSize ;
        Main main;

        Scanner in = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String number = in.nextLine();

        switch (number){
            case "1":
                System.out.println("Repetation For Wuhan");
                length = 5; previousSize = 0; main = new Main();
                System.out.println("Wuhan RNA's lenght is "+main.getWuhanString().length()+"\n"+main.getWuhanString());
                main.setCovidString(main.getWuhanString());
                main.findAllRepetationandWrite("Covids\\WUHANREPETITION.txt");
                break;

            case "2":
                System.out.println("Repetation For Nepal");
                length = 5; previousSize = 0;  main = new Main();
                System.out.println("Nepal RNA's lenght is "+main.getNepalString().length()+"\n"+main.getNepalString());
                main.setCovidString(main.getNepalString());
                main.findAllRepetationandWrite("Covids\\NEPALREPETITION.txt");
                break;
            case "3":
                System.out.println("Ucuncu Kisim");
                main = new Main();
                main.setNepalHash( main.readTxtFileToHash("Covids\\NEPALREPETITION.txt"));
                System.out.println("============== Nepal Repatition Keys =================");
                for (String  key : main.getNepalHash().keySet())
                    System.out.println(key);
                main.setWuhanHash( main.readTxtFileToHash("Covids\\WUHANREPETITION.txt"));
                System.out.println("================  Wuhan Repatition Keys ==============");
                for (String  key : main.getWuhanHash().keySet())
                    System.out.println(key);
                break;

        }

                        // Find 1 character difference ===========================

       /* Main main = new Main();
        main.setCovidString(main.getWuhanString());
        main.findSearchTextForDiffenceChar(14);
        System.out.println(main.getOneCharacterDifferenceHash());
        //main.findDifference("Yasin","Yasim");*/


       /*Main main = new Main();
        main.writeHashToTxtFile();*/

    }

    public void findAllRepetationandWrite(String filePath){
        int length =5; int previousSize =0;
        while(true){
             findSearchTextForRepetition(length);
            if ( (getRepetationSize()-previousSize) > 0){
                System.out.println("Lengt of Searching Key => "+length);
                System.out.println("Number Of Repetation => "+ (getRepetationSize()-previousSize) );
                previousSize = getRepetationSize();
                length++;
            }
            else
                break;
        }
        writeHashToTxtFile(getRepetitionNumberHash(),filePath);   //"Covids\\WUHANREPETITION.txt"

    }

    public void findDifference(String first, String second){
        int numberOfDifference = 0;
       // String one = "Yacin"; String two = "Yasin";
        char[] oneChar = first.toCharArray(); char[] twoChar = second.toCharArray();

        for (int i = 0; i<oneChar.length; i++)
            if (oneChar[i] != twoChar[i])
                numberOfDifference++;

        if (numberOfDifference == 1) {
            oneCharacterDifferenceHash.put(first,second);
        }

    }


    public String readTxtFile(String filePath) throws IOException {
        String txtFile = "";
        txtFile = new String(Files.readAllBytes(Paths.get((filePath))));	//Input File read
        txtFile=txtFile.replaceAll(" ","");
        String pureTXT="";
        for (int i=0;i<txtFile.length();i++){
            if(txtFile.charAt(i)=='a' || txtFile.charAt(i)=='t' ||txtFile.charAt(i)=='c' ||txtFile.charAt(i)=='g' ){
                pureTXT=pureTXT+txtFile.charAt(i);
            }
        }

        return pureTXT;
    }

    public HashMap<String, Integer> readTxtFileToHash(String filePathName) throws IOException {
        String filePath =  filePathName;  //"Covids\\WUHANREPETITION.txt"
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        String line;
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        while ((line = reader.readLine()) != null)
        {
            String[] parts = line.split(":", 2);
            if (parts.length >= 2)
            {
                String key = parts[0];
                String value = parts[1];
                map.put(key, Integer.parseInt(value));
            } else {
                System.out.println("ignoring line: " + line);
            }
        }

        /*for (String key : map.keySet())
        {
            System.out.println(key + ":" + map.get(key));
        }*/
        reader.close();

        //System.out.println(map);
        return map;
    }

    public void writeHashToTxtFile( HashMap<String, Integer> hMapNumbers, String filePathName){

        //new file object
        File file = new File(filePathName);

        BufferedWriter bf = null;;

        try{

            //create new BufferedWriter for the output file
            bf = new BufferedWriter( new FileWriter(file) );

            //iterate map entries
            for(Map.Entry<String, Integer> entry : hMapNumbers.entrySet()){

                //put key and value separated by a colon
                bf.write( entry.getKey() + ":" + entry.getValue() );

                //new line
                bf.newLine();
            }

            bf.flush();

        }catch(IOException e){
            e.printStackTrace();
        }finally{

            try{
                //always close the writer
                bf.close();
            }catch(Exception e){}
        }
    }


}





/* System.out.println("Korona alacağımız önlemlerden daha güçlü değildir.");
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
        System.out.println("Wuhan RNA's lenght is "+pureWuhan.length()+"\n"+pureWuhan);*/