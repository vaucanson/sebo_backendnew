#!/usr/bin/bash
printf "SUPPRESSION DES FICHIERS INDÉSIRÉS :\n"
rm -v src/*/*orig 2>/dev/null
rm -v src/*/*bak 2>/dev/null
rm -v src/*/*/*orig 2>/dev/null
rm -v src/*/*/*bak 2>/dev/null
rm -v src/*/*/*/*orig 2>/dev/null
rm -v src/*/*/*/*bak 2>/dev/null
rm -v src/*/*/*/*/*orig 2>/dev/null
rm -v src/*/*/*/*/*bak 2>/dev/null

printf "\nRECHERCHE DE FICHIERS CONTENANT DES ANOMALIES :\n"
grep "<<" src/*/* 2>/dev/null
grep "<<" src/*/*/* 2>/dev/null
grep "<<" src/*/*/*/* 2>/dev/null
grep "<<" src/*/*/*/*/* 2>/dev/null