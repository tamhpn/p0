package com.github.tamhpn;

import static org.junit.Assert.assertEquals;

import com.github.tamhpn.lib.SuperFile;
import com.github.tamhpn.util.SuperFiles;

import org.junit.Ignore;
import org.junit.Test;

public class SuperFilesTest {
    @Test
    public void changesDirectory() {
        SuperFile file = new SuperFile(System.getProperty("user.home"));
        file = SuperFiles.changeDirectory(file, "/");
        assertEquals(file.getAbsolutePath(), "/");
    }

    @Ignore("Unable to implement test at the moment")
    @Test
    public void createsFile() {

    }

    @Ignore("Unable to implement test at the moment")
    @Test
    public void createsFolder() {
        
    }

    @Ignore("Unable to implement test at the moment")
    @Test
    public void renamesFile() {
        
    }

    @Ignore("Unable to implement test at the moment")
    @Test
    public void movesFile() {
        
    }

    @Ignore("Unable to implement test at the moment")
    @Test
    public void copiesFile() {
        
    }

    @Ignore("Unable to implement test at the moment")
    @Test
    public void deletesFile() {
        
    }
}
