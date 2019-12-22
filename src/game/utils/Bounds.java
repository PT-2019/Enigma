package game.utils;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

@Immutable
public final class Bounds {

    public final float left, right, top, bot;

    public Bounds(float left, float right, float top, float bot) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bot = bot;
    }

    public Bounds(Rectangle bounds) {
        this.left = bounds.x;
        this.right = this.left + bounds.width;
        this.top = bounds.y + bounds.height;
        this.bot = bounds.y;
    }

    public boolean contains(Vector2 pos) {
        if (pos.x >= this.left && pos.x <= this.right) {
            return pos.y >= this.bot && pos.y <= this.top;
        }
        return false;
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
