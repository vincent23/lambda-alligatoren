#!/usr/bin/make -f

########################################################################
HELPTEXT = \
=== UMLGraph Makefile ===~\
Important variables:~\
	PACKAGE: Sets the package to create the uml graph for. E.g. 'make PACKAGE=de.croggle.ui export:png'~\
	JAVA_HOME: Needs to be set to your Java installation folder, where UMLGraph can find "lib/tools.jar"~\
Available targets:~\
	$$ make [all] : Builds a .dot uml file with UMLGraph to out/ folder~\
	$$ make export:xxx : Renders the .dot UML file built with graphviz\' dot in a format specified by xxx. This can be png, svg etc.~\
	$$ make help : Print this help text~\
	$$ make clean : Delete all contents of out/ folder~\
Export needs graphviz\' dot renderer installed on the system~\
########################################################################

ROOTDIR 	= $(realpath $(dir $(lastword $(MAKEFILE_LIST))))
OUTDIR 		= $(ROOTDIR)/out
ECHO		= @echo
REQUIRE		= 	@if !(which $1 > /dev/null 2>&1);\
					then echo You need to have $1 installed for this action to work;\
					false;\
				fi
REQUIREFILE	= 	@if [ ! -f $1 ];\
					then echo Could not find file $1. $2;\
					false;\
				fi
AT			= @
ifeq ($(VERBOSE),3)
	AT		=
endif

# See http://www.umlgraph.org/doc/cd-opt.html for possible UMLGraph arguments:
UMLGRAPH 	= $(AT)java -classpath "$(ROOTDIR)/umlgraph/lib/UmlGraph.jar:$$JAVA_HOME/lib/tools.jar" \
	org.umlgraph.doclet.UmlGraph -outputencoding UTF-8 -quiet -private

# UML specific variables
SRCDIR		= $(ROOTDIR)/java
PACKAGE		= de
UNQUALIFIEDPACKAGENAME	= $(lastword $(PACKAGE:.=/))
PACKAGEPATH	= $(SRCDIR)/$(PACKAGE:.=/)
PARENTPPATH	= $(realpath $(dir $(PACKAGEPATH)))
FILENAME	= uml
DOTFILE		= $(OUTDIR)/$(FILENAME).dot
JAVAFILES	= $(shell find $(SRCDIR) -type f -name '*.java')

# Document specific variables
TEXFILENAME	= entwurf
PDFNAME		= $(TEXFILENAME)
TEXFILES	= $(shell find $(SRCDIR) -maxdepth 1 -type f -name '*.tex')


.PHONY: all debug document dotfile export\:% help umlgraph
.PRECIOUS: gollum $(OUTDIR)/$(FILENAME).% $(OUTDIR)/$(PDFNAME).pdf $(DOTFILE)

all: $(DOTFILE) document

debug:
	@echo UNQUALIFIEDPACKAGENAME: $(UNQUALIFIEDPACKAGENAME)
	@echo PACKAGEPATH: $(PACKAGEPATH)
	@echo PARENTPPATH: $(PARENTPPATH)

help:
	@printf "$(HELPTEXT:~=\\n)"
	
# Alias $(OUTDIR)/$(PDFNAME).pdf as target "document"
document: $(OUTDIR)/$(PDFNAME).pdf
	@true
	
dotfile umlgraph: $(DOTFILE)
	@true

# Builds pdf documents
$(OUTDIR)/$(PDFNAME).pdf: $(TEXFILES)
	$(call REQUIRE,pdflatex)
	$(ECHO) Building pdf document $(notdir $@)
	$(AT)pdflatex -jobname $(PDFNAME) -output-directory $(OUTDIR) $(TEXFILENAME).tex 1> /dev/null

$(OUTDIR):
	$(AT)mkdir $@
	
# Create a .dot file with umlgraph
$(DOTFILE): $(OUTDIR) $(SRCDIR) $(JAVAFILES)
	$(call REQUIRE,java)
	$(call REQUIREFILE,$$JAVA_HOME/lib/tools.jar,Is your JAVA_HOME variable set?)
	$(ECHO) building diagram...
	$(UMLGRAPH) -sourcepath $(PARENTPPATH) -subpackages $(UNQUALIFIEDPACKAGENAME) -output $@

# Map target "export:xxx" on an output file of type xxx
export\:%: $(OUTDIR)/$(FILENAME).%
	@true

# Render the .dot file with graphviz' dot
$(OUTDIR)/$(FILENAME).%: $(DOTFILE)
	$(call REQUIRE,dot)
	$(ECHO) exporting diagram as $* file...
	$(AT)dot -T$* -o$(OUTDIR)/$(FILENAME).$* $(DOTFILE)


clean:
	$(AT)rm -rf $(ROOTDIR)/out/*
