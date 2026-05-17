#!/bin/bash

echo ""
echo "Compiling Program..."
rm -f SteepestDescent.class
javac SteepestDescent.java

if [ $? -ne 0 ]; then
    echo "Compilation failed!"
    exit 1
fi

echo ""
echo "Running Steepest Descent Program..."
echo ""

java SteepestDescent