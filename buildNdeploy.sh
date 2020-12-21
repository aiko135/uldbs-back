mvn clean install -e
rm -r ~/uldbs-server/apache-tomee-plume-8.0.5/webapps/*
cp ./target/uldbs-back.war ~/uldbs-server/apache-tomee-plume-8.0.5/webapps
