package game.utils;

@Immutable
public final class Bounds {

	public final float left, right, top, bot;

	public Bounds(float left, float right, float top, float bot) {
		this.left = left;
		this.right = right;
		this.top = top;
		this.bot = bot;
	}

	@Override
	public String toString() {
		return "Bounds{" +
				"left=" + left +
				", right=" + right +
				", top=" + top +
				", bot=" + bot +
				'}';
	}
}
