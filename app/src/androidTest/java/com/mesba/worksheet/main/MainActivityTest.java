package com.mesba.worksheet.main;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.test.filters.SmallTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.mesba.worksheet.R;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.mesba.worksheet.SmokeTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@SmallTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);
    private MainActivity mainActivity;

    @Before
    public void setActivity() {
        mainActivity = mActivityTestRule.getActivity();
    }

    @After
    public void tearDown() {
    }

    @SmokeTest
    @Test
    public void StartAndEndClock() {
        onView(withId(R.id.btn_track_time)).perform(click());
        onView(withId(R.id.btn_track_time)).perform(click());

    }

    @Test
    public void CheckTextShowTimesFrontPage(){
        onView(allOf(withId(R.id.btn_show), withText("SHOW TIMES")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
