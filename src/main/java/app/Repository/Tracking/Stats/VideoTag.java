
package app.Repository.Tracking.Stats;

public enum VideoTag {
	WITH_VOCAL("WITH_VOCAL"), OFF_VOCAL("OFF_VOCAL");

	private String stringValue;

	VideoTag(String stringValue) {
		this.setStringValue(stringValue);
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
}