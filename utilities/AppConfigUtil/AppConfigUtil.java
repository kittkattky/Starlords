package utilities.AppConfigUtil;

/**
 * AppConfigUtil public class for extracting external properties.
 * Authors: Preston Williamson
 * Last Updated Date: 05-MAY-2020
 */

import java.io.FileInputStream;
import java.util.Properties;

public class AppConfigUtil {
    private final Properties configFile = new java.util.Properties();
    private final String srcDirectoryPath = System.getProperty("user.dir") + "\\src\\utilities\\AppConfigUtil\\";
    
    public AppConfigUtil(String _filePath)
    {
        //append project source directory if path is not absolute.
        if (!_filePath.contains("C:\\"))
            _filePath = this.srcDirectoryPath + _filePath;
        
        try {
            //capture and load file stream into the Properties collection.
            FileInputStream input = new FileInputStream (_filePath);
            this.configFile.load(input);
            input.close();
        }catch(Exception eta){
            //print exception stack trace.
            eta.printStackTrace();
        }
    }

    public String getProperty(String _key)
    {        
        //return property string value if present; else, return null.
        boolean propertyExists = this.configFile.containsKey(_key);
        return (propertyExists) ? this.configFile.getProperty(_key) : null;
    }
}
