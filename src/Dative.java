import java.util.ArrayList;

public class Dative extends Use{
    private ArrayList<String> uses;
    String[] special = {"adversor", "cedo", "credo", "faveo", "fido", "ignosco", "impero", "indulgeo", "invideo", "irascor", "minitor", "noceo", "parco", "pareo", "placeo", "resisto", "servio", "moderor", "studeo", "tempero", "suadeo", "persuadeo", "suscēenseo", "tempero", "obtempero", "auxilior", "medeor", "opitulor", "subvenio", "obsequor", "oboedio", "obsto", "repugno", "displiceo", "impero", "praecipio", "diffido", "succenseo"};
    String[] possession = {"sum"};
    String[] impersonal = {"accido", "contingo", "expedio", "libet", "licet", "placeo"};
    String[] compound = {"ad", "ante", "con", "in", "ob", "post", "prae", "pro", "sub", "super"};
    String[] separation = {"ab", "de", "ex"};
    String[] fitness = {"aptus", "similis", "proximus", "amicus"};
    String[] purpose = {"clades", "cura", "auxilium", "impedimentum", "praemium", "praesidium", "subsidium", "usus", "castra", "receptus"};


    public Dative(String base){
        uses = new ArrayList<>();
        for(String str : purpose)
            if(base.equals(str))
                uses.add("purpose");
    }

    @Override
    public void filter1(ArrayList<Word> words) {

        for(Word wd : words){
            if(isIn(wd, special))
                uses.add("with special (intransitive) verb");
            else if(isIn(wd, possession))
                uses.add("possession");
            else if(isIn(wd, impersonal) && contains2(wd, "3rd sg"))
                uses.add("with impersonal verb");
            else if(isIn3(wd, "Gerundive"))
                uses.add("agent");
            else if(isIn3(wd, "Verb") && prefix(wd, compound))
                uses.add("with a compound verb");
            else if(isIn3(wd, "Verb") && prefix(wd, separation))
                uses.add("separation");
            else if(isIn(wd, fitness))
                uses.add("with adjective");


        }
    }
    public void filter2(ArrayList<Word> words){
        uses.add("indirect object");
        uses.add("reference");
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
