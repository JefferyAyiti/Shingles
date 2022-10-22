package com.example.shingles.datamodel;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StopWordFilter {

    /**
     * Stop words provide no new information. Are present for Grammatical cohesion and don't necessarily communicate subject information
     * Case independent
     */
    private static final String stopWords = "\\b(?i)a\\b|\\b(?i)about\\b|\\b(?i)above\\b|\\b(?i)after\\b|\\b(?i)again\\b|\\b(?i)against\\b|\\b(?i)all\\b|\\b(?i)am\\b|\\b(?i)an" +
            "\\b|\\b(?i)and\\b|\\b(?i)any\\b|\\b(?i)are\\b|\\b(?i)aren't\\b|\\b(?i)as\\b|\\b(?i)at\\b|\\b(?i)be\\b|\\b(?i)because\\b|\\b(?i)been\\b|\\b(?i)before\\b|\\b(?i)being\\b|\\b(?i)below\\b|\\b(?i)between" +
            "\\b|\\b(?i)both\\b|\\b(?i)but\\b|\\b(?i)by\\b|\\b(?i)can't\\b|\\b(?i)cannot\\b|\\b(?i)could\\b|\\b(?i)couldn't\\b|\\b(?i)did\\b|\\b(?i)didn't\\b|\\b(?i)do\\b|\\b(?i)does\\b|\\b(?i)doesn't\\b|\\b(?i)doing\\b|\\b(?i)don't" +
            "\\b|\\b(?i)down\\b|\\b(?i)during\\b|\\b(?i)each\\b|\\b(?i)few\\b|\\b(?i)for\\b|\\b(?i)from\\b|\\b(?i)further\\b|\\b(?i)had\\b|\\b(?i)hadn't\\b|\\b(?i)has\\b|\\b(?i)hasn't\\b|\\b(?i)have\\b|\\b(?i)haven't" +
            "\\b|\\b(?i)having\\b|\\b(?i)he\\b|\\b(?i)he'd\\b|\\b(?i)he'll\\b|\\b(?i)he's\\b|\\b(?i)her\\b|\\b(?i)here\\b|\\b(?i)here's\\b|\\b(?i)hers\\b|\\b(?i)herself\\b|\\b(?i)him\\b|\\b(?i)himself\\b|\\b(?i)his" +
            "\\b|\\b(?i)how\\b|\\b(?i)how's\\b|\\b(?i)i\\b|\\b(?i)i'd\\b|\\b(?i)i'll\\b|\\b(?i)i'm\\b|\\b(?i)I'm\\b|\\b(?i)i've\\b|\\b(?i)if\\b|\\b(?i)in\\b|\\b(?i)into\\b|\\b(?i)is\\b|\\b(?i)isn't\\b|\\b(?i)it\\b|\\b(?i)it's\\b|\\b(?i)its\\b|\\b(?i)itself\\b|\\b(?i)let's" +
            "\\b|\\b(?i)me\\b|\\b(?i)more\\b|\\b(?i)most\\b|\\b(?i)mustn't\\b|\\b(?i)my\\b|\\b(?i)myself\\b|\\b(?i)no\\b|\\b(?i)nor\\b|\\b(?i)not\\b|\\b(?i)of\\b|\\b(?i)off\\b|\\b(?i)on\\b|\\b(?i)once\\b|\\b(?i)only\\b|\\b(?i)or\\b|\\b(?i)other" +
            "\\b|\\b(?i)ought\\b|\\b(?i)our\\b|\\b(?i)ours\\b|\\b(?i)ourselves\\b|\\b(?i)out\\b|\\b(?i)over\\b|\\b(?i)own\\b|\\b(?i)same\\b|\\b(?i)shall\\b|\\b(?i)shan't\\b|\\b(?i)she\\b|\\b(?i)she'd\\b|\\b(?i)she'll\\b|\\b(?i)she's\\b|\\b(?i)should" +
            "\\b|\\b(?i)shouldn't\\b|\\b(?i)so\\b|\\b(?i)some\\b|\b(?i)such\\b|\\b(?i)than\\b|\\b(?i)(?i)that\\b|\\b(?i)that's\\b|\\b(?i)the\\b|\\b(?i)their\\b|\\b(?i)theirs\\b|\\b(?i)them\\b|\\b(?i)themselves\\b|\\b(?i)then" +
            "\\b|\\b(?i)there\\b|\\b(?i)there's\\b|\\b(?i)these\\b|\\b(?i)they\\b|\\b(?i)they'd\\b|\\b(?i)they'll\\b|\\b(?i)they're\\b|\\b(?i)they've\\b|\\b(?i)this\\b|\\b(?i)those\\b|\\b(?i)through\\b|\\b(?i)to" +
            "\\b|\\b(?i)too\\b|\\b(?i)under\\b|\\b(?i)until\\b|\\b(?i)up\\b|\\b(?i)very\\b|\\b(?i)was\\b|\\b(?i)wasn't\\b|\\b(?i)we\\b|\\b(?i)we'd\\b|\\b(?i)we'll\\b|\\b(?i)we're\\b|\\b(?i)we've\\b|\\b(?i)were\\b|\\b(?i)weren't\\b|\\b(?i)what" +
            "\\b|\\b(?i)what's\\b|\\b(?i)when\\b|\\b(?i)when's\\b|\\b(?i)where\\b|\\b(?i)where's\\b|\\b(?i)which\\b|\\b(?i)while\\b|\\b(?i)who\\b|\\b(?i)who's\\b|\\b(?i)whom\\b|\\b(?i)why\\b|\\b(?i)why's\\b|\\b(?i)with" +
            "\\b|\\b(?i)won't\\b|\\b(?i)would\\b|\\b(?i)wouldn't\\b|\\b(?i)you\\b|\\b(?i)you'd\\b|\\b(?i)you'll\\b|\\b(?i)you're\\b|\\b(?i)you've\\b|\\b(?i)your\\b|\\b(?i)yours\\b|\\b(?i)yourself\\b|\\b(?i)yourselves";

    /**
     * Predicates for filter.
     * Filter out empty elements and single word elements.
     */
    private static Predicate<String> nonEmpty = w -> !w.isEmpty();
    private static Predicate<String> validWord = nonEmpty.and(w -> w.length() > 1);


    /**
     * @param largeString User String to be filtered
     * @return String array containing words that can give context text subject;
     */
    protected static String[] filter(String largeString){
        String filtered = largeString.replaceAll(stopWords," ");

        return Arrays.stream(filtered.split("(\\s)+"))
                .filter(element -> validWord.test(element) )
                .collect(Collectors.toList())
                .toArray(String[]::new);
    }

    /**
     * @param stopWords Array of strings that should be filtered out.
     * @return regex formatted stopwords
     */
    protected static String generator(String[] stopWords){
        StringBuilder generator = new StringBuilder("\\b(?i)a\\b");
        for(String stopWord: stopWords){
            generator.append("|\\b(?i)" + stopWord + "\\b");
        }
        return new String(generator);
    }
    
}
