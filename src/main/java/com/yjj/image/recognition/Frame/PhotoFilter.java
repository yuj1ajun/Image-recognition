package com.yjj.image.recognition.Frame;

import com.google.common.collect.Lists;

import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.util.List;

/**
 * @author yujiajun
 */
public class PhotoFilter extends FileFilter {

    private final List<String> suffix = Lists.newArrayList("BMP", "JPG", "JPEG", "PNG", "GIF", "PDF");

    @Override
    public boolean accept(File file) {

        if (file.isDirectory()) {

            return true;
        }

        String name = file.getName();

        String s = name.toUpperCase().substring(name.lastIndexOf(".") + 1, name.length());

        return suffix.contains(s);
    }

    @Override
    public String getDescription() {
        return null;
    }
}
