import java.util.ArrayList;

public class Adjective extends Use{
        private ArrayList<String> uses;
        private String word;
        private String form;
        private int ind;
        public Adjective(String word, String form, int ind){
                this.word = word;
                if(form.contains("comp"))
                        this.form = form.substring(0, form.length() - 5);
                else if(form.contains("superl"))
                        this.form = form.substring(0, form.length() - 7);
                else
                        this.form = form;
                this.ind = ind;
                uses = new ArrayList<>();


        }
        public void filter1(ArrayList<Word> words) {
                for (int i = 0; i < words.size(); i++)
                        if(ind != i) {
                                Word wd = words.get(i);
                                for (DictionaryEntry entry : wd.getEntries())
                                        for (String fm : entry.getForms())
                                                if (fm.substring(0, fm.indexOf(" ")).equals("noun") && fm.substring(fm.indexOf(" ") + 1).equals(form))
                                                        if (!uses.contains(entry.getWord()))
                                                                uses.add(entry.getWord());
                        }
        }
        public void filter2(ArrayList<Word> words){
                for (int i = 0; i < words.size(); i++)
                        if(ind != i) {
                                Word wd = words.get(i);
                                for (DictionaryEntry entry : wd.getEntries())
                                        for (String fm : entry.getForms())
                                                if ((fm.substring(0, fm.indexOf(" ")).equals("noun") || fm.substring(0, fm.indexOf(" ")).equals("pron")) && fm.substring(fm.indexOf(" ") + 1).contains(form))
                                                        if (!uses.contains(entry.getWord()))
                                                                uses.add(entry.getWord());
                        }
        }
        public ArrayList<String> getUses(){
                return uses;
        }

        public String toString(){
                if(uses.isEmpty())
                        return "";
                else
                        return "(" + uses.toString().substring(1, uses.toString().length() - 1) + ")";
        }

}
