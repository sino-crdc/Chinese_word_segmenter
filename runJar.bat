call mvn -Dmaven.test.skip clean install dependency:copy-dependencies
set JAVA_OPTS=-Xms1200m -Xmx1200m
set CLASS_PATH=word.jar;target/word-1.3.jar;target/dependency/slf4j-api-1.6.4.jar;target/dependency/logback-classic-0.9.28.jar;target/dependency/logback-core-0.9.28.jar
set EXECUTOR=java %JAVA_OPTS% -cp %CLASS_PATH%
call %EXECUTOR% Main %1 %2 gbk