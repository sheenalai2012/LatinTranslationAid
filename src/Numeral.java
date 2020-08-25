import java.util.ArrayList;

public class Numeral extends Use {
    private ArrayList<String> uses;
    public Numeral(){
        uses = new ArrayList<>();
        uses.add("");
    }
    @Override
    public void filter1(ArrayList<Word> words) {

    }
    public void filter2(ArrayList<Word> words){

    }

    @Override
    public ArrayList<String> getUses() {
        return uses;
    }

    @Override
    public String toString() {
        return "";
    }
}
