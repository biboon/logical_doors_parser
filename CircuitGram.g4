grammar CircuitGram;

@header { import circuits.*; }
@members { EquationCircuit myCircuit = new EquationCircuit(); }

main : 'eq_circuit' '('inlist')' 'returns' '('outlist')' eqlist 'end' cmdlist;


inlist :
  ID { Interrupteur tmp=new Interrupteur($ID.text); myCircuit.addComponent(tmp); myCircuit.interruptNames.put($ID.text, tmp); }
| ID ',' inlist { Interrupteur tmp=new Interrupteur($ID.text); myCircuit.addComponent(tmp); myCircuit.interruptNames.put($ID.text, tmp); };

outlist :
  ID { Vanne tmp=new Vanne($ID.text); myCircuit.addComponent(tmp); myCircuit.vannesNames.put($ID.text, tmp); }
| ID ',' outlist { Vanne tmp=new Vanne($ID.text); myCircuit.addComponent(tmp); myCircuit.vannesNames.put($ID.text, tmp); };

expr returns [ Composant e ] : 
  ID { $e=myCircuit.interruptNames.get($ID.text); }
| e1=expr '&' e2=expr { And tmp=new And(); tmp.setIn1($e1.e); tmp.setIn2($e2.e); myCircuit.addComponent(tmp); $e=tmp; }
| e1=expr '|' e2=expr { Or tmp=new Or(); tmp.setIn1($e1.e); tmp.setIn2($e2.e); myCircuit.addComponent(tmp); $e=tmp; }
| '!' var=expr { Not tmp=new Not(); tmp.setIn($var.e); myCircuit.addComponent(tmp); $e=tmp; }
| '(' var=expr ')' { $e=$var.e; };

eq : ID '=' expr ';' { Vanne v=myCircuit.vannesNames.get($ID.text); v.setIn($expr.e); };

eqlist : eq | eq eqlist;

bool : 'true' | 'false';

boollist : bool | bool ',' boollist;

cmd :
  'descr' '(' ')' { myCircuit.description(); }
| 'eval' '(' boollist ')';

cmdlist : cmd | cmd cmdlist;

ID : [a-zA-Z0-9]+ ;
WS : [ \t\n\r]+ -> skip ;

