package ua.nure.borisenko.practice6.part1;

public class Word {
    private String variableWord;
    private int frequent;

    public Word(String word) {
        this.variableWord = word;
        this.frequent = 1;
    }

    public void riseFrequent() {
        frequent++;
    }

    public String getWord() {
        return variableWord;
    }

    public int getFreq() {
        return frequent;
    }


}