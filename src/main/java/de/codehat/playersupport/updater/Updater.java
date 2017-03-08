/*
 * Copyright (c) 2017 CodeHat.
 * This file is part of 'PlayerSupport' and is licensed under GPLv3.
 */

package de.codehat.playersupport.updater;

import de.codehat.playersupport.util.HttpRequest;

/**
 * Updater
 *
 * @author CodeHat
 */

public class Updater implements Runnable {

    private static final String URL = "https://api.codehat.de/plugin/2";
    private String plugin_version = null;
    private UpdateCallback<UpdateResult, String> callback;

    /**
     * Constrcutor.
     *
     * @param current_version Current plugin version.
     */
    public Updater(String current_version, UpdateCallback<UpdateResult, String> callback) {
        this.plugin_version = current_version;
        this.callback = callback;
    }

    /**
     * Get the plugin download link.
     *
     * @return The plugin download link.
     */
    public static String getDownloadUrl() {
        return "https://www.spigotmc.org/resources/playersupport.11255/";
    }

    /**
     * Checks for Updates.
     *
     * @return The result of the Updater.
     */
    @Override
    public void run() {
        String version = null;
        try {
            version = HttpRequest.sendGet(URL);
        } catch (Exception e) {
            e.printStackTrace();
            callback.call(UpdateResult.COULD_NOT_CHECK, null);
        }
        if (!plugin_version.equals(version)) {
            callback.call(UpdateResult.NEEDED, version);
        } else {
            callback.call(UpdateResult.UNNEEDED, version);
        }
    }
}