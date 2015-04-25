package circuits;

abstract class Porte2Entrees extends Porte {

    protected Composant in1, in2;
    
    abstract boolean eval() throws NonConnecteException;

    public void setIn1(Composant comp) {
        in1 = comp;
    }

    public void setIn2(Composant comp) {
        in2 = comp;
    }
    
    public Composant getIn1() {
        return in1;
    }
    
    public Composant getIn2() {
        return in2;
    }

    public String description() {
        return super.description() + " in1: " + ((in1 != null) ? in1.getId() : "non connecte") + " in2: " + ((in2 != null) ? in2.getId() : "non connecte");
    }
    
    public boolean getEtat() throws NonConnecteException {
        if (in1 == null || in2 == null)
            throw new NonConnecteException();
        else
            return eval();
    }
}
