#!/bin/sh

SERVER_PORT=20885
APP_PATH=`pwd`
KILL_FLAG=""

if  [ $KILL_FLAG ] ;then
	LIVE_TPID=`ps -ef|grep $APP_PATH|grep -v grep|grep -v kill|awk '{print $2}'`
    if [ $LIVE_TPID ] ;then
    	echo "$LIVE_TPID Stop Process..."
    	kill -15 $LIVE_TPID
    	sleep 3
    	LIVE_TPID=`ps -ef|grep $APP_PATH|grep -v grep|grep -v kill|awk '{print $2}'`
    	if [ $LIVE_TPID ] ; then
		    echo 'Kill Process!'
		    kill -9 $LIVE_TPID
		else
		    echo '$LIVE_TPID Stop Success!'
		fi
    fi
fi
