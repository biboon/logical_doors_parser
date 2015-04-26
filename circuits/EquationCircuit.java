package circuits;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class EquationCircuit extends Circuit {

    public Map<String, Interrupteur> interruptNames = new LinkedHashMap<String, Interrupteur> ();
    public Map<String, Vanne> vannesNames = new LinkedHashMap<String, Vanne> ();
    public List<Boolean> interruptValues = new ArrayList<Boolean> ();

    public EquationCircuit(String nom) {
        super(nom);
    }

    public void eval() {
        System.out.println("Evaluating circuit " + nom);
        List<Interrupteur> ins = new ArrayList<Interrupteur> (interruptNames.values());
        for (int i = 0; i < ins.size(); i++)
            if (interruptValues.get(i)) ins.get(i).on();
            else ins.get(i).off();
        interruptValues = new ArrayList<Boolean> ();
    }
    
}
