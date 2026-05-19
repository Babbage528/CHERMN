package com.example.chermn;

/**
 * A helper class that provides Text-To-Speech (TTS) functionality
 * using the operating system's built-in speech engine.
 *
 * Fully self-contained:
 * - TTS enabled/disabled toggle
 * - Voice selection
 * - Volume control (0–100)
 */
public class SpeechHelper {

    private static Process currentSpeechProcess;

    // Global TTS settings
    private static boolean ttsEnabled = true;
    private static String selectedVoice = "Microsoft Zira Desktop";
    private static int ttsVolume = 100; // 0–100

    // ============================================================
    // PUBLIC API
    // ============================================================

    public static void speak(String text) {
        stop();

        if (!ttsEnabled) return;
        if (text == null || text.isBlank()) return;

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

    public static void stop() {
        try {
            if (currentSpeechProcess != null && currentSpeechProcess.isAlive()) {
                currentSpeechProcess.destroyForcibly();
            }
        } catch (Exception ignored) {}
    }

    // ============================================================
    // SETTINGS
    // ============================================================

    public static void setTtsEnabled(boolean enabled) {
        ttsEnabled = enabled;
    }

    public static boolean isTtsEnabled() {
        return ttsEnabled;
    }

    public static void setSelectedVoice(String voiceName) {
        selectedVoice = voiceName;
    }

    public static String getSelectedVoice() {
        return selectedVoice;
    }

    /** Sets TTS volume (0–100). */
    public static void setTtsVolume(double volume) {
        ttsVolume = (int) volume;
    }

    public static int getTtsVolume() {
        return ttsVolume;
    }

    // ============================================================
    // PLATFORM IMPLEMENTATIONS
    // ============================================================

    private static void speakWindows(String text) throws Exception {
        String safe = text.replace("'", "''");

        String command =
                "Add-Type –AssemblyName System.Speech;" +
                        "$s = New-Object System.Speech.Synthesis.SpeechSynthesizer;" +
                        "$s.SelectVoice('" + selectedVoice + "');" +
                        "$s.Volume = " + ttsVolume + ";" +   // <-- APPLY VOLUME HERE
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
        // macOS "say" does not support volume directly
        ProcessBuilder pb = new ProcessBuilder("say", text);
        currentSpeechProcess = pb.start();
    }

    /**
     * Private constructor to prevent instantiation of class.
     */
    private SpeechHelper() {
    }
}