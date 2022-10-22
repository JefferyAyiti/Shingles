package com.example.shingles.datamodel;

import java.util.HashSet;
import java.util.Set;

public class Shingles {
    private String fstAreaString;
    private String sndAreaString;

    private int shingle;
    private HashSet<String> fstStringSet;
    private HashSet<String> sndStringSet;

    private HashSet<String> intersectionSet;

    /**
     * @param fstAreaString First text String
     * @param sndAreaString Second text String
     * @param shingle k-shingle value
     * Constructor for initializing Shingle Algorithm
     */
    public Shingles(String fstAreaString, String sndAreaString, int shingle) {
        this.fstAreaString = fstAreaString;
        this.sndAreaString = sndAreaString;
        this.shingle = shingle;
    }

    /**
     * Creates to TextData instances and collects partitions for each set
     */
    private void runShingle(){
        TextData fst = new TextData(fstAreaString, shingle);
        TextData snd = new TextData(sndAreaString, shingle);

        fstStringSet = fst.splitter();
        sndStringSet = snd.splitter();

    }

    /**
     * Intersection of Strings in the two shingle sets
     * Result Saved in new set
     */
    private void setHashIntersection(){
        intersectionSet = new HashSet<>(Set.copyOf(fstStringSet));
        intersectionSet.retainAll(sndStringSet);
    }

    /**
     * @return Value of jaccard coefficient
     * Size of intersection of two sets divided by the size union of thw two sets
     */
    public double jaccard(){
        this.runShingle();
        this.setHashIntersection();
        int textSet1 = getfstSetSize();
        int textSet2 = getsndSetsSize();
        int intSet = getIntSetSize();
        double unionSize = (textSet1 + textSet2) - intSet;
        return intSet/unionSize;
    }

    private int getfstSetSize(){
        return fstStringSet.size();
    }

    private int getsndSetsSize(){
        return sndStringSet.size();
    }

    private int getIntSetSize(){
        return intersectionSet.size();
    }
}
