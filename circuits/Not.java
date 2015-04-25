package circuits;

public class Not extends Porte {

    protected Composant in;

    public void setIn(Composant comp) {
        in = comp;
    }
    
    public Composant getIn() {
        return in;
    }

    public String description() {
        return super.description() + " in: " + ((in != null) ? in.getId() : "non connecte");
    }

    public boolean getEtat() throws NonConnecteException {
        if (in == null)
            throw new NonConnecteException();
        else
            return !in.getEtat();
    }
}
