package com.riverstone.unknown303.mrnom.util;

import android.util.Log;

import com.riverstone.unknown303.framework.components.base.FileIO;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Settings {
    public static boolean soundEnabled = true;
    public static int highScore = 0;

    public static void load(FileIO files) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(
                    files.readFile(".mrnom")));
            soundEnabled = Boolean.parseBoolean(in.readLine());
            highScore = Integer.parseInt(in.readLine());
        } catch (IOException e) {
            Log.w(e.getClass().getName(), "Failed to read file '.mrnom'. Reverting to Defaults.");
            Log.v(e.getClass().getName(), e.getMessage(), e);
        } catch (NumberFormatException e) {
            Log.e(e.getClass().getName(), "Failed to read highscore: Line included non-numerical characters. Reverting to Defaults.");
            Log.v(e.getClass().getName(), e.getMessage(), e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                Log.e(e.getClass().getName(), "Failed to close BufferedReader 'in'");
                Log.v(e.getClass().getName(), e.getMessage(), e);
            }
        }
    }

    public static void save(FileIO files) {
        Log.i(null, "Saving settings. Do not exit!");
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    files.writeFile(".mrnom")));
            out.write(Boolean.toString(soundEnabled));
            out.write("\n");
            out.write("\n");
            out.write(Integer.toString(highScore));
            out.write("\n");
        } catch (IOException e) {
            Log.e(e.getClass().getName(), "Failed to write file '.mrnom'");
            Log.v(e.getClass().getName(), e.getMessage(), e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                Log.e(e.getClass().getName(), "Failed to close BufferedWriter 'out'");
                Log.v(e.getClass().getName(), e.getMessage(), e);
            }
        }
    }

    public static void checkAndAddScore(int score, FileIO files) {
        if (score > highScore) {
            highScore = score;
        }
        save(files);
    }
}
