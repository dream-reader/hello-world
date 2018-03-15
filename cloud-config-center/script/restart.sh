#!/bin/sh

SERVER_PORT=20885
APP_PATH=`pwd`
FILE=
KILL_FLAG="1"
if [ -f "$FILE" ]; then
	TP_ID=`cat tpid`
	if  [ $TP_ID ] ;then
	    LIVE_TPID=`ps -ef|grep $TP_ID|grep -v grep|grep -v kill|awk '{print $2}'`
	    if [ $LIVE_TPID ] && [ "$TP_ID" == "$LIVE_TPID" ] ;then
	    	echo "$TP_ID Stop Process..."
	    	kill -15 $TP_ID
	    	sleep 3
	    	LIVE_TPID=`ps -ef|grep $TP_ID|grep -v grep|grep -v kill|awk '{print $2}'`
	    	if [ $LIVE_TPID ] && [ "$TP_ID" == "$LIVE_TPID" ] ; then
			    echo 'Kill Process!'
			    kill -9 $TP_ID
			else
			    echo '$TP_ID Stop Success!'
			    KILL_FLAG=""
			fi
	    fi
	fi
fi

rm -f tpid

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

#JAVA_OPTS=" -server -Xms2g -Xmx2g -Xss256k -Djava.rmi.server.hostname=localhost -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=1090 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false"

JAVA_OPTS=" -server -Xms512m -Xmx512m -Xss256k -Dapp.server.path=$APP_PATH -Ddubbo.protocol.port=$SERVER_PORT -XX:+DisableExplicitGC -XX:+CMSParallelRemarkEnabled -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70"

JAVA_DEBUG_OPTS=""
if [ "$1" = "debug" ]; then
     JAVA_DEBUG_OPTS=" -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=9082,server=y,suspend=n "
fi

# nohup java -jar myapp.jar --spring.config.location=application.yml > /dev/null 2>&1 &
nohup java $JAVA_OPTS $JAVA_DEBUG_OPTS -jar cloud-config-center.jar > stdout.log 2>&1 &

echo $! > tpid
echo Start Success!

tail -f stdout.log
