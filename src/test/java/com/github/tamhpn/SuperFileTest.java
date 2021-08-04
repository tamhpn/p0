package com.github.tamhpn;

import static org.junit.Assert.assertEquals;

import com.github.tamhpn.lib.SuperFile;

import org.junit.Test;

public class SuperFileTest {
    @Test
    public void createsSuperFile() {
        SuperFile file = new SuperFile(System.getProperty("user.home"));
        assertEquals(file.getAbsolutePath(), System.getProperty("user.home"));
    }
}
