#Shrey Valia & Aman Mangalore
#svalia & aamangal
#12/8/14
#cmps012b
#The makefile we made for asg4


JAVASRC    = CYOA.java
SOURCES    = ${JAVASRC} Makefile
ALLSOURCES = ${SOURCES}
MAINCLASS  = CYOA
CLASSES    = ${patsubst %.java, %.class, ${JAVASRC}}

all: ${CLASSES}

%.class: %.java
	javac -Xlint $<

clean:
	rm -f *.class

test: all
	java CYOA demo.adventure

.PHONY: clean all test







