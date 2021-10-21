package bot.itemku;

import bot.itemku.pojo.ProductList.ProductList;
import bot.itemku.utility.Properties;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class ItemkuBot {

    private static final String ERROR_GENERAL = "GENERAL ERROR";
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ItemkuBot.class);

    private static OkHttpClient client = new OkHttpClient();
    private static ProductList productList;

    private static final Gson gson = new Gson();

    private static WebDriver driver;

    public static final String NUMERIC_TRUE = "1";

    //Fields
    public static final String URL_HOME_ITEMKU = "https://itemku.com/";
    private static final String URL_ITEMKU_API_V1 = "https://api-gateway.itemku.com/v1";
    private static final String URL_PRODUCT_NETFLIX_SORT_CHEAPEST = URL_ITEMKU_API_V1 + "/product?game_id=161&page=1&per_page=7&sort=cheap&has_item_info=1&is_auto_delivery_first=1&is_with_promotion=1";
    private static final String URL_PRODUCT_DETAIL = "https://itemku.com/dagangan";
    private static String productName = "";
    private static Integer price;
    private String MESSAGE_ERROR_NO_TRANSACTION;


    String message = "";
    boolean status = false;
    private String nickname = "";

    private Thread thread;
    private static boolean botIdleStatus = false;
    private static boolean botDriverRunningStatus = false;

    private static String transactionCode = "0";

    private Map<Boolean, String> transactionStatus = new HashMap<>();

    @Autowired
    public ItemkuBot() {

    }

    public Map<Boolean, String> startTransaction() {

        if (!botDriverRunningStatus) {
            setupBot();
            initiateBotConstantAction();
        } else {
            try {

                purchaseNetflixWithSelenium();
            } catch (Exception e) {
                e.printStackTrace();
                logger.info("Bot Error Message : " + e.getMessage());
                message = e.getMessage();
            }
        }
        updateResult();
        setBotDriverRunningStatus(true);
        setBotIdleStatus(true);
        resetTransactionStatus();

        return transactionStatus;
    }

    private void updateResult() {
        if (!transactionCode.equalsIgnoreCase("0")) {
            String successMessage = "Item :" + productName + " | Price :" + price + " | V/A:" + transactionCode;
            transactionStatus.put(true, successMessage);
        }
    }

    private static void setupBot() {
        try {
            initWebDriverAndStartBrowser();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Error Setup Bot " + e.getMessage());
        }
    }

    private static void processTransaction() {
        try {
            purchaseNetflixWithSelenium();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }
    }

    private static void initWebDriverAndStartBrowser() throws Exception {
        System.setProperty("webdriver.gecko.driver", Properties.geckodriverPath);

        ProfilesIni profileIni = new ProfilesIni();
        FirefoxProfile profile = profileIni.getProfile(Properties.botProfile);
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        options.setProfile(profile);

        driver = new FirefoxDriver(options);

        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        driver.get(URL_HOME_ITEMKU);
    }

    private static void purchaseNetflixWithSelenium() throws Exception {

        String urlBase = getProductDetailUrl();
        driver.get(urlBase);

        driver.findElement(By.id("sticky")).click();
        logger.info("Clicked 'Beli'");
        driver.findElement(By.cssSelector("#sticky > div:nth-child(1) > button > div > div > div")).click();
        logger.info("Clicked 'Langsung Beli'");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#sticky > div:nth-child(2)")));
        driver.findElement(By.cssSelector("#sticky > div:nth-child(2)")).click();
        logger.info("Clicked 'Bayar'");

        logger.info("Getting TransactionCode");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".items-baseline > .text-nero")));
        WebElement elementTransactionCode = driver.findElement(By.cssSelector(".items-baseline > .text-nero"));
        transactionCode = elementTransactionCode.getText();
        logger.info("V/A Number : " + transactionCode);
    }

    private static String getProductDetailUrl() throws Exception {
        logger.info("Getting Cheapest Netflix Product By Http Request");
        Request request = new Request.Builder()
                .url(URL_PRODUCT_NETFLIX_SORT_CHEAPEST)
                .build();

        logger.info("Sending Request to : " + URL_PRODUCT_NETFLIX_SORT_CHEAPEST);
        Response response = client.newCall(request).execute();
        String responseString = response.body().string();
        logger.info("Response : " + responseString);
        productList = gson.fromJson(responseString, ProductList.class);
        logger.info("Id : " + productList.getData().getData().get(0).getId());
        productName = productList.getData().getData().get(0).getName();
        logger.info("Name : " + productName);
        price = productList.getData().getData().get(0).getPrice();
        logger.info("Price : " + price);
        logger.info("Seo String : " + productList.getData().getData().get(1).getSeoString());


        return URL_PRODUCT_DETAIL + "/" + productList.getData().getData().get(1).getSeoString() + "/" + productList.getData().getData().get(1).getId();
    }

    private void doClickById(String locator) {
        printPerformedAction("Click", locator);
        driver.findElement(By.id(locator)).click();
    }

    private void doClickByCssSelector(String locator) throws Exception {
        printPerformedAction("Click", locator);
        driver.findElement(By.cssSelector(locator)).click();
    }

    private void printPerformedAction(String action, String element) {
        logger.info("[Action]:" + action + " | [Target]->" + element);
    }

    private void initiateBotConstantAction() {
        thread = new Thread("Bot Auto Clicks") {
            public void run() {
                while (true) {
                    System.out.println("Bot idle status is : " + botIdleStatus);
                    if (botIdleStatus) {
                        try {
                            driver.findElement(By.cssSelector("#sticky > a:nth-child(1)")).click();
                            logger.info("Clicked " + driver.findElement(By.cssSelector("#sticky > a:nth-child(1)")).getText());
                        } catch (Exception e) {
                            logger.info(e.getMessage());
                        }
                    }
                    ItemkuBot.sleep(Integer.parseInt(Properties.botRefreshInterval));
                }
            }
        };
        if (!thread.isAlive())
            thread.start();
    }

    public void setBotIdleStatus(boolean newStatus) {
        botIdleStatus = newStatus;
        System.out.println("Set Bot Idle Status to : " + newStatus);
    }

    public void setBotDriverRunningStatus(boolean newStatus) {
        botDriverRunningStatus = newStatus;
        System.out.println("Set Bot Driver Status to : " + newStatus);
    }

    private boolean isTransactionExist() {
        return MESSAGE_ERROR_NO_TRANSACTION == null;
    }

    private void resetTransactionStatus() {
        MESSAGE_ERROR_NO_TRANSACTION = null;
    }

    private static void sleep(Integer time) {
        try {
            Thread.sleep(time * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

