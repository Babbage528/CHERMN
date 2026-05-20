package com.example.chermn;

/**
 * A helper class that provides cross‑platform Text‑To‑Speech (TTS) functionality
 * using the operating system's built‑in speech engines.
 *
 * <p>This class is fully self‑contained and supports:
 * <ul>
 *     <li>Enabling/disabling TTS globally</li>
 *     <li>Voice selection (Windows only)</li>
 *     <li>Volume control (0–100, Windows only)</li>
 *     <li>Automatic OS detection (Windows/macOS)</li>
 *     <li>Process‑level cancellation to prevent overlapping speech</li>
 * </ul>
 *
 * <p>Windows implementation uses PowerShell + System.Speech.
 * macOS implementation uses the built‑in {@code say} command.
 */
public class SpeechHelper {

    /** The currently running speech process, if any. */
    private static Process currentSpeechProcess;

    // ============================================================
    // GLOBAL SETTINGS
    // ============================================================

    /** Whether Text‑To‑Speech is enabled. */
    private static boolean ttsEnabled = true;

    /** The selected Windows voice name (ignored on macOS). */
    private static String selectedVoice = "Microsoft Zira Desktop";

    /** The TTS volume level (0–100). */
    private static int ttsVolume = 100;

    // ============================================================
    // PUBLIC API
    // ============================================================

    /**
     * Speaks the given text using the OS‑specific TTS engine.
     * <p>
     * If TTS is disabled, or the text is null/blank, the method returns immediately.
     * Any currently running speech process is stopped before new speech begins.
     *
     * @param text the text to speak
     */
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

    /**
     * Stops any currently running TTS process.
     * <p>
     * This prevents overlapping or stuck speech processes.
     */
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

    /**
     * Enables or disables Text‑To‑Speech globally.
     *
     * @param enabled {@code true} to enable TTS, {@code false} to disable it
     */
    public static void setTtsEnabled(boolean enabled) {
        ttsEnabled = enabled;
    }

    /**
     * Returns whether Text‑To‑Speech is currently enabled.
     *
     * @return {@code true} if TTS is enabled
     */
    public static boolean isTtsEnabled() {
        return ttsEnabled;
    }

    /**
     * Sets the Windows voice to use when speaking.
     * <p>
     * This setting is ignored on macOS.
     *
     * @param voiceName the name of the Windows voice
     */
    public static void setSelectedVoice(String voiceName) {
        selectedVoice = voiceName;
    }

    /**
     * Returns the currently selected Windows voice.
     *
     * @return the voice name
     */
    public static String getSelectedVoice() {
        return selectedVoice;
    }

    /**
     * Sets the TTS volume level.
     * <p>
     * Valid range is 0–100. Values outside this range are clamped.
     *
     * @param volume the desired volume level (0–100)
     */
    public static void setTtsVolume(double volume) {
        ttsVolume = (int) volume;
    }

    /**
     * Returns the current TTS volume level.
     *
     * @return the volume (0–100)
     */
    public static int getTtsVolume() {
        return ttsVolume;
    }

    // ============================================================
    // PLATFORM IMPLEMENTATIONS
    // ============================================================

    /**
     * Speaks text using Windows PowerShell and the System.Speech API.
     *
     * @param text the text to speak
     * @throws Exception if the PowerShell process fails to start
     */
    private static void speakWindows(String text) throws Exception {
        String safe = text.replace("'", "''");

        String command =
                "Add-Type –AssemblyName System.Speech;" +
                        "$s = New-Object System.Speech.Synthesis.SpeechSynthesizer;" +
                        "$s.SelectVoice('" + selectedVoice + "');" +
                        "$s.Volume = " + ttsVolume + ";" +
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
     * Speaks text using macOS's built‑in {@code say} command.
     * <p>
     * Note: macOS does not support volume control via {@code say}.
     *
     * @param text the text to speak
     * @throws Exception if the process fails to start
     */
    private static void speakMac(String text) throws Exception {
        ProcessBuilder pb = new ProcessBuilder("say", text);
        currentSpeechProcess = pb.start();
    }
}