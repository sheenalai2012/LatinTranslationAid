import java.util.ArrayList;

public class Other extends Use {
    private ArrayList<String> uses;
    public Other(){
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
