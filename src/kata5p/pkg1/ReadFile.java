/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kata5p.pkg1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carlo
 */
class ReadFile {

    BufferedReader br;
    public ReadFile(String fileName) throws FileNotFoundException {
        br = new BufferedReader(new FileReader(fileName));
        
    }
    
    public List<String> readAll() throws IOException{
        String line;
        List<String> list = new ArrayList<>();
        while( (line = br.readLine()) != null){
            if (line.contains("@")) list.add(getMail(line));
        }
        return list;
    }

    private String getMail(String email) {
        return email.substring(email.lastIndexOf("@")+1,email.length()-1);
    }
    
}
