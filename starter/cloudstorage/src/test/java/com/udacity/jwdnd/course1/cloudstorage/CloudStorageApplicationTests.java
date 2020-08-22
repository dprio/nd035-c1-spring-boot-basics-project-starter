package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.page.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.page.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.page.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	private SignupPage signupPage;
	private LoginPage loginPage;
	private HomePage homePage;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		driver = new ChromeDriver();
		loginPage = new LoginPage(driver);
		signupPage = new SignupPage(driver);
		homePage = new HomePage(driver);
	}

	@AfterEach
	public  void afterEach() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void unauthorizedUserCanOnlySeeLoginAndSignup() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());

		driver.get("http://localhost:" + this.port);
		Assertions.assertEquals("Login", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
	}

	@Test
	public void loginAndLogoutVerifyingAllowedPages() throws InterruptedException {
		this.signup();
		this.login();
		TimeUnit.SECONDS.sleep(1L);

		Assertions.assertEquals("Home", driver.getTitle());

		homePage.clickLogoutButton();
		TimeUnit.SECONDS.sleep(1L);
		Assertions.assertEquals("Login", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/home");
		TimeUnit.SECONDS.sleep(1L);
		Assertions.assertEquals("Login", driver.getTitle());

	}

	@Test
	public void notesFlow() throws InterruptedException {
		this.signup();
		this.login();
		Assertions.assertEquals("Home", driver.getTitle());
		TimeUnit.SECONDS.sleep(1L);

		//####### CREATING NOTE #########
		homePage.clickNotesTab();
		TimeUnit.SECONDS.sleep(1L);

		homePage.clickAddNewNote();
		TimeUnit.SECONDS.sleep(1L);

		homePage.setNoteTitle("NoteTitle");
		homePage.setNoteDescription("NoteDescription");
		homePage.submitNotesModal();
		Assertions.assertEquals("Home", driver.getTitle());
		TimeUnit.SECONDS.sleep(1L);

		//####### VERIFYING NOTE CREATION #########
		homePage.clickNotesTab();
		TimeUnit.SECONDS.sleep(1L);

		String title = driver.findElement(
				By.xpath("//table/tbody/tr[1]/th")).getText();
		Assertions.assertEquals("NoteTitle", title);

		String description = driver.findElement(
				By.xpath("//table/tbody/tr[1]/td[2]")).getText();
		Assertions.assertEquals("NoteDescription", description);

		//####### EDITING CREATED NOTE #########
		driver.findElement(
				By.xpath("//table/tbody/tr[1]/td[1]/button")).click();
		TimeUnit.SECONDS.sleep(1L);

		homePage.setNoteTitle("NoteTitle2");
		homePage.submitNotesModal();
		Assertions.assertEquals("Home", driver.getTitle());
		TimeUnit.SECONDS.sleep(1L);

		//####### VERIFYING EDITION #########
		homePage.clickNotesTab();
		TimeUnit.SECONDS.sleep(1L);

		title = driver.findElement(
				By.xpath("//table/tbody/tr[1]/th")).getText();
		Assertions.assertEquals("NoteTitle2", title);

		description = driver.findElement(
				By.xpath("//table/tbody/tr[1]/td[2]")).getText();
		Assertions.assertEquals("NoteDescription", description);

		//####### DELETING NOTE #########
		driver.findElement(
				By.xpath("//table/tbody/tr[1]/td[1]/a")).click();
		TimeUnit.SECONDS.sleep(1L);

		//####### VERIFYING DELETION #########
		homePage.clickNotesTab();
		TimeUnit.SECONDS.sleep(1L);

		Assertions.assertThrows(NoSuchElementException.class,() -> driver.findElement(
				By.xpath("//table/tbody/tr")));


	}

	@Test
	public void credentialsFlow() throws InterruptedException {
		this.signup();
		this.login();
		Assertions.assertEquals("Home", driver.getTitle());
		TimeUnit.SECONDS.sleep(1L);

		//####### CREATING CREDENTIAL #########
		homePage.clickCredentialTab();
		TimeUnit.SECONDS.sleep(1L);

		homePage.clickAddNewCredential();
		TimeUnit.SECONDS.sleep(1L);

		homePage.setModalCredentialUrl("Url");
		homePage.setModalCredentialUsername("Username");
		homePage.setModalCredentialPassword("Password");
		homePage.submitCredentialsModal();
		Assertions.assertEquals("Home", driver.getTitle());
		TimeUnit.SECONDS.sleep(1L);

		//####### VERIFYING CREDENTIAL CREATION #########
		homePage.clickCredentialTab();
		TimeUnit.SECONDS.sleep(1L);

		String url = driver.findElement(
				By.xpath("//table/tbody/tr[1]/th")).getText();
		Assertions.assertEquals("Url", url);

		String username = driver.findElement(
				By.xpath("//table/tbody/tr[1]/td[2]")).getText();
		Assertions.assertEquals("Username", username);

		String password = driver.findElement(
				By.xpath("//table/tbody/tr[1]/td[3]")).getText();
		Assertions.assertNotEquals("Password", password);

		//####### EDITING CREATED CREDENTIAL #########
		driver.findElement(
				By.xpath("//table/tbody/tr[1]/td[1]/button")).click();
		TimeUnit.SECONDS.sleep(1L);

		homePage.setModalCredentialUrl("Url2");

		homePage.submitCredentialsModal();
		TimeUnit.SECONDS.sleep(1L);

		//####### VERIFYING EDITION #########
		homePage.clickCredentialTab();
		TimeUnit.SECONDS.sleep(1L);

		url = driver.findElement(
				By.xpath("//table/tbody/tr[1]/th")).getText();
		Assertions.assertEquals("Url2", url);

		username = driver.findElement(
				By.xpath("//table/tbody/tr[1]/td[2]")).getText();
		Assertions.assertEquals("Username", username);

		password = driver.findElement(
				By.xpath("//table/tbody/tr[1]/td[3]")).getText();
		Assertions.assertNotEquals("Password", password);

		//####### DELETING CREDENTIAL #########
		driver.findElement(
				By.xpath("//table/tbody/tr[1]/td[1]/a")).click();
		TimeUnit.SECONDS.sleep(1L);

		//####### VERIFYING DELETION #########
		homePage.clickCredentialTab();
		TimeUnit.SECONDS.sleep(1L);

		Assertions.assertThrows(NoSuchElementException.class,() -> driver.findElement(
				By.xpath("//table/tbody/tr")));

	}

	private void signup(){
		driver.get("http://localhost:" + this.port + "/signup");
		signupPage.setfirstName("FisrtName");
		signupPage.setLastNameName("LastName");
		signupPage.setUserName("userName");
		signupPage.setpassword("password");
		signupPage.submit();
	}

	private void login(){
		driver.get("http://localhost:" + this.port + "/login");
		loginPage.setUsername("userName");
		loginPage.setPassword("password");
		loginPage.submitLogin();
	}

}
