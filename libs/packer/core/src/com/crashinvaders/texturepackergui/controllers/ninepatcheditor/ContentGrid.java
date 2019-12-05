package com.crashinvaders.texturepackergui.controllers.ninepatcheditor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ContentGrid extends PatchGrid {

    private final Drawable contentAreaDrawable;

    public ContentGrid(Skin skin, Color primaryColor, GridValues values) {
        super(skin, primaryColor, values);
        contentAreaDrawable = ((NinePatchDrawable)skin.getDrawable("white"))
                .tint(new Color(primaryColor.r, primaryColor.g, primaryColor.b, 0.25f));
    }

    @Override
    protected void drawAreaGraphics(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);

//        // Fill outer area
//        contentAreaDrawable.draw(batch, getX(), getY(), left.getX(), getHeight());
//        contentAreaDrawable.draw(batch, getX() + right.getX(), getY(), getWidth() - right.getX(), getHeight());
//        contentAreaDrawable.draw(batch, getX() + left.getX(), getY(), right.getX() - left.getX(), bottom.getY());
//        contentAreaDrawable.draw(batch, getX() + left.getX(), getY() + top.getY(), right.getX() - left.getX(), getHeight() - top.getY());

        // Fill inner area
        contentAreaDrawable.draw(batch, getX() + left.getX(), getY() + bottom.getY(), right.getX() - left.getX(), top.getY() - bottom.getY());
    }

    @Override
    protected void validateLinePositions() {
        super.validateLinePositions();

        // Cut lines, leaving only a box shape
        left.setY(bottom.getY());
        left.setHeight(top.getY() - bottom.getY());
        right.setY(left.getY());
        right.setHeight(left.getHeight());
        top.setX(left.getX());
        top.setWidth(right.getX() - left.getX());
        bottom.setX(top.getX());
        bottom.setWidth(top.getWidth());
    }
}
