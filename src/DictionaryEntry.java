import java.util.ArrayList;

public class DictionaryEntry {
    private String word;
    private String base;
    private String def;
    private ArrayList<String> forms;
    private ArrayList<Use> uses;
    private ArrayList<Word> words;
    private boolean good;

    public DictionaryEntry(String word, String base, String def, String forms, ArrayList<Word> words, int ind, boolean good){
        this.base = base;
        this.word = word;
        this.def = def;
        this.forms = new ArrayList<>();
        setForms(forms);
        uses = new ArrayList<>();
        this.words = words;
        this.good = good;

        for (String str : this.forms) {
            String speech = str.substring(0, str.indexOf(" "));
            if(!good)
                uses.add(new Other());
            else if(speech.equals("noun") || speech.equals("pron")){
                if(str.contains("supine"))
                    uses.add(new Supine(str));
                else if(str.contains("nom"))
                    uses.add(new Nominative(word));
                else if(str.contains("gen"))
                    uses.add(new Genitive(word));
                else if(str.contains("dat"))
                    uses.add(new Dative(base));
                else if(str.contains("acc"))
                    uses.add(new Accusative(base));
                else if(str.contains("abl"))
                     uses.add(new Ablative(word));
                 else if(str.contains("voc"))
                     uses.add(new Vocative());

            }
            else if(speech.equals("adj")) {
                String fm = str.substring(str.indexOf(" ") + 1);
                uses.add(new Adjective(word, fm, ind));
            }
            else if(speech.equals("verb")) {
                if (str.contains("ind"))
                    uses.add(new Indicative());
                else if (str.contains("gerundive"))
                    uses.add(new Gerundive(word));
                else if (str.contains("subj"))
                    uses.add(new Subjunctive(word, str.substring(str.indexOf(" ") + 1)));
                else if (str.contains("imperat"))
                    uses.add(new Imperative());
                else if (str.contains("inf"))
                    uses.add(new Infinitive());
            }
            else if(str.contains("numeral"))
                    uses.add(new Numeral());
            else if(speech.equals("part")) {
                String fm = str.substring(str.indexOf(" ") + 1, str.indexOf(" ") + 3) + str.substring(str.indexOf(" ", str.length() - 9));
                uses.add(new Participle(word, fm, ind));
            }
            else{
                uses.add(new Other());
            }

        }


    }

    public DictionaryEntry(String keyword) {
        base = keyword;
        word = "";
        def = "no information";
        this.forms = new ArrayList<>();
        uses = new ArrayList<>();
    }

    private void setForms(String str){
        int j = str.length();
        for (int i = str.length() - word.length(); i >= 0 ; i--) {
            if(str.substring(i, i + word.length()).equals(word)){
                forms.add(str.substring(i + word.length() + 1, j));
                j = i - 1;
            }
        }
        for (int i = 0; i < forms.size(); i++) {
            String fm = forms.get(i);
            if (fm.contains("poetic"))
                forms.remove(fm);
        }
    }
    public void filter1() {
        for (Use use : uses)
            use.filter1(words);
        for (int i = 0; i < uses.size(); i++) {
            Use use = uses.get(i);
            use.removeDuplicates(use.getUses());
            if (use.getUses().isEmpty()) {
                uses.remove(use);
                forms.remove(i);
                i--;
            }

        }
    }
    public void filter2(){
        for(Use use : uses)
            use.filter2(words);
        for (int i = 0; i < forms.size(); i++) {
            Use use = uses.get(i);
            use.removeDuplicates(use.getUses());
            if (use.getUses().isEmpty()) {
                uses.remove(use);
                forms.remove(i);
                i--;
            }

        }
    }
    public ArrayList<Use> getUses(){
        return uses;
    }

    @Override
    public String toString() {
        String rtn = "";
        for (int i = 0; i < uses.size(); i++)
            rtn += (forms.get(i) + " " + uses.get(i).toString() + "\n");

        return base + ": "+ def + '\n' + rtn;
    }

    public ArrayList<String> getForms() {
        return forms;
    }

    public String getBase() {
        return base;
    }
    public String getWord(){
        return word;
    }
}
