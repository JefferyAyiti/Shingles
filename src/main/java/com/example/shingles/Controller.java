package com.example.shingles;

import com.example.shingles.datamodel.Shingles;
import com.example.shingles.datamodel.UniqueCounter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class Controller {

    @FXML
    private TextArea firstText;
    @FXML
    private TextArea secondText;

    @FXML
    private TextField firstTitle;

    @FXML
    private TextField secondTitle;
    @FXML
    private Label lineCount;

    @FXML
    private Label wordCount;

    @FXML
    private Spinner<Integer> spinner;

    @FXML
    private TextArea fstCounterDisplay;
    @FXML
    private TextArea sndCounterDisplay;

   @FXML
   private Label lineCountTwo;
   @FXML
   private Label wordCountTwo;

   @FXML
   private Label showJaccard;

    private String fstAreaString;
    private String sndAreaString;

    @FXML
    private Label titleOne_display;
    @FXML
    private Label titleTwo_display;

    /**
     *Provide live feedback to the user on the number of
     * line and words in both text areas
     */
    @FXML
    public void initialize(){
        liveCount(firstText, lineCount, wordCount);
        liveCount(secondText, lineCountTwo, wordCountTwo);
    }

    /**
     * @param area Text area for String input for processing
     * @param areaLineCount FXML Object for displaying the number of lines
     * @param areaWordCount FXML Object for displaying the number of words
     */
    private void liveCount(TextArea area, Label areaLineCount, Label areaWordCount) {
        area.setOnMouseExited(event -> {
            String areaString = area.getText();
            long nLines = areaString.lines().count();
            int nWords = areaString.strip().split("\\s").length;
            areaLineCount.setText(" " + nLines);
            areaWordCount.setText(" " + nWords);
        });
    }

    /**
     * Executes first "Clear" button in GUI.
     * Removes content in TextArea
     */
    @FXML
    public void clearFirstBox(){
        firstTitle.clear();
        firstText.clear();
        fstCounterDisplay.clear();
    }

    /**
     * Executes second "Clear" button in GUI.
     * Removes content in TextArea
     */
    @FXML
    public void clearSecondBox(){
        secondTitle.clear();
        secondText.clear();
        sndCounterDisplay.clear();
    }

    /**
     * @return The Jaccard Co-efficient of the two Strings is calculated
     */
    public double evaluate(){
        fstAreaString = firstText.getText().strip();
        sndAreaString = secondText.getText().strip();
        int n_shingle = spinner.getValueFactory().getValue();
        Shingles compareText = new Shingles(fstAreaString,sndAreaString, n_shingle);
        return compareText.jaccard();
    }

    /**
     * @return String display for first text word frequency ranking
     */
    private String countFstText(){
        UniqueCounter countFstText = new UniqueCounter(fstAreaString);
        return countFstText.display(false);
    }

    /**
     * @return String display for second text word frequency ranking
     */
    private String countSndText(){
        UniqueCounter countSndText = new UniqueCounter(sndAreaString);
        return countSndText.display(false);
    }


    /**
     * Displays results to the GUI after the user clicks on the start button
     * Results:
     * Jaccard Co-efficient,
     * Word Frequency Ranking for first Text
     * Word Frequency Ranking for second Text
     */
    @FXML
    public void startResult(){
        double result = evaluate() * 100;
        String resultString = String.format("%.3f",result);
        showJaccard.setText(resultString + "%");
        fstCounterDisplay.setText(countFstText());
        sndCounterDisplay.setText(countSndText());

        String titleOne = firstTitle.getText();
        //Displaying Title Names in results;
        if( titleOne.isEmpty()){
            titleOne_display.setText("First Title: Word Frequency");
        }else{
            titleOne_display.setText(firstTitle.getText());
        }

        String titleTwo = secondTitle.getText();

        if( titleTwo.isEmpty()){
            titleTwo_display.setText("Second Title: Word Frequency");
        }else{
            titleTwo_display.setText(secondTitle.getText());
        }


    }



}