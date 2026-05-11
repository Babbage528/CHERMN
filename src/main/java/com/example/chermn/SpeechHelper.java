package com.example.chermn;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class SpeechHelper {

    private static final String VOICE_NAME = "kevin16";

    public static void speak(String text) {
        Voice voice = VoiceManager.getInstance().getVoice(VOICE_NAME);

        if (voice == null) {
            System.out.println("Voice not found");
            return;
        }

        voice.allocate();
        voice.speak(text);
        voice.deallocate();
    }
}