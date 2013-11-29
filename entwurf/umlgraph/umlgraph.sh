#!/bin/sh
#
# Unix shell script to run UMLGraph on the specified base file name
# For this to work you must adjust the following defintion of
# UMLGRAPH_HOME to point to the directory where UmlGraph.jar is installed.
#
# $Id$
#

UMLGRAPH_HOME=lib

if [ x$2 = x ]
then
	echo usage: umlgraph base_file_name filetype [umlgraph arguments] 1>&2
	echo example: umlgraph MyClass png 1>&2
	echo '(The above will convert MyClass.java into MyClass.png)' 1>&2
	exit 1
else
	BASE=$1
	FILETYPE=$2
	shift 2
	java -classpath "$UMLGRAPH_HOME/UmlGraph.jar:$JAVA_HOME/lib/tools.jar" \
	org.umlgraph.doclet.UmlGraph -package $* -output - $BASE.java |
	dot -T$FILETYPE -o$BASE.$FILETYPE
fi
