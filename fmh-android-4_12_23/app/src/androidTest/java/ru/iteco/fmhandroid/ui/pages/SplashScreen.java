package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static ru.iteco.fmhandroid.ui.matchers.TimeoutEspresso.onViewWithTimeout;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.matchers.TimeoutEspresso;

public class SplashScreen {
    public static TimeoutEspresso.TimedViewInteraction welcomeImage =
            onViewWithTimeout(withId(R.id.splashscreen_image_view));
    public static TimeoutEspresso.TimedViewInteraction progressIndicator =
            onViewWithTimeout(withId(R.id.splash_screen_circular_progress_indicator));
    public static TimeoutEspresso.TimedViewInteraction welcomeText =
            onViewWithTimeout(withId(R.id.splashscreen_text_view));
}
