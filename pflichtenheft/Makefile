#!/usr/bin/make -f
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
	Systemmodelle/anwendungsfaelle\
	Systemmodelle/benutzerschnittstelle\
	Systemmodelle/dynamischemodelle\
	Systemmodelle/objektmodell\
	Systemmodelle/szenarien\

TOC=$(MAIN_FILE).toc
REFS=$(MAIN_FILE).out

DOCUMENTS_TEX=$(DOCUMENTS:=.tex)
DOCUMENTS_AUX=$(DOCUMENTS:=.aux)


DIAGRAMS=\
	Systemmodelle/player_class.svg\
#	Systemmodelle/level_class.svg\
#	Systemmodelle/game_state.svg\

DIAGRAMS_PDF=$(DIAGRAMS:.svg=.pdf)

all: $(DOCUMENTS_TEX) $(DIAGRAMS_PDF)
	pdflatex $(MAIN_FILE).tex

clean:
	rm -f $(DOCUMENTS_AUX) $(TOC) $(REFS) $(MAIN_FILE).pdf $(MAIN_FILE).log $(DIAGRAMS_PDF)

%.pdf: %.svg
	convert $< $@

.PHONY: all clean
