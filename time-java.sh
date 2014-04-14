#!/bin/bash

#START=$(date +%s%N)

java -cp ./tlang-declarative-nowf.jar exampleprogs.Main in/recipes/02-rewrites.txt in

#END=$(date +%s%N)
#DIFF=$((( $END - $START ) / 1000000))
#echo "It took $DIFF milliseconds"

