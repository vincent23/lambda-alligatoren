
.PHONY: all dot

ROOTDIR 	= $(realpath $(dir $(lastword $(MAKEFILE_LIST))))
SRCDIR		= $(ROOTDIR)/java
OUTDIR 		= $(ROOTDIR)/out

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
UMLGRAPH 	= @java -classpath "$(ROOTDIR)/umlgraph/lib/UmlGraph.jar:$$JAVA_HOME/lib/tools.jar" \
	org.umlgraph.doclet.UmlGraph -sourcepath $(SRCDIR) -quiet
FILENAME	= uml
DOTFILE		= $(OUTDIR)/$(FILENAME).dot

all: $(DOTFILE)

dot:
	@if  !(which dot > /dev/null 2>&1); \
		then echo "You need to have dot installed for advanced export options";\
		false;\
	fi

$(OUTDIR):
	@mkdir $@
	
$(DOTFILE): $(OUTDIR) $(SRCDIR)
	$(UMLGRAPH) -package alligatorgame -output $@

export\:%: dot $(DOTFILE)
	@dot -T$* -o$(OUTDIR)/$(FILENAME).$* $(DOTFILE)
