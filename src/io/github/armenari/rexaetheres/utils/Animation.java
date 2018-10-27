package io.github.armenari.rexaetheres.utils;

import io.github.armenari.rexaetheres.component.Component;

public class Animation {

	private boolean loop;
	private float frame = 0f;
	private int frame_limit;
	private float speed = Constants.ANIMATION_SPEED;
	private String asset;

	public Animation(String asset, boolean loop) {
		this.loop = loop;
		this.frame_limit = Component.animation_data.getJsonObject(asset).getJsonArray("frames").size() - 1;
		this.asset = asset;
	}

	public void next() {
		frame += speed;
		if (loop) {
			if (frame > (float) frame_limit + 0.5f || frame < 0f - 0.5f) {
				speed = -1 * speed;
			}
		} else {
			if (frame >= frame_limit) {
				frame = frame_limit;
			}
		}

	}

	public void previous() {
		frame -= speed;
		if (loop) {
			if (frame > (float) frame_limit + 0.5f || frame < 0f - 0.5f) {
				speed = -1 * speed;
			}
		} else {
			if (frame < 0f - 0.5f) {
				frame = 0;
			}
		}
	}

	/**
	 * @return the frame
	 */
	public int getFrameReverse() {
		return (int) (this.frame_limit - this.frame);

	}

	public int getFrame() {
		return (int) this.frame;
	}

	public void setFrame(int i) {
		this.frame = i;
	}

	/**
	 * @return the asset
	 */
	public String getAsset() {
		return asset;
	}

	public void setSpeed(float f) {
		this.speed = f;
	}

	public float getSpeed() {
		return this.speed;
	}

	/**
	 * @return the frame_limit
	 */
	public int getFrameLimit() {
		return frame_limit;
	}

}
