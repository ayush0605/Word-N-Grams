/**
 * WordGram
 * 
 * @author (Ayush Singhal)
 * @version (1.0)
 */

public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        for(int k=0; k<myWords.length ; k++){
            ret+=myWords[k] + " ";
        }
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if(this.length() != other.length()){
            return false;
        }
        for(int i=0; i<myWords.length; i++){
            if(!myWords[i].equals(other.wordAt(i))){
                return false;
            }
        }
        return true;

    }

    public WordGram shiftAdd(String word) { 
        String[] myWordCopy = new String[myWords.length];
        for(int i=0; i<myWords.length-1 ;i++){
            myWordCopy[i] = this.wordAt(i+1);
        }
        myWordCopy[myWords.length-1] = word;
        WordGram out = new WordGram(myWordCopy, 0, myWords.length);
        return out;
    }

}