package io.foodust.makeRamen.lwjgl3;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import io.foodust.makeRamen.game.MakeRamen;
import io.foodust.makeRamen.object.ObjectManager;

import java.awt.*;

/**
 * Launches the desktop (LWJGL3) application.
 */
public class Lwjgl3Launcher {
    public static void main(String[] args) {
        if (StartupHelper.startNewJvmIfRequired()) return; // This handles macOS support and helps on Windows.
        createApplication();
    }

    private static Lwjgl3Application createApplication() {
        return new Lwjgl3Application(new MakeRamen(), getDefaultConfiguration());
    }

    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();

        // 모니터의 해상도 가져오기
        Graphics.DisplayMode primaryMode = Lwjgl3ApplicationConfiguration.getDisplayMode();

        // 창이 화면 중앙에 오도록 위치 계산
        int windowX = (primaryMode.width - ObjectManager.X.intValue()) / 2;
        int windowY = (primaryMode.height - ObjectManager.Y.intValue()) / 2;

        configuration.setTitle("우왁굳의 쌀국수 가게");
        configuration.useVsync(true);
        configuration.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate + 1);
        configuration.setWindowedMode(ObjectManager.X.intValue(),ObjectManager.Y.intValue());
        configuration.setWindowPosition(windowX, windowY +100);
        configuration.setWindowIcon("logo128.png", "logo64.png", "logo32.png", "logo16.png");
        configuration.setResizable(true);
        configuration.setDecorated(true);
        return configuration;
    }
}
