package me.meyerzinn.buildbattle.stages;

public enum Rating {
	SUPER_POOP(0), POOP(1), OK(2), GOOD(3), EPIC(4), LEGENDARY(5);

	private int numericRating;

	Rating(int rating) {
		this.numericRating = rating;
	}

	public int getNumericRating() {
		return this.numericRating;
	}

}
