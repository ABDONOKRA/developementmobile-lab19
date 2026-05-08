package com.ennoukra.gestionnairetaches;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

// Test instrumenté — s'exécute sur un appareil Android réel ou émulé
@RunWith(AndroidJUnit4.class)
public class TestInstrumente {

    @Test
    public void verifierNomPackage() {
        Context contexte = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.ennoukra.gestionnairetaches", contexte.getPackageName());
    }
}
