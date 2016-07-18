/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saveloadfunction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveLoadFunction {

   public static String[] getFileName(String path){
        String list[] = new File(path).list();
        return list;
    }
    
    public static File castFileFromObject(Object path) {
        File fpath;
        if (path instanceof java.lang.String) {
            fpath = new File((String)path);
        } else {
            if (path instanceof java.io.File) {
                fpath = (File) path;
            } else {
                fpath = null;
            }
        }
        return fpath;
    }
    
     public static void saveObjectToFile(Object path, Object value) throws Exception {
        File fpath = castFileFromObject(path);
        if (fpath == null) {
            throw new Exception("Invalid argument");
        }
        try (FileOutputStream fos = new FileOutputStream(fpath); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(value);
        }
    }
    
    public static Object loadObjectFromFile(Object path) 
            throws Exception {
        Object result = null;
        File fpath = castFileFromObject(path);
        if (fpath == null) {
            throw new Exception("Invalid argument");
        } else {
            try (FileInputStream fis = new FileInputStream(fpath)) {
                ObjectInputStream ois = new ObjectInputStream(fis);
                result = ois.readObject();
                ois.close();
            }
        }
        return result;
    }
}
