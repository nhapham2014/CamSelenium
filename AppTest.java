package vn.camhomework;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class AppTest {
    @Test
    public void testFado() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://fado.vn/dang-nhap?redirect=https%3A%2F%2Ffado.vn%2F");
        driver.manage().window().maximize();
        //Verify hiển thị message "Vui lòng nhập dữ liệu" bên dưới các field bị rỗng khi login
        WebElement Loginbtn = driver.findElement(By.cssSelector("button[type=\"submit\"]"));
        Loginbtn.click();
        WebElement ErrorEmailtxt = driver.findElement(By.id("auth-block__form-group__email-error"));
        WebElement ErrorPasstxt = driver.findElement(By.id("password-error"));
        Assert.assertEquals("Vui lòng nhập dữ liệu", ErrorEmailtxt.getText());
        Assert.assertEquals("Vui lòng nhập dữ liệu", ErrorPasstxt.getText());
        //Verify các message lỗi ở #1 sẽ biến mất khi nhập các giá trị hợp lệ.
        WebElement Emailtxb = driver.findElement(By.name("email"));
        WebElement Passtxb = driver.findElement(By.name("password"));
        WebElement screen = driver.findElement(By.cssSelector("#auth-block__login-form"));
        Emailtxb.sendKeys("nhapham@gmail.com");
        Passtxb.sendKeys("123456");
        Actions actions = new Actions(driver);
        actions.click().build().perform();
        Assert.assertEquals("", ErrorEmailtxt.getText());
        Assert.assertEquals("", ErrorPasstxt.getText());
        //Verify message "Có lỗi xảy ra:\n- Mật khẩu không đúng, vui lòng kiểm tra lại" hiển thị
        Loginbtn.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement Errormsg = driver.findElement(By.cssSelector("div[class*=\"danger\"]"));
        Assert.assertEquals("Có lỗi xảy ra:\n" +
                "- Tài khoản không tồn tại, vui lòng kiểm tra lại", Errormsg.getText());


    }

}
