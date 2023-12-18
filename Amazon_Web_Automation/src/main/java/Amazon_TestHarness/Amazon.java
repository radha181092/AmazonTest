package Amazon_TestHarness;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;


public class Amazon 
{
	//Variables and Objects
	WebDriver driver;

	//Excel file path
	public static final String CURRENT_DIR = System.getProperty("user.dir");
	public static final String TestDataExcel = CURRENT_DIR+"\\src\\test\\resources\\TestData.xls";

	//Open Browser and enter URL
	public void Open_URL()
	{
		String path = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver",path+"\\src\\main\\resources\\chromedriver.exe");        
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(GetDataFromCell(TestDataExcel, "Main", 0, 1));
	}

	//On Amazon Home Page search as iphone , first list appears , navigate to detail page
	public void navigateToProduct() throws InterruptedException
	{
		Thread.sleep(2000);
		String launchTitle = driver.getTitle();
		if (launchTitle.contains("Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in")) {	
			System.out.println("Amazon Title verified Successfully -- Passed");
		} else {
			System.out.println("Amazon Title not verified  -- Failed");
		}
		Thread.sleep(2000);
		//Search-iphone on search text box
		WebElement searchTxtbox = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", searchTxtbox);
		Thread.sleep(2000);
		if(driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).isDisplayed() == true)
		{
			System.out.println("Search Textbox is displayed!!!");
			searchTxtbox.sendKeys(GetDataFromCell(TestDataExcel, "Main", 1, 1));	
			System.out.println("Entered Text Successfully as 'iphone' !!!");
		}
		else
		{
			System.out.println("Search Textbox is not available!!!");
		}
		//Click on search icon
		WebElement searchImg = driver.findElement(By.xpath("//span[contains(@aria-label,'Go')]"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", searchImg);
		Thread.sleep(2000);
		if(searchImg.isDisplayed() == true)
		{
			searchImg.click();
			System.out.println("Search Img icon is displayed and Clicked successfully!!!");
		}
		else
		{
			System.out.println("Search Img icon is not available!!!");
		}
		Thread.sleep(3000);
		//First iphone from result list and get Text
		WebElement iphoneFirst = driver.findElement(By.xpath("((//div[@class='puisg-row'])[2]//preceding-sibling::div//span)[11]"));
		String iphoneGetText;
		if(iphoneFirst.isDisplayed() == true)
		{
			iphoneGetText = iphoneFirst.getText();
			System.out.println("Name of Iphone is : " +iphoneGetText);	
			System.out.println("iPhone Displayed and Text verified successfully!!!!");	
		}
		else
		{
			System.out.println("iPhone is not available!!!");
		}

		//On click iphone , new window open 
		WebElement iphoneFirstLnk = driver.findElement(By.xpath("((//div[@class='puisg-row'])[2]//preceding-sibling::div//span)[11]//parent::a"));
		if(iphoneFirstLnk.isDisplayed() == true)
		{
			iphoneFirstLnk.click();
			System.out.println("iPhone Link displayed and clicked Successfully !!!");
		}
		else
		{
			System.out.println("iPhone link is not available!!!");
		}
		//Swicth to new tab window - iphone detail page
		Thread.sleep(3000);
		//webDriver.switchTo().defaultContent();
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		String handleName = tabs.get(1);
		String defaultWindow = tabs.get(0);
		driver.switchTo().window(handleName);
		Thread.sleep(2000);
		System.out.println("Window Switched Successfully!!!");
	}

	//Product Detail Page
	public void navigateToProductDetailPage() throws InterruptedException
	{

		//Add to Cart
		Thread.sleep(2000);
		WebElement iphoneAddCart = driver.findElement(By.xpath("(//span[contains(., 'Add to Cart')])[7]"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", iphoneAddCart);
		if(iphoneAddCart.isDisplayed() == true)
		{   iphoneAddCart.click();
		Thread.sleep(2000);
		System.out.println("Add to Cart added Successfully !!!");
		}
		else
		{
			System.out.println("Add to Cart is not available!!!");
		}
		//Verify Iphone Amount
		WebElement iphoneAmount = driver.findElement(By.xpath("//div[@id='apex_desktop']//following::span[@class='a-price-whole']"));
		String iphoneAmountTxt;
		if(iphoneAmount.isDisplayed() == true)
		{
			iphoneAmountTxt = iphoneAmount.getText();
			System.out.println("Amount of Iphone is : " +iphoneAmountTxt);	
			System.out.println("iPhone amount Displayed and Text verified successfully!!!!");	
		}
		else
		{
			System.out.println("iPhone amount is not available!!!");
		}
		Thread.sleep(3000);	
	}

	//Add to cart
	public void addToCartProduct() throws InterruptedException
	{
		WebElement iphoneAddCart = driver.findElement(By.xpath("(//form[@id='addToCart'])[2]//div[36]"));
		Thread.sleep(2000);
		iphoneAddCart.click();
		if(iphoneAddCart.isDisplayed() == true)
		{   
			System.out.println("Add to Cart added Successfully !!!");
		}
		else
		{
			System.out.println("Add to Cart is not available!!!");
		}
	}


	//Close browser after test complete
	public void CloseBrowser()
	{
		driver.quit();
	}


	//Read data from excel 
	public String GetDataFromCell(String xlFilePath,String sheetName,int row,int coloumn) 
	{
		Cell tableStart=null;
		try
		{
			Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
			Sheet sheet = workbook.getSheet(sheetName);
			tableStart=sheet.getCell(coloumn,row);

		}catch(Exception e)
		{
		}
		return tableStart.getContents();
	}



}
