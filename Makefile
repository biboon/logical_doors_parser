JC = javac
JFLAGS = -g # -Xlint:unchecked
JPATH = -cp .:./circuits/

CIRCUIT_SRC=$(shell ls circuits/*.java)
CIRCUIT_CLASS=$(CIRCUIT_SRC:.java=.class)

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $(JPATH) $*.java

all: circuits
circuits: $(CIRCUIT_CLASS:.java=.class)

clean:
	rm -f circuits/*.class
