//package com.popbeans.plant;
//
//import androidx.test.ext.junit.rules.ActivityScenarioRule;
//import androidx.test.ext.junit.runners.AndroidJUnit4;
//import androidx.test.filters.LargeTest;
//
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.testng.annotations.BeforeSuite;
//
//import java.io.File;
//import java.net.URL;
//
//import io.appium.java_client.MobileElement;
//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.service.local.AppiumDriverLocalService;
//
//@RunWith(AndroidJUnit4.class)
//@LargeTest
//public class PlantInstrumentedTest_BAK {
//
//    private AndroidDriver<WebElement> driver;
//    private static AppiumDriverLocalService service;
//    private final String appPackage = "io.appium.android.apis";
//
//    @BeforeSuite
//    public void setUp() throws Exception {
//        File classpathRoot = new File(System.getProperty("user.dir"));
//        File appDir = new File(classpathRoot, "../apps");
//        File app = new File(appDir.getCanonicalPath(), "app-debug-androidTest.apk");
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("deviceName", "Android Emulator");
//        capabilities.setCapability("app", app.getAbsolutePath());
//        capabilities.setCapability("appPackage", "com.popbeans.plant");
//        capabilities.setCapability("appActivity", ".MainActivity");
//        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
//    }
//
//    // Launch Activity
//    @Rule
//    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);
//
//    @Test
//    public void sunIncrementsToMax() {
//        MobileElement el1 = (MobileElement) driver.findElementById("com.popbeans.plant:id/btnSun");
//        el1.click();
//    }
////        for (int i = 0; i < 6; i++) {
////            onView(ViewMatchers.withId(R.id.btnSun))
////                    .perform(ViewActions.click());
////        }
////        onView(ViewMatchers.withId(R.id.statSunValCur)).check(ViewAssertions.matches(ViewMatchers.withText("5")));
////    }
////
////    @Test
////    public void waterIncrementsToMax() {
////        for (int i = 0; i < 6; i++) {
////            onView(ViewMatchers.withId(R.id.btnWater))
////                    .perform(ViewActions.click());
////        }
////        onView(ViewMatchers.withId(R.id.statWaterValCur)).check(ViewAssertions.matches(ViewMatchers.withText("5")));
////    }
////
////    @Test
////    public void loveIncrementsToMax() {
////        for (int i = 0; i < 6; i++) {
////            onView(ViewMatchers.withId(R.id.btnLove))
////                    .perform(ViewActions.click());
////        }
////        onView(ViewMatchers.withId(R.id.statLoveValCur)).check(ViewAssertions.matches(ViewMatchers.withText("5")));
////    }
//
//}
