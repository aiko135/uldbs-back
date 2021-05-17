mvn clean install -e
rm -r ../ApacheTomeeServerConfigured/webapps/*
cp ./target/uldbs-back.war ../ApacheTomeeServerConfigured/webapps
