package com.example.shingles.datamodel;

import java.util.HashSet;

public class TextData {
    private String instanceString;
    private int instanceShingle;

    /**
     * Collect k-shingles set for a string
     */
    private HashSet<String> wordSet = new HashSet<>();


    /**
     * @param instanceString User text from GUI Text Area
     * @param instanceShingle User defined value for string partition
     */
    protected TextData(String instanceString, int instanceShingle) {
        this.instanceString = instanceString;
        this.instanceShingle = instanceShingle;
    }


    private String getInstanceString() {
        return instanceString;
    }

    private int getInstanceShingle() {
        return instanceShingle;
    }

    /**
     * Takes user String and partitions it according to k-shingles
     * String partitions are then saved in a Hashset
     * @return HashSet containing k-shingle Strings
     */
    protected HashSet<String> splitter(){
        int n = getInstanceShingle();
        getInstanceString().lines().forEach(line->{
            String[] wordArray = line.split(" ");
            int endIndex = wordArray.length;
            int i = 0;


            //get n-shingles from word Array
            while(i+n <= endIndex){
                StringBuilder substring = new StringBuilder();
                //concat words from subarray TODO
                for(int x = i; x < i+n; x++){
                    substring.append(wordArray[x]);
                    substring.append(" ");
                }//end for loop
                i++;
                wordSet.add(new String(substring));
            }

        });
        return wordSet;
    }

}
