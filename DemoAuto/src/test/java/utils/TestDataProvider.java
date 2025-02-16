package utils;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

	@DataProvider(name="logindata")
	public Object[][] loginMethodData(){
		return new Object[][] 
				{
			{"standard_user" ,"secret_sauce"},
			{"abc","123"}
				};

	}

}
