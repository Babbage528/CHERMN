package com.example.chermn;


public class SpeechHelper {

    public static void speak(String text) {
        if (text == null || text.isBlank()) return;

        String os = System.getProperty("os.name").toLowerCase();

        try {
            if (os.contains("win")) {
                speakWindows(text);
            } else if (os.contains("mac")) {
                speakMac(text);
            } else if (os.contains("nix") || os.contains("nux")) {
                speakLinux(text);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void speakWindows(String text) throws Exception {
        String safe = text.replace("'", "''");

        String command = "PowerShell -Command \"Add-Type –AssemblyName System.Speech;" +
                " $speak = New-Object System.Speech.Synthesis.SpeechSynthesizer;" +
                " $speak.Speak('" + safe + "');\"";

        new ProcessBuilder("cmd.exe", "/c", command).start();
    }

    private static void speakMac(String text) throws Exception {
        new ProcessBuilder("say", text).start();
    }

    private static void speakLinux(String text) throws Exception {
        new ProcessBuilder("espeak", text).start();
    }
}