package shane.common;

import java.util.Hashtable;
import java.util.Properties;

public class PropertiesManager {
	
	private static Hashtable propertiesCache;
	private static java.lang.Object loaderLock;
	static {
		loaderLock = new Object();
	}

	public PropertiesManager() {
		super();
	}

  public static java.util.Properties getProperties(String propertiesFileName)
                  throws java.io.FileNotFoundException {
    if (propertiesCache == null || propertiesCache.get(propertiesFileName) == null)
      synchronized (loaderLock)
      {

        if (propertiesCache == null|| propertiesCache.get(propertiesFileName) == null) {
          propertiesCache = new Hashtable();
          propertiesCache.put( propertiesFileName, new PropertiesLoader().getProperties(propertiesFileName));
        }
      }
    return (Properties) propertiesCache.get(propertiesFileName);
  }

  public static void reset() {
    propertiesCache = null;
  }
}
