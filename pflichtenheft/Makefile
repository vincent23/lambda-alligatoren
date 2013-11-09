MAIN_FILE=pflichtenheft

DOCUMENTS=\
	$(MAIN_FILE)\
	funktionaleanforderungen\
	glossar\
	nichtfunktionaleanforderungen\
	produktdaten\
	produkteinsatz\
	produktumgebung\
	systemmodelle\
	testfaelle\
	zielbestimmung\
	commands\


TOC=$(MAIN_FILE).toc
REFS=$(MAIN_FILE).out

DOCUMENTS_TEX=$(DOCUMENTS:=.tex)
DOCUMENTS_AUX=$(DOCUMENTS:=.aux)

all: $(DOCUMENTS_TEX)
	pdflatex $(MAIN_FILE).tex

clean:
	rm $(DOCUMENTS_AUX) $(TOC) $(REFS) $(MAIN_FILE).pdf $(MAIN_FILE).log

.PHONY: all clean
