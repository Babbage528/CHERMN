package com.example.chermn;

public class SpeechHelper {

    private static Process currentSpeechProcess;

    /**
     * Speaks the given text using the OS's built-in TTS engine.
     * Works on both Windows and macOS.
     */
    public static void speak(String text) {
        stop(); // stop any previous speech

        if (text == null || text.isBlank()) {
            return;
        }

        try {
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                speakWindows(text);
            } else if (os.contains("mac")) {
                speakMac(text);
            } else {
                System.out.println("TTS not supported on this OS.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops any currently running speech process.
     */
    public static void stop() {
        try {
            if (currentSpeechProcess != null && currentSpeechProcess.isAlive()) {
                currentSpeechProcess.destroyForcibly();
            }
        } catch (Exception ignored) {}
    }

    // PLATFORM IMPLEMENTATIONS - so it works for both windows and apple
    private static void speakWindows(String text) throws Exception {
        String safe = text.replace("'", "''");

        String command =
                "Add-Type –AssemblyName System.Speech;" +
                        "$s = New-Object System.Speech.Synthesis.SpeechSynthesizer;" +
                        "$s.Speak('" + safe + "');";

        ProcessBuilder pb = new ProcessBuilder(
                "powershell.exe",
                "-NoProfile",
                "-WindowStyle", "Hidden",
                "-Command", command
        );
        currentSpeechProcess = pb.start();
    }

    private static void speakMac(String text) throws Exception {
        ProcessBuilder pb = new ProcessBuilder("say", text);
        currentSpeechProcess = pb.start();
    }
}