# PokéSolve

![Application video](https://media.giphy.com/media/MCXcBlkO5HGY61Mv5x/giphy.gif)

Practicing Android app development and testing!

Choose your starter Pokémon, give it a name, and then solve increasingly difficult math problems in order to evolve it!

<h2>Code, Challenges</h2>
<h3>Passing data between activities</h3>
Initially I was simply passing a string between the ChoiceActivity and the MainActivity, where it would hit a switch statement to select which Pokemon object to create.
However after refactoring the Pokemon class, this approach would've undone a lot of my good work. I found Intent cannot handle objects unless they were serialised, so I implemented it for both the Pokemon and the PokemonStatistics classes.

<h3>Animated PNGs for Sprites</h3>
penfeizhou's APNG library was amazing for this, and it's my first time using a 3rd party library. So easy!
This added a lot more character to what was essentially a static screen. I also implemented an ImageView ontop of the sprite to animate effects when the Pokemon evolved.

<h3>Automated Testing</h3>
I've been using Appium and TestNG for automated testing of this project on Amazon Device Farm. This was honestly a nightmare at the start, as dealing with movement between Activities was really difficult. I especially faced challenges with the Alert Modal Dialog pop-ups. After implementing WebDriverWait and the shamefully bruteforce sleep(), I overcame a lot of the missing element errors.
It's also really fun watching the automation suite solve the math challenges, and absolutely puts me to shame. Snapshot of the code is at the bottom of this readme.

<h2>To-do</h2>

- [x] Refactor all View IDs
- [x] Implement polymorphism (removing a large number of switch statements), generify classes and refactor methods
- [ ] Remove the majority of the hard sleeps implemented in the automation script
- [ ] Add multiplication and division math challenges (will have to change from int to handle this)
- [ ] Learn and understand how to use the Android Activity Lifecycle

![Application image](screenshot_1.png)
![Application image](screenshot_2.png)
![Application image](screenshot_3.png)

```
package tests;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ApplicationTestSuite {
    private AndroidDriver driver;
    private WebDriverWait wait;
    private List<String> buttonAccessibilityIdList = Arrays.asList(
            "FireButton",
            "GrassButton",
            "WaterButton",
            "DragonButton",
            "ElectricButton",
            "SteelButton",
            "PsychicButton",
            "GhostButton");
    private List<String> buttonIdList = Arrays.asList(
            "com.popbeans.plant:id/button_select_fire",
            "com.popbeans.plant:id/button_select_grass",
            "com.popbeans.plant:id/button_select_water",
            "com.popbeans.plant:id/button_select_dragon",
            "com.popbeans.plant:id/button_select_electric",
            "com.popbeans.plant:id/button_select_steel",
            "com.popbeans.plant:id/button_select_psychic",
            "com.popbeans.plant:id/button_select_ghost");

    @BeforeSuite
    public void setUp() throws IOException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appActivity", ".ChoiceActivity");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("app", "C:/Users/Dominic McRae/Projects/plant/app/build/outputs/apk/debug/app-debug.apk");

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);

        wait = new WebDriverWait(driver, 10);
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
    }

    @Test (priority = 1)
    public void assertPresenceOfChoices() {
        sleep(2000);
        for (String s : buttonIdList) {
            driver.findElement(By.id(s));
        }
    }

    @Test (priority = 2)
    public void validateChoices() {
        for (String s : buttonIdList) {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id(s))).click();
            sleep(500);
            driver.switchTo().alert();
            MobileElement submitButton = (MobileElement) driver.findElementById("android:id/button1");
            submitButton.click();
            sleep(300);
            driver.navigate().back();
        }

    }

    @Test (priority = 3)
    public void endToEnd() {
        // Choose Starter Pokemon Randomly
        MobileElement randomChoiceButton = (MobileElement) driver.findElementByAccessibilityId(buttonAccessibilityIdList.get(new Random().nextInt(buttonAccessibilityIdList.size())));
        randomChoiceButton.click();

        // Switch Context to the Modal Dialog
        sleep(500);
        driver.switchTo().alert();

        // Set Pokemon Nickname
        MobileElement nameInputField = (MobileElement) driver.findElementByAccessibilityId("NameInputField");
        nameInputField.sendKeys("Appium Test");
        MobileElement submitButton = (MobileElement) driver.findElementById("android:id/button1");
        submitButton.click();

        // Wait Up Bro
        sleep(1000);

        for (int n = 0; n < 3; n++) {
            // Get Current and Maximum Values, Buttons
            // Sun Elements
            sleep(1000);
            MobileElement valueSunCurrent = (MobileElement) driver.findElementByAccessibilityId("ValueSunCurrent");
            int i = Integer.parseInt(valueSunCurrent.getText());
            MobileElement valueSunMaximum = (MobileElement) driver.findElementByAccessibilityId("ValueSunMaximum");
            int ii = Integer.parseInt(valueSunMaximum.getText());

            // Water Elements
            MobileElement valueWaterCurrent = (MobileElement) driver.findElementByAccessibilityId("ValueWaterCurrent");
            int j = Integer.parseInt(valueWaterCurrent.getText());
            MobileElement valueWaterMaximum = (MobileElement) driver.findElementByAccessibilityId("ValueWaterMaximum");
            int jj = Integer.parseInt(valueWaterMaximum.getText());

            //Love Elements
            MobileElement valueLoveCurrent = (MobileElement) driver.findElementByAccessibilityId("ValueLoveCurrent");
            int k = Integer.parseInt(valueLoveCurrent.getText());
            MobileElement valueLoveMaximum = (MobileElement) driver.findElementByAccessibilityId("ValueLoveMaximum");
            int kk = Integer.parseInt(valueLoveMaximum.getText());

            // Loop Through Tasks For Each Value
            // Set Initial Values
            int constant;
            String operator;
            int variable;
            int sum;

            // Sun Elements
            while (i != ii) {
                sleep(500);
                MobileElement sunButton = (MobileElement) driver.findElementByAccessibilityId("SunButton");
                sunButton.click();
                sleep(500);
                driver.switchTo().alert();
                constant = Integer.parseInt(driver.findElementByAccessibilityId("ConstantField").getText());
                operator = driver.findElementByAccessibilityId("OperatorField").getText();
                sum = Integer.parseInt(driver.findElementByAccessibilityId("SumField").getText());
                variable = mathSolver(constant, operator, sum);
                MobileElement variableField = (MobileElement) driver.findElementByAccessibilityId("VariableField");
                variableField.sendKeys(String.valueOf(variable));
                MobileElement solveButton = (MobileElement) driver.findElementById("android:id/button1");
                solveButton.click();
                i++;
            }

            sleep(500);

            // Water Elements
            while (j != jj) {
                sleep(500);
                MobileElement waterButton = (MobileElement) driver.findElementByAccessibilityId("WaterButton");
                waterButton.click();
                sleep(500);
                driver.switchTo().alert();
                constant = Integer.parseInt(driver.findElementByAccessibilityId("ConstantField").getText());
                operator = driver.findElementByAccessibilityId("OperatorField").getText();
                sum = Integer.parseInt(driver.findElementByAccessibilityId("SumField").getText());
                variable = mathSolver(constant, operator, sum);
                MobileElement variableField = (MobileElement) driver.findElementByAccessibilityId("VariableField");
                variableField.sendKeys(String.valueOf(variable));
                MobileElement solveButton = (MobileElement) driver.findElementById("android:id/button1");
                solveButton.click();
                j++;
            }

            sleep(500);

            // Love Elements
            while (k != kk) {
                sleep(500);
                MobileElement loveButton = (MobileElement) driver.findElementByAccessibilityId("LoveButton");
                loveButton.click();
                sleep(500);
                driver.switchTo().alert();
                constant = Integer.parseInt(driver.findElementByAccessibilityId("ConstantField").getText());
                operator = driver.findElementByAccessibilityId("OperatorField").getText();
                sum = Integer.parseInt(driver.findElementByAccessibilityId("SumField").getText());
                variable = mathSolver(constant, operator, sum);
                MobileElement variableField = (MobileElement) driver.findElementByAccessibilityId("VariableField");
                variableField.sendKeys(String.valueOf(variable));
                MobileElement solveButton = (MobileElement) driver.findElementById("android:id/button1");
                solveButton.click();
                k++;
            }

            sleep(500);

            if (n < 2) {
                MobileElement evolveButton = (MobileElement) driver.findElementByAccessibilityId("EvolveButton");
                evolveButton.click();
            }

            // Let me take a nice lingering look at the end
            sleep(5000);

        }

    }

    private int mathSolver(int constant, String operator, int sum) {
        return Math.abs(constant - sum);
    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
```
