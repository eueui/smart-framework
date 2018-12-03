package com.sky.chapter2.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * properties file read
 */
public class PropsUtil {

    private static final Logger logger = LoggerFactory.getLogger(PropsUtil.class);

    /**
     * load properties
     * @param fileName
     * @return
     */
    public static Properties loadProps(String fileName){
        Properties properties = null;
        InputStream is = null;


        try{
            is = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(fileName);
            if(is == null){
                throw new FileNotFoundException(fileName + " file id not found...");
            }

            properties = new Properties();
            properties.load(is);
        }catch(IOException e){
            logger.error("load properties fail..." + e.getMessage(),e);
        }finally {
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error("close inputstream error..." + e.getMessage(),e);
                }
            }
        }

        return properties;
    }

    public static String getString(Properties properties,String key){
        return properties.getProperty(key);
    }
}
