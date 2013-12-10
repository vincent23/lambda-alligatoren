#!/usr/bin/make -f

########################################################################
HELPTEXT = \
=== UMLGraph Makefile ===~\
Important variables:~\
	PACKAGE: Sets the package to create the uml graph for. E.g. 'make PACKAGE=de.croggle.ui export:png'~\
	EXPORTFORMAT: The format used for exporting ~\
	VERBOSE: Setting this variable to "3" will print all relevant commands executed.~\
	VIEWS: If set to "yes" on the command line, UMLGraph will build the graph with views support~\
Available targets:~\
	$$ make [all] : Builds a .dot uml file with UMLGraph to out/ folder~\
	$$ make export : Renders the .dot UML file built with graphviz\' dot in a format specified by variable EXPORTFORMAT.~\
	$$ make help : Print this help text~\
	$$ make texdoclet : Builds a .tex file from the java sources~\
	$$ make document ~\
	$$ make clean : Delete all contents of out/ folder and generated java files in source tree~\
	$$ make clean-src : Delete all uml.java in the java/de/... folders~\
	$$ make clean-out : Delete all files in out/~\
	$$ make clean-uml : Delete all uml diagram related files in out/~\
	$$ make clean-doc : Delete all latex related files in out/~\
Export needs graphviz\' dot renderer installed on the system~\
########################################################################

# Make life easier
ROOTDIR 		= $(realpath $(dir $(lastword $(MAKEFILE_LIST))))
OUTDIR 			= $(ROOTDIR)/out
ECHO			= @echo
REQUIRE			= 	@if !(which $1 > /dev/null 2>&1);\
						then echo You need to have $1 installed for this action to work;\
						false;\
					fi
REQUIREFILE		= 	@if [ ! -f $1 ];\
						then echo Could not find file $1. $2;\
						false;\
					fi

AT				= @
SPACE 			:=
SPACE 			+=
MUTE			= 1> /dev/null
ifeq ($(VERBOSE),3)
	AT			=
	MUTE		= 
endif

# Javadoc
SRCDIR			= $(ROOTDIR)/java
LIBPATH			= $(ROOTDIR)/lib
DOCLETPATH		= $(LIBPATH)
PACKAGE			= de.croggle
PACKAGEPATH		= $(SRCDIR)/$(subst .,/,$(PACKAGE))
JAVAFILES		= $(shell find $(PACKAGEPATH) -type f -name '*.java')
CP				=
CP				+= $(LIBPATH)/gdx.jar
CP				+= $(LIBPATH)/android.jar
CLASSPATH		= $(subst $(SPACE),:,$(strip $(CP)))
JAVADOC			= javadoc -classpath "$(CLASSPATH)" -quiet \
					-sourcepath $(SRCDIR) -subpackages $(PACKAGE)

# UMLGraph
UMLGRAPHDOCLET	= -docletpath $(DOCLETPATH)/UmlGraph.jar -doclet org.umlgraph.doclet.UmlGraph $(VIEWSOPT) \
					-private -d "$(OUTDIR)" -outputencoding UTF-8
VIEWSOPT		= -views
VIEWFILES		= $(PACKAGEPATH)/uml.java 
#$(shell find $(PACKAGEPATH) -type f -name 'uml.java')
UMLFILES		= $(addprefix $(OUTDIR)/,$(notdir $(subst .java,.dot,$(VIEWFILES))))
UMLDEPS			= $(UMLFILES)
EXPORTFORMAT	= svg
DPI				= 72

# Document specific variables
TEXFILENAME	= entwurf
PDFNAME		= $(TEXFILENAME)
TEXFILES	= $(shell find $(ROOTDIR) -maxdepth 1 -type f -name '*.tex') $(TEXDOCFILE)

# TexDoclet
TEXDOCLET		= -docletpath $(DOCLETPATH)/TexDoclet.jar -doclet org.wonderly.doclets.TexDoclet
TEXDOCFILE		= $(OUTDIR)/docs.tex

.PHONY: all debug help clean clean-% export\:% export texdoclet document
.PRECIOUS: $(subst dot,$(EXPORTFORMAT),$(UMLDEPS))

all: export document
	$(ECHO) finished.
	
help:
	@printf "$(HELPTEXT:~=\\n)"

