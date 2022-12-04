package lib;

import com.sun.jna.platform.win32.Winspool;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class Platform {
    private static final String ANDROID_PLATFORM = "android";
    private static final String iOS_PLATFORM = "ios";
    private static final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";

    private static Platform instance;
    private Platform(){};

    public static Platform getInstance(){
        if(instance==null){
            instance=new Platform();
        }
        return instance;
    }

    public boolean isAndroid(){
        return isPlatform(ANDROID_PLATFORM);
    }

    public boolean isiOS(){
        return isPlatform(iOS_PLATFORM);
    }

    public AppiumDriver getDriver() throws Exception{
        URL url = new URL(APPIUM_URL);
        if(this.isAndroid()){
            return new AndroidDriver(url, getAndroidDesiredCapabilities());
        }else if(this.isiOS()){
            return new IOSDriver(url, getiOSDesiredCapabilities());
        }else{
            throw new Exception("Can not detect driver.Platfrom value: "+ this.getPlatformVar());
        }
    }

    private DesiredCapabilities getAndroidDesiredCapabilities(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:\\Users\\user\\Desktop\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");
        return capabilities;
    }

    private DesiredCapabilities getiOSDesiredCapabilities(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone SE");
        capabilities.setCapability("platformVersion", "11.3");
        capabilities.setCapability("app", "C:\\Users\\user\\Desktop\\JavaAppiumAutomation\\apks\\wikipedia.app");
        return capabilities;
    }

    private String getPlatformVar(){
        return System.getenv("PLATFORM");
    }

    private boolean isPlatform(String my_platform){
        String platform = this.getPlatformVar();
        return my_platform.equals(platform);
    }
}
