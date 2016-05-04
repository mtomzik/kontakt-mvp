package io.kontakt;

public class Model {

	private final String left;
	private final String right;

	public Model(final String left, final String right) {
		this.left = left;
		this.right = right;
	}

	public String left() {
		return this.left;
	}

	public String right() {
		return this.right;
	}

}
