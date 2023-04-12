package com.levin.oak.base.utils;

public abstract class UrlPathUtils {

    public static String safeUrl(String url) {
        return safeUrl(url, true);
    }

    public static String safeUrl(String url, boolean isReplaceSpace) {

        while (url.contains("\\")) {
            url = url.replace("\\", "/");
        }

        while (url.contains("//")) {
            url = url.replace("//", "/");
        }

        if (isReplaceSpace) {
            //不允许空格
            while (url.contains(" ")) {
                url = url.replace(" ", "");
            }
        }

        return url;
    }
}
