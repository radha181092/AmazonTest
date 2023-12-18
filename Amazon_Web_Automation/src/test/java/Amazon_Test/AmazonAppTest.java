package Amazon_Test;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Amazon_TestHarness.Amazon;


@Test(singleThreaded=true)
public class AmazonAppTest
{	
	Amazon AmazonFun = new Amazon();

	
	@BeforeTest
	public void testOpenURL()
	{	
		try
		{
			AmazonFun.Open_URL();
		}
		catch(Exception e)
		{
			e.getMessage();
		}
	}
	
	@Test(priority = 1)
	public void testNavigateProduct()
	{	
		try
		{
			AmazonFun.navigateToProduct();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@Test (priority = 2)
	public void testNavigateToProductDetailPage()
	{	
		try
		{
			AmazonFun.navigateToProductDetailPage();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@Test (priority = 3)
	public void testAddToCartProduct()
	{	
		try
		{
			AmazonFun.addToCartProduct();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@AfterTest
	public void testQuit()
	{	
		try
		{
			AmazonFun.CloseBrowser();
		}
		catch(Exception e)
		{
			e.getMessage();
		}
	}	
}