debug:
	$(ECHO) nothing to see here...
	$(ECHO) $(VIEWFILES)

umlgraph: $(UMLDEPS)
	@true

# Make uml graphs based on views
$(UMLFILES): $(OUTDIR)/ $(PACKAGEPATH)/uml.java $(JAVAFILES)
	$(call REQUIRE,javadoc)
	$(ECHO) building diagram...
	$(AT)$(JAVADOC) $(UMLGRAPHDOCLET) $(MUTE)
	
# Have uml class updated in every subpackage
$(SRCDIR)/%/uml.java: $(SRCDIR)/uml.java
	$(ECHO) Updating uml.java for package $(PACKAGE)...
	$(AT)cp -f $< $@
	$(AT)perl -i -pe 's/.*/package $(PACKAGE);/ if $$. == 1' $@

# Legacy: Delegate export:xxx commands to export
export\:%: export
	@true
	
# Alias for exported files
export: $(UMLDEPS:%.dot=%.$(EXPORTFORMAT))
	$(ECHO) done exporting.

# Export with dot
$(UMLDEPS:%.dot=%.$(EXPORTFORMAT)): %.$(EXPORTFORMAT): $(OUTDIR)/ %.dot
	$(call REQUIRE,dot)
	$(call REQUIRE,unflatten)
	$(ECHO) exporting diagram as $(EXPORTFORMAT) file...
	@# The actual command: Unflatten and dot.
	@# -T: Set the output file type
	@# -LC1: Set overlap expansion factor
	@# -Gsize: Set max bounding box to DIN A0
	@# -Gratio: Set aspect ratio to 1/sqrt(2)
	@# -o: Output file name
	$(AT)unflatten -l 4 -f -c 10 $*.dot |\
	dot \
		-T$(EXPORTFORMAT) \
		-LC1 \
		-Gdpi=$(DPI)\
		-Gsize="46.8,33.1" \
		-Gratio=0.7071 \
		-Ncolorscheme=set312 \
		-o$@
	
# Alias to build tex docs
texdoclet: $(TEXDOCFILE)
	@true;
	
# Builds tex docs with TexDoclet
$(TEXDOCFILE): $(OUTDIR) $(JAVAFILES)
	$(AT)cd $(OUTDIR); $(JAVADOC) $(TEXDOCLET)
	
# Alias $(OUTDIR)/$(PDFNAME).pdf as target "document"
document: $(OUTDIR)/$(PDFNAME).pdf
	@true

# Builds pdf documents
$(OUTDIR)/$(PDFNAME).pdf: $(OUTDIR) $(TEXDOCFILE) $(TEXFILES)
	$(call REQUIRE,pdflatex)
	$(ECHO) Building pdf document $(notdir $@)
	$(AT)pdflatex -interaction batchmode -jobname $(PDFNAME) -output-directory $(OUTDIR) $(ROOTDIR)/$(TEXFILENAME).tex $(MUTE)

# Make sure, $(OUTDIR) exists
$(OUTDIR)/:
	$(AT)mkdir $@
	
# Clean
clean: clean-out clean-src
	@true

clean-out:
	$(AT)rm -rf $(OUTDIR)/*

clean-texdoclet:
	$(AT)rm -f $(TEXDOCFILE)

clean-uml:
	$(AT)find $(OUTDIR)/ -type f \( -name '*.png' -o -name '*.svg' -o -name '*.dot' \) -exec rm {} \;
	
clean-doc: clean-doc-pdf clean-doc-tmp
	@true
	
clean-doc-pdf:
	$(AT)find $(OUTDIR)/ -type f -name '*.pdf' -exec rm -f {} \;

clean-doc-tmp:
	$(AT)find $(OUTDIR)/ -type f \( -name '*.aux' -o -name '*.log' -o -name '*.out' -o -name '*.toc' \) -exec rm {} \;
	
# Used later to clean all UMLGraph specific files in source tree
clean-src:
	$(AT)find $(SRCDIR)/de/ -type f -name 'uml.java' -exec rm -f {} \;
	$(AT)find $(SRCDIR)/de/ -type f -name 'UMLOptions.java' -exec rm -f {} \;
	$(AT)find $(SRCDIR)/de/ -type f -name 'PackageUMLView.java' -exec rm -f {} \;
