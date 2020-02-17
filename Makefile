.PHONY: build
build:
	./gradlew assembleRelease

.PHONY: clean
clean:
	./gradlew clean

.PHONY: publishBintray
publishBintray:
	make build
	./gradlew :goong-android-places-plugin:assembleRelease
	./gradlew :goong-android-places-plugin:publishToMavenLocal :goong-android-places-plugin:bintrayUpload
