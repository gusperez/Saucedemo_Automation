package TestInitializer;

import MainContext.FrameworkContext;

public class TextContext {
	
	public static String GetUrl()
	{
		return FrameworkContext.GetJsonString("WebUrl");
	}

}
