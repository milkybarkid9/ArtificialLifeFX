/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artificiallifefx;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 *
 * @author James
 */
public class FileMgr {
    protected int counter;
    
    FileMgr(){
        counter = 0;
    }
    
    public int getSaveCount(){
        return counter;
    }
    
    public void save(String name, String config){
        try {
            counter++;
            Files.write(Paths.get("world_configs.txt"), (counter+") "+name+" - "+config+System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
            System.out.println("Saved");            
        }catch (IOException e) {
            System.out.println("Saving failed");
        }
    }
    
    public String open(){
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        
        try {
            br = new BufferedReader(new FileReader("world_configs.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                if (sb.length() > 0) {
                    sb.append("\n");
                }
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        String contents = sb.toString();
        return contents;
    }
}
