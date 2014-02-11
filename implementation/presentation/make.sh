#!/bin/bash
OUTDIR=out
FILENAME=implementation_presentation.tex
latexmk -pdf -output-directory=$OUTDIR -auxdir=$OUTDIR $FILENAME
