#!/bin/bash
OUTDIR=out
FILENAME=implementation.tex
latexmk -pdf -output-directory=$OUTDIR -auxdir=$OUTDIR $FILENAME
