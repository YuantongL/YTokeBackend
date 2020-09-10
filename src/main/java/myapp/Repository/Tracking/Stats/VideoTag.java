
package myapp;

public enum VideoTag {
	WITH_VOCAL("WITH_VOCAL"),
	OFF_VOCAL("OFF_VOCAL");

    private String stringValue;

    VideoTag(String stringValue) {
        this.stringValue = stringValue;
    }
}