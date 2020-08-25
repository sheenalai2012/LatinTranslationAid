import java.io.IOException;
import java.util.ArrayList;

public class InputDivider {    //divides input into sentences
    private ArrayList<SentenceDivider> sentences;
    public InputDivider(String string) throws IOException {
        sentences = new ArrayList<>();
        divide(string);

    }
    private void divide (String sentence) throws IOException { //divides sentence into chunks
        int j = 0;
        sentence += ".";
        for (int i = 0; i < sentence.length(); i++) {
            if(sentence.charAt(i) >= '!' && sentence.charAt(i) <= '?' && sentence.charAt(i) != ','){
                sentences.add(new SentenceDivider (sentence.substring(j, i)));
                for (int k = i; k < sentence.length(); k++) {
                    char c = sentence.charAt(k);
                    if((c > '?' || c == ',')) {
                        sentences.add(new SentenceDivider (sentence.substring(i, k)));
                        i = k;
                        break;
                    }

                }
                j = i;
            }
        }
    }
    public void filter1() throws IOException {
        for(SentenceDivider sent : sentences)
            sent.filter1();
    }
    public String toString(){
        String rtn = "";
        for(SentenceDivider sent : sentences)
            rtn += sent.toString();
        return rtn;
    }
}
