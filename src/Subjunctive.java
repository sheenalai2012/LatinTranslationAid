import java.util.ArrayList;

public class Subjunctive extends Use {
    private ArrayList<String> uses;
    private String word;
    private String form;
    private boolean un; // has ut or ne
    private boolean subres;
    String[] comparative = {"tamquam", "quasi", "velut", "ceu"}; //ac si???  ut si??? quam si??
    String[] proviso = {"dum", "modo", "dummodo"};  //tantum ut??
    String[] relativepur = {"qui"};
    String[] conditional = {"si", "nisi"};
    String[] indques = {"quis", "cur", "ubi"};
    String[] cum = {"cum"};
    String[] fear = {"timeo", "metuo", "extimesco", "pertimesco", "sollicitus"};
    String[] utne = {"ut", "ne"};
    String[] opt = {"utinam"};
    String[] antic = {"dum", "donec", "antequam", "priusquam"};
    String[] adjres = {"talis", "tantus"};
    String[] advres = {"tot", "eo", "adeo", "ita", "sic", "tam", "totiens"};
    String[] indcom = {"impero", "mando", "persuadeo", "moneo", "oro", "rogo", "peto", "postulo", "quaero"};

    public Subjunctive(String word, String form){
        this.word = word;
        this.form = form;
        uses = new ArrayList<>();
        uses.add("potential");

    }
    @Override
    public void filter1(ArrayList<Word> words) {
        for(Word wd : words){
            if(isIn(wd, comparative))
                uses.add("clause of comparison");
            else if(isIn(wd, proviso))
                uses.add("clause of proviso");
            else if(isIn(wd, relativepur))
                uses.add("relative purpose clause");
            else if(isIn(wd, conditional))
                uses.add("conditional");
            else if (isIn(wd, indques))
                uses.add("indirect question");
            else if(isIn(wd, cum))
                uses.add("cum clause");
            else if(wd.getKeyword().equals("ut")){
                un = true;
                subres = true;
            }
            else if(isIn(wd, utne)) {
                un = true;
                uses.add("purpose clause");
            }
            else if(isIn(wd, opt))
                uses.add("optative clause");
            else if(isIn(wd, antic))
                uses.add("anticipatory clause");
            else if(form.contains("pres") && (form.contains("2nd") || form.contains("3rd")))
                uses.add("jussive clause");
            else if(form.contains("pres") && (form.contains("1st")))
                uses.add("hortatory clause");
            else if(wd.getKeyword().contains("?"))
                uses.add("deliberative");


        }
    }
    public void filter2(ArrayList<Word> words){
        for(Word wd : words) {
            if (un) {
                if (isIn(wd, adjres) || isIn2(wd, advres))
                    uses.add("adverbial result clause");
                else if (isIn(wd, fear))
                    uses.add("clause of fearing");
                else if (isIn(wd, indcom))
                    uses.add("indirect command");
            }
            if (subres) {
                String[] facio = {"facio", "accido"};
                if (contains1(wd, "ficio") || isIn(wd, facio) || wd.getKeyword().equals("fore"))
                    uses.add("substantive result clause");

            }
        }
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
