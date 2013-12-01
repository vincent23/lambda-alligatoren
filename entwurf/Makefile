#!/usr/bin/make -f

ROOTDIR 	= $(realpath $(dir $(lastword $(MAKEFILE_LIST))))
SRCDIR		= $(ROOTDIR)/java
OUTDIR 		= $(ROOTDIR)/out
ECHO		= @echo
REQUIRE		= 	@if !(which $1 > /dev/null 2>&1);\
					then echo You need to have $1 installed for this action to work;\
					false;\
				fi
AT			= @
ifeq ($(VERBOSE),3)
	AT		=
endif

# Possible UMLGraph arguments:
#-overview <file>          Read overview documentation from HTML file
#-public                   Show only public classes and members
#-protected                Show protected/public classes and members (default)
#-package                  Show package/protected/public classes and members
#-private                  Show all classes and members
#-help                     Display command line options and exit
#-doclet <class>           Generate output via alternate doclet
#-docletpath <path>        Specify where to find doclet class files
#-sourcepath <pathlist>    Specify where to find source files
#-classpath <pathlist>     Specify where to find user class files
#-exclude <pkglist>        Specify a list of packages to exclude
#-subpackages <subpkglist> Specify subpackages to recursively load
#-breakiterator            Compute 1st sentence with BreakIterator
#-bootclasspath <pathlist> Override location of class files loaded by the bootstrap class loader
#-source <release>         Provide source compatibility with specified release
#-extdirs <dirlist>        Override location of installed extensions
#-verbose                  Output messages about what Javadoc is doing
#-locale <name>            Locale to be used, e.g. en_US or en_US_WIN
#-encoding <name>          Source file encoding name
#-quiet                    Do not display status messages
#-J<flag>                  Pass <flag> directly to the runtime system
#-X                        Print a synopsis of nonstandard options
#-output                   Where to save the generated dotfile. "-" as output will write to stdout.
UMLGRAPH 	= $(AT)java -classpath "$(ROOTDIR)/umlgraph/lib/UmlGraph.jar:$$JAVA_HOME/lib/tools.jar" \
	org.umlgraph.doclet.UmlGraph -sourcepath $(SRCDIR) -quiet -private

# UML specific variables
PACKAGE		= de
FILENAME	= uml
DOTFILE		= $(OUTDIR)/$(FILENAME).dot
JAVAFILES	= $(shell find $(SRCDIR) -type f -name '*.java')

# Document specific variables
TEXFILENAME	= entwurf
PDFNAME		= $(TEXFILENAME)
TEXFILES	= $(shell find $(SRCDIR) -maxdepth 1 -type f -name '*.tex')


.PHONY: all document dotfile export\:% help umlgraph
.PRECIOUS: gollum $(OUTDIR)/$(FILENAME).% $(OUTDIR)/$(PDFNAME).pdf $(DOTFILE)

all: $(DOTFILE) document

help:
	$(ECHO) "=== UMLGraph Makefile ==="
	$(ECHO) "Available options:"
	$(ECHO) "$$ make [all] : Builds a .dot uml file with UMLGraph to out/ folder"
	$(ECHO) "$$ make export:xxx : Renders the .dot UML file built with graphviz' dot in a format specified by xxx. This can be png, svg etc."
	$(ECHO) "$$ make help : Print this help text"
	$(ECHO) "$$ make clean : Delete all contents of out/ folder"
	$(ECHO) "Export needs graphviz' dot renderer installed on the system"
	
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
	$(ECHO) building diagram...
	$(UMLGRAPH) -subpackages $(PACKAGE) -output $@

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
