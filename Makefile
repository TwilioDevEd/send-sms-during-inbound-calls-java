install:
	mvn compile

serve:
	. .env
	mvn compile exec:java -Dexec.mainClass=com.twilio.app.App
