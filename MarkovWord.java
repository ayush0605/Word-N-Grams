
/**
 * MarkovWord
 * 
 * @author (Ayush Singhal) 
 * @version (1.0)
 */
import java.util.*;
public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int order) {
        myRandom = new Random();
        this.myOrder = order;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
    myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram key = new WordGram(myText,index, myOrder);
        sb.append(key);
        sb.append(" ");
        System.out.println(key);
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            System.out.println(follows);
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            System.out.println(next);
            key = key.shiftAdd(next);
        }
        return sb.toString().trim();
    }
    
    private int indexOf(String[] words, WordGram target, int start){
        for(int k=start; k<words.length - myOrder; k++){
           WordGram curr = new WordGram(words, k , myOrder);
           if(curr.equals(target)){
             return k;  
           }
        }
        return -1;
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while(true){
            int start = indexOf(myText,kGram,pos);
            if(start==-1){
                break;
            }
            if(start == (myText.length - myOrder)){
                break;
            }
            follows.add(myText[start+myOrder]);
            pos = start+1;
        }
        return follows;
    }

}
