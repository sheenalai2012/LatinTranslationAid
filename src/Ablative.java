import java.util.ArrayList;

public class Ablative extends Use{
    private ArrayList<String> uses;
    private String word;
    private boolean agent;
    private boolean comp;

    String[] preposition = {"sine", "pro", "prae", "sub", "in", "super"};
    String[] fromwhich = {"ab", "ex", "de"};
    String[] puffv = {"fruor", "fungor", "utor", "potior", "vescor"};
    String[] ag = {"ab"};
    String[] accomp = {"cum"};
    String[] origin = {"orior", "nascor"};
    public Ablative(String word){
        this.word = word;
        uses = new ArrayList<>();
    }

    @Override
    public void filter1(ArrayList<Word> words) {
        for(Word wd : words)
            if(isIn(wd, fromwhich)){
                uses.add("object of preposition");
                uses.add("place from which");
            }
            else if(isIn(wd, accomp))
                uses.add("accompaniment");
            else if(isIn(wd, preposition))
                uses.add("object of preposition");
            else if((isIn3(wd, "Verb") && contains2(wd, "pass")))
                agent = true;
            else if(isIn(wd, puffv))
                uses.add("special verb");
            else if(isIn3(wd, "Adjective") && contains2(wd, "comp"))
                uses.add("degree of difference");
            else if(wd.getKeyword().equals("quam"))
                comp = false;
            else if(wd.getKeyword().equals("opus") || wd.getKeyword().equals("usus"))
                uses.add("with opus or usus to show need of something");
            else if(isIn3(wd, "Verb") && prefix(wd, fromwhich))
                uses.add("separation");
            else if(isIn(wd, origin))
                uses.add("origin/source");
    }
    public void filter2(ArrayList<Word> words){
        for(Word wd : words)
            if(agent && isIn(wd, ag))
                uses.add("agent");
            else if(comp && isIn3(wd, "Adjective") && contains2(wd, "comp"))
                uses.add("comparison");
            else if(isIn3(wd, "Adjective") && !hasUse(wd, word))
                uses.add("respect");
            //else if(isIn3(wd, "Participle") ) // ablative absolute
            else if((isIn3(wd, "Adjective") || isIn3(wd, "Participle")) && hasUse(wd, word))
                uses.add("description");
            else if(isIn3(wd, "Participle") && hasUse(wd, word))
                uses.add("ablative absolute");

        uses.add("means");
        uses.add("material");
        uses.add("manner");
        uses.add("place where");
        uses.add("time when");
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
