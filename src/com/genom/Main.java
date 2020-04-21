package com.genom;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    String ExampleString;
    String covidString = "";
    String NepalString = "";
    String WuhanString = "";
    HashMap<String, Integer> repetitionNumberHash;
    HashMap<String, Integer> mostFrequentHash;
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
        mostFrequentHash = new HashMap<String, Integer>();
        oneCharacterDifferenceHash = new HashMap<String, String>();

    }

    public HashMap<String, Integer> getRepetitionNumberHash() {
        return repetitionNumberHash;
    }

    //Find the number of Repetition in hear
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

    //Take from string from sequence which you want to search
    public void findSearchTextForRepetition(int length){

        for (int i=0; i<getCovidString().length()-length+1; i++){
            repetition(getCovidString().substring(0+i,length+i),length);
        }
    }

    //take reverse complement of string which you take
    public void findSearchTextForReverse(int length){

        for (int i=0; i<getCovidString().length()-length+1; i++){
            reverseCompliment(getCovidString().substring(0+i,length+i),length);
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

    public HashMap<String, Integer> getMostFrequentHash() {
        return mostFrequentHash;
    }

    public void setMostFrequentHash(HashMap<String, Integer> mostFrequentHash) {
        this.mostFrequentHash = mostFrequentHash;
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
        System.out.print("Enter a number: \n " +
                "1= Most Frequent k mers For Wuhan in (Covids\\WUHANREPETITION.txt) file \n" +
                "2= Most Frequent k mers For Nepal in (Covids\\NEPALREPETITION.txt) file\n" +
                "3= Same Value Wuhan and Nepal \n" +
                "4= Reverse Complement For Nepal in (Covids\\NEPALREVERSECOMPLEMENT.txt) file \n" +
                "5= Reverse Complement For Wuhan in  (Covids\\WUHANREVERSECOMPLEMENT.txt) file" );
        String number = in.nextLine();

        switch (number){
            case "1":
                System.out.println("Repetation For Wuhan");
                length = 5; previousSize = 0; main = new Main();
                System.out.println("Wuhan RNA's lenght is "+main.getWuhanString().length()+"\n"+main.getWuhanString());
                main.setCovidString(main.getWuhanString());
                main.findAllRepetationandWrite("Covids\\WUHANREPETITION.txt",1);
                break;

            case "2":
                System.out.println("Repetation For Nepal");
                length = 5; previousSize = 0;  main = new Main();
                System.out.println("Nepal RNA's lenght is "+main.getNepalString().length()+"\n"+main.getNepalString());
                main.setCovidString(main.getNepalString());
                main.findAllRepetationandWrite("Covids\\NEPALREPETITION.txt",1);
                break;
            case "3":
                ArrayList<String> sameRepetition = new ArrayList<>();
                main = new Main();
                main.setNepalHash( main.readTxtFileToHash("Covids\\NEPALREPETITION.txt"));
                main.setWuhanHash( main.readTxtFileToHash("Covids\\WUHANREPETITION.txt"));
                System.out.println("================  Common in two File  ==============");
                for (String keyNepal : main.getNepalHash().keySet())
                    for (String keyWuhan : main.getWuhanHash().keySet())
                        if(keyNepal.compareTo(keyWuhan) == 0)
                            sameRepetition.add(keyNepal);

                for(String key : sameRepetition)
                    System.out.println(key);
                break;

            case "4":
                main = new Main();
                System.out.println("Reverse Complement For Nepal");
                main.setCovidString(main.getNepalString());
                main.findAllRepetationandWrite("Covids\\NEPALREVERSECOMPLEMENT.txt",2);
                break;

            case "5":
                main = new Main();
                System.out.println("Reverse Complement For Wuhan");
                main.setCovidString(main.getWuhanString());
                main.findAllRepetationandWrite("Covids\\WUHANREVERSECOMPLEMENT.txt",2);
                break;

        }



    }

    //find reverse complemet of string
    public void reverseCompliment(String sequence,int length){
        char[] oneChar = sequence.toCharArray();
        char[] reverseChar = sequence.toCharArray();
        int a = 0;
        for ( int i = oneChar.length-1; i >=0  ; i--,a++) { //a-t-g-c
            if (oneChar[i] == 'a')
                reverseChar[a] = 't';
            else if (oneChar[i] == 't')
                reverseChar[a] = 'a';
            else if (oneChar[i] == 'g')
                reverseChar[a] = 'c';
            else if (oneChar[i] == 'c')
                reverseChar[a] = 'g';
        }
        System.out.println("Sequence =  " +sequence);
        System.out.println("Reverse Comp = " + new String(reverseChar));
        repetition(new String(reverseChar),length);


    }


    //Find all repetition as k(5,...) value and write the file
    public void findAllRepetationandWrite(String filePath, int value){
        int length =5; int previousSize =0;
        while(true){
            if (value == 2)  //for reverse Complement
                findSearchTextForReverse(length);
            else            //for others
                findSearchTextForRepetition(length);
            if ( getRepetationSize() > 0){
                System.out.println("Lengt of Searching Key => "+length);
                System.out.println("Number Of Repetation => "+ (getRepetationSize()-previousSize) );
                previousSize = getRepetationSize();
                mostFrequent(getRepetitionNumberHash(),filePath);
                repetitionNumberHash.clear();
                length++;
            }
            else
                break;
        }
        //orderHash(filePath);
        // writeHashToTxtFile(getRepetitionNumberHash(),filePath);   //"Covids\\WUHANREPETITION.txt"

    }
    //find the most frequent
    public void mostFrequent(HashMap<String, Integer> hashMap, String filePath){
        int a = 0;
        for (String key : hashMap.keySet())
        {
            if ( hashMap.get(key) > a  )
                a = hashMap.get(key);
            //System.out.println(key + ":" + hashMap.get(key) );
        }
        for (String key : hashMap.keySet()){
            if(hashMap.get(key) == a)
                mostFrequentHash.put(key , a);
        }

       /* for (String key : mostFrequentHash.keySet())
        {
            System.out.println(key + ":" + mostFrequentHash.get(key) );
        }*/
        orderHash(filePath);
    }
    //Order the string as length which we found repetition
    public void orderHash(String filePath){
        Map<String, Integer> treeMap = new TreeMap<String, Integer>(
                new Comparator<String>() {
                    @Override
                    public int compare(String s1, String s2) {
                        if (s1.length() < s2.length()) {
                            return -1;
                        } else if (s1.length() > s2.length()) {
                            return 1;
                        } else {
                            return s1.compareTo(s2);
                        }
                    }
                });

        treeMap.putAll(getMostFrequentHash());

        for (Map.Entry<String, Integer> entry : treeMap.entrySet())
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());

        writeHashToTxtFile(treeMap,filePath);   //"Covids\\WUHANREPETITION.txt"
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

    public void writeHashToTxtFile( Map<String, Integer> hMapNumbers, String filePathName){

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
