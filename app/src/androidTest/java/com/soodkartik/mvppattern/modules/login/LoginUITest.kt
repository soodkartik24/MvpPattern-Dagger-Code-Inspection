package com.soodkartik.mvppattern.modules.login

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.soodkartik.mvppattern.R
import com.soodkartik.mvppattern.modules.login.activity.LoginActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoginUITest {

    private val USERNAME_TYPED = "Kartiksood"

    private val USERNAME_PASSWORD = "123456"

    @get:Rule
    var mLoginActivityRule: ActivityTestRule<LoginActivity> =
        ActivityTestRule(LoginActivity::class.java)

    @Test
    fun loginClickedSuccess() {
        onView(withId(R.id.username_ed))
            .perform(typeText(USERNAME_TYPED))
        onView(withId(R.id.password_ed))
            .perform(typeText(USERNAME_PASSWORD))

        onView(withId(R.id.submit_button)).perform(closeSoftKeyboard()).perform(click())
    }
}