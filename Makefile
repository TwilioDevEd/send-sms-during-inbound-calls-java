.PHONY: install serve

install:
	mvn compile

serve:
	mvn compile exec:java -Dexec.mainClass=com.twilio.app.App
