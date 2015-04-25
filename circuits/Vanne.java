package circuits;

public class Vanne extends Composant {

    protected Composant in1;
    protected String name;
    
    public Vanne(String s) {
        name = s;
    }

    public void setIn(Composant comp) {
        in1 = comp;
    }

    public String description() {
      return super.description() + " in1: " + ((in1 != null) ? in1.getId() : "non connecte");
    }

    public boolean getEtat() throws NonConnecteException {
        if (in1 == null)
            throw new NonConnecteException();
        else
            return in1.getEtat();
    }
}
