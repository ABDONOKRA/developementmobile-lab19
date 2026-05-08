package com.ennoukra.gestionnairetaches;

import org.junit.Test;

import static org.junit.Assert.*;

// Test unitaire local — s'exécute sur la machine de développement
public class TestUnitaire {

    @Test
    public void additionEstCorrecte() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void chaineNonVideEstValide() {
        String libelle = "Préparer la réunion";
        assertFalse(libelle.trim().isEmpty());
    }

    @Test
    public void chaineVideEstRejetee() {
        String libelle = "   ";
        assertTrue(libelle.trim().isEmpty());
    }
}
