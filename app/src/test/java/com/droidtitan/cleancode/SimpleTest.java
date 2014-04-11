package com.droidtitan.cleancode;

import android.view.View;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.util.ActivityController;

import static org.fest.assertions.api.ANDROID.assertThat;

@RunWith(MavenTestRunner.class)
public class SimpleTest {

    private MainActivity activity;

    @Before
    public void setUp() throws Exception {
        ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class);
        activity = controller.create().start().resume().get();
    }

    @Test
    public void testRange() throws Exception {
        View helloWorld = activity.findViewById(R.id.helloWorldTextView);
        assertThat(helloWorld).isVisible();
    }
}
