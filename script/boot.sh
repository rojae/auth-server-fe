#!/bin/bash
# ./gradlew clean build

java -jar index-web/build/libs/index-web-0.0.1-SNAPSHOT.jar > index-web/log.out 2>&1 &
java -jar auth-signin-web/build/libs/auth-signin-web-0.0.1-SNAPSHOT.jar > auth-signin-web/log.out 2>&1 &
java -jar auth-signup-web/build/libs/auth-signup-web-0.0.1-SNAPSHOT.jar > auth-signup-web/log.out 2>&1 &

