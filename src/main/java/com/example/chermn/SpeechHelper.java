package com.example.chermn;

/**
 * A helper class that provides Text-To-Speech (TTS) functionality
 * using the operating system's built-in speech engine.
 *
 * This version is fully self-contained and includes:
 * - A global toggle to enable/disable TTS
 * - A global selected voice for Windows
 * - Windows + macOS support
 */
public class SpeechHelper {

    /** Holds the currently running speech process so it can be stopped. */
    private static Process currentSpeechProcess;

    /** Global toggle for enabling/disabling TTS across the entire app. */
    private static boolean ttsEnabled = true;

    /** The selected Windows voice (ignored on macOS). */
    private static String selectedVoice = "Microsoft Zira Desktop";

    // ============================================================
    // PUBLIC API
    // ============================================================

    /**
     * Speaks the given text using the OS's built-in TTS engine.
     * Respects the global TTS enabled/disabled toggle.
     *
     * @param text The text to speak aloud.
     */
    public static void speak(String text) {
        stop(); // stop any previous speech

        if (!ttsEnabled) {
            return; // TTS disabled globally
        }

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

    // ============================================================
    // SETTINGS (used by SettingsController)
    // ============================================================

    /**
     * Enables or disables TTS globally.
     *
     * @param enabled true to enable TTS, false to disable it
     */
    public static void setTtsEnabled(boolean enabled) {
        ttsEnabled = enabled;
    }

    /**
     * @return true if TTS is enabled
     */
    public static boolean isTtsEnabled() {
        return ttsEnabled;
    }

    /**
     * Sets the Windows TTS voice.
     *
     * @param voiceName the name of the Windows voice to use
     */
    public static void setSelectedVoice(String voiceName) {
        selectedVoice = voiceName;
    }

    /**
     * @return the currently selected Windows voice
     */
    public static String getSelectedVoice() {
        return selectedVoice;
    }

    // ============================================================
    // PLATFORM IMPLEMENTATIONS
    // ============================================================

    /**
     * Uses Windows PowerShell + System.Speech to speak text.
     * Applies the selected voice.
     */
    private static void speakWindows(String text) throws Exception {
        String safe = text.replace("'", "''");

        String command =
                "Add-Type –AssemblyName System.Speech;" +
                        "$s = New-Object System.Speech.Synthesis.SpeechSynthesizer;" +
                        "$s.SelectVoice('" + selectedVoice + "');" +
                        "$s.Speak('" + safe + "');";

        ProcessBuilder pb = new ProcessBuilder(
                "powershell.exe",
                "-NoProfile",
                "-WindowStyle", "Hidden",
                "-Command", command
        );

        currentSpeechProcess = pb.start();
    }

    /**
     * Uses macOS "say" command for TTS.
     */
    private static void speakMac(String text) throws Exception {
        ProcessBuilder pb = new ProcessBuilder("say", text);
        currentSpeechProcess = pb.start();
    }
}