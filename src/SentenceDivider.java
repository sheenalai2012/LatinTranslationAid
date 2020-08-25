import java.io.IOException;
import java.util.ArrayList;

public class SentenceDivider {   //divides each sentence into chunks
    private ArrayList<String> chunks;
    private ArrayList<Chunk> chunks2;
    public SentenceDivider(String sentence) throws IOException {
        chunks = new ArrayList<>();
        chunks2 = new ArrayList<>();
        divide(sentence);
        filter1();
    }
    private void divide (String sentence) throws IOException { //divides sentence into chunks
        int j = 0;
        int i2 = 0;
        while(i2 < sentence.length() && sentence.charAt(i2) >= '!' && sentence.charAt(i2) <= '?'){
            i2++;
        }
        if(i2 != 0){
            chunks.add(sentence.substring(0, i2));
            chunks2.add(new Chunk(sentence.substring(0, i2)));
        }
        if(i2 < sentence.length()) {
            sentence += ".";
            for (int i = i2; i < sentence.length(); i++) {
                if (sentence.charAt(i) >= '!' && sentence.charAt(i) <= '?') {
                    chunks.add(sentence.substring(j, i));
                    chunks2.add(new Chunk(sentence.substring(j, i)));
                    for (int k = i; k < sentence.length(); k++) {
                        char c = sentence.charAt(k);
                        if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                            chunks.add(sentence.substring(i, k));
                            chunks2.add(new Chunk(sentence.substring(i, k)));
                            i = k;
                            break;
                        }

                    }
                    j = i;
                }
            }
        }
    }
    public void filter1() throws IOException {
        for(Chunk chunk : chunks2){
            chunk.filter1();
            chunk.filter2();
        }
    }
    public String toString(){
        String rtn = "";
        for(Chunk ck : chunks2)
            rtn += ck.toString();
        return rtn;
    }
}
