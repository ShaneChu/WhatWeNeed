package shane.common;

import java.util.Enumeration;
import java.util.Properties;
import java.util.PropertyResourceBundle;


public class PropertiesLoader {

  private java.util.Properties properties;

  public PropertiesLoader() {
    super();
  }

   public java.util.Properties getProperties(String fileName)
              throws java.io.FileNotFoundException
      {
        if (properties == null)
        {
              java.util.PropertyResourceBundle resourceBundle = null;
              resourceBundle =(PropertyResourceBundle) PropertyResourceBundle.getBundle(fileName);
              Enumeration enu = resourceBundle.getKeys();
              properties = new Properties();
              
              while (enu.hasMoreElements()) {
                String propertyName = enu.nextElement().toString();
                properties.setProperty(propertyName, resourceBundle.getString(propertyName));
              }
            }
        
            return properties;
           }
}
