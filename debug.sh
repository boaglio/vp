#!/bin/bash

reset

activator clean 

activator debug run  -jvm-debug 9999
