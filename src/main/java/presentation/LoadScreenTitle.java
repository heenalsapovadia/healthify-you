package presentation;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class LoadScreenTitle {
	
	private static final Logger LOGGER = Logger.getLogger(LoadScreenTitle.class.getName());
	
	public String getScreenTitle(String screenTitleName) {
		String screenTitle = null;
		try (InputStream resourceStream = this.getClass().getClassLoader().getResourceAsStream("screentitle.properties")){
			Properties prop = new Properties();
			prop.load(resourceStream);
			screenTitle = prop.getProperty(screenTitleName);
		}
		catch(IOException e) {
			
		}
		return screenTitle;
	}
}
