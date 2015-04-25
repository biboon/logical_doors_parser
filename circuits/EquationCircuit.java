package circuits;

import java.util.Map;
import java.util.LinkedHashMap;

public class EquationCircuit extends Circuit {

    public Map<String, Interrupteur> interruptNames = new LinkedHashMap<String, Interrupteur> ();
    public Map<String, Vanne> vannesNames = new LinkedHashMap<String, Vanne> ();
    
    public EquationCircuit() {
        super();
    }
}
