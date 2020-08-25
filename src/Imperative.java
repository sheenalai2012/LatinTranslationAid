import java.util.ArrayList;

public class Imperative extends Use {
    private ArrayList<String> uses;

    public Imperative(){
        uses = new ArrayList<>();
        uses.add("command");

    }
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
        return "(" + uses.toString().substring(1, uses.toString().length() - 1) + ")";
    }
}
