JC = javac
ANTLR4 = java -jar ./antlr-4.5-complete.jar
JFLAGS = -g
JPATH = -cp .:./circuits/

CIRCUIT_SRC=$(shell ls circuits/*.java)
CIRCUIT_CLASS=$(CIRCUIT_SRC:.java=.class)

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $(JPATH) $*.java

all: circuits gram

circuits: $(CIRCUIT_CLASS:.java=.class)

gram:
	$(ANTLR4) CircuitGram.g4
	$(JC) *.java

clean:
	rm -f circuits/*.class
	rm -f *.class *.java *.tokens
