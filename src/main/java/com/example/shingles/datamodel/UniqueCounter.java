package com.example.shingles.datamodel;

import java.util.*;
import java.util.Map;

public class UniqueCounter {

    private HashMap<String, Integer> wordFreq = new HashMap<>();
    List<Map.Entry<String, Integer>> sortedMap;


    private String largeString;

    /**
     * @param largeString User String input from GUI
     */
    public UniqueCounter(String largeString) {
        this.largeString = largeString;
    }

    /**
     * Take String array and count word frequency. Saved in HashMap
     */
    public void runCounter(){
        counter(StopWordFilter.filter(largeString));
    }

    /**
     * @param uniqueList Counts frequency of unique words into a hash map.
     *
     * Checks for case and comma
     */
    private void counter( String[] uniqueList){
        for(String wordS:uniqueList){
            String word = wordS.toLowerCase().strip();
            int last = word.length() -1;

            if(word.charAt(last)==','|| word.charAt(last)==')'){
                word = word.substring(0,last);
            }
            if(word.charAt(0)=='('){
                word = word.substring(1);
            }
            if(wordFreq.containsKey(word)){
                Integer key = wordFreq.get(word);
                int newKey = key + 1;
                wordFreq.put(word, newKey);
            }else{
                wordFreq.put(word, 1);
            }
        }
    }

    /**
     * Sort HashMap according to value
     * @param ascending Sorting in ascending order.
     * Default is descending order
     */
    private void rank(boolean ascending){
        sortedMap = new ArrayList<>(wordFreq.entrySet());
        if(ascending){
            sortedMap.sort(Map.Entry.comparingByValue());
        }
        sortedMap.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
    }

    /**
     * @return sum of values for all unique words
     */
    private double sumValues(){
        return wordFreq.values().stream().mapToDouble(w-> w.intValue()).sum();
    }

    /**
     * Runs methods to execute UniqueCounter
     * @param ascending Takes parameter for rank method
     * @return String with Word-Frequency
     */
    public String display(boolean ascending){
        runCounter();
        rank(ascending);
        StringBuilder output = new StringBuilder();
        for(int i = 0; i<10;i++){
            String word = sortedMap.get(i).getKey();
            int wordFreq = sortedMap.get(i).getValue();
            int wordRank = i+1;
            String wordOut = String.format("%d.  %s : %d\n",wordRank, word, wordFreq);
            output.append(wordOut);
        }

        return new String(output);
    }









}
