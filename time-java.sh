#!/bin/bash

#START=$(date +%s%N)

java -cp ./tlang-declarative.jar exampleprogs.Main in out

#END=$(date +%s%N)
#DIFF=$((( $END - $START ) / 1000000))
#echo "It took $DIFF milliseconds"

