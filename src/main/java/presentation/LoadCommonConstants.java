package presentation;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

public class LoadCommonConstants {
	
private static final Logger LOGGER = Logger.getLogger(LoadCommonConstants.class.getName());
	
	public Map<String, String> getCommonConstant(List<String> commonConstantName) {
		Map<String, String> commonConstantsMap = new HashMap<>();
		try (InputStream resourceStream = this.getClass().getClassLoader().getResourceAsStream("screentitle.properties")){
			Properties prop = new Properties();
			prop.load(resourceStream);
			for(String commonConstant: commonConstantName)
				commonConstantsMap.put(commonConstant, prop.getProperty(commonConstant));
		}
		catch(IOException e) {
			
		}
		return commonConstantsMap;
	}
}
