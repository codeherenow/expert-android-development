package com.codeherenow.butterknife;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectViews;
import butterknife.OnClick;

/**
 * Example for {@link android.view.View.OnClickListener} injection using
 * {@link butterknife.OnClick} annotation. When you are using `@OnClick`,
 * remember the following:
 *
 *     1. Methods that are annotated with `OnClick` should not be private.
 *     2. Methods can have a single `View` parameter or have no parameters.
 *     3. `OnClick` can accept an array of `View` IDs if you want the same
 *        listener to be used by multiple views.
 *
 * @author Ragunath Jawahar <www.codeherenow.com>
 */
public class TrafficLightsActivity extends Activity {

    @InjectViews({
        R.id.redImageButton,
        R.id.amberImageButton,
        R.id.greenImageButton
    })
    List<ImageButton> mLightImageButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic_lights);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.redImageButton)
    void turnOnRedLight() {
        turnOnLight(0, R.drawable.red_on);
    }

    @OnClick(R.id.amberImageButton)
    void turnOnAmberLight() {
        turnOnLight(1, R.drawable.amber_on);
    }

    @OnClick(R.id.greenImageButton)
    void turnOnGreenLight() {
        turnOnLight(2, R.drawable.green_on);
    }

    /*
     * Alternative method, injecting the same `OnClickListener` into
     * multiple view instances.
     */
    /*
    @OnClick({
        R.id.redImageButton,
        R.id.amberImageButton,
        R.id.greenImageButton
    })
    void onLightClicked(View view) {
        switch (view.getId()) {
            case R.id.redImageButton:
                turnOnRedLight();
                break;

            case R.id.amberImageButton:
                turnOnAmberLight();
                break;

            case R.id.greenImageButton:
                turnOnGreenLight();
                break;
        }
    }
    */

    private void turnOnLight(int lightIndex, int drawableResId) {
        ButterKnife.apply(mLightImageButtons, TURN_OFF);
        mLightImageButtons.get(lightIndex)
            .setImageResource(drawableResId);
    }

    private final ButterKnife.Action<ImageButton> TURN_OFF =
            new ButterKnife.Action<ImageButton>() {
                @Override
                public void apply(ImageButton view, int index) {
                    view.setImageResource(R.drawable.light_off);
                }
            };
}
