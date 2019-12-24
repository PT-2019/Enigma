package com.crashinvaders.texturepackergui;

import com.crashinvaders.common.Version;
import com.github.czyzby.autumn.mvc.component.i18n.LocaleService;

import java.io.File;
import java.util.Locale;

public class AppConstants {
    public static final Version version = new Version("4.8.2");
    public static final String APP_TITLE = "GDX Texture Packer";
    public static final String EXTERNAL_DIR = ".gdxtexturepackergui";
    public static final String MODULES_DIR = EXTERNAL_DIR + File.separator + "modules";
    public static final String LOGS_DIR = EXTERNAL_DIR + File.separator + "logs";
    public static final String PROJECT_FILE_EXT = "tpproj";
    public static final String[] IMAGE_FILE_EXT = new String[]{"png", "jpg", "jpeg"};

    public static final String PREF_NAME_COMMON = "common.xml";
    public static final String PREF_NAME_INSTALLED_MODULES = "installed_modules.xml";

    public static final String GITHUB_OWNER = "crashinvaders";
    public static final String GITHUB_REPO = "gdx-texture-packer-gui";

    public static final Locale LOCALE_EN = Locale.ENGLISH;
    public static final Locale LOCALE_DE = Locale.GERMAN;
    public static final Locale LOCALE_RU = LocaleService.toLocale("ru");
    public static final Locale LOCALE_ZH_TW = LocaleService.toLocale("zh_TW");
    public static final Locale LOCALE_DEFAULT = LOCALE_EN;

    public static File logFile;
}