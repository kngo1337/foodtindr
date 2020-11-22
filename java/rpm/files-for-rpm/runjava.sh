#!/bin/sh
real_exe=`x=$0; while test -L $x ; do x=$(readlink $x); done; echo $x`
dir=`dirname $real_exe`
bindir=`cd $dir && /bin/pwd`

export HOME=`cd $bindir/../ && /bin/pwd`

java -classpath "$HOME/lib/*" "$@"