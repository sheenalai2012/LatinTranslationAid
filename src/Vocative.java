import java.util.ArrayList;

public class Vocative extends Use{
    private ArrayList<String> uses;
    public Vocative(){
        uses = new ArrayList<>();
        uses.add("direct address");
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
        return "(" + uses.toString().substring(1, uses.toString().length() - 1) + ")";
    }
}
