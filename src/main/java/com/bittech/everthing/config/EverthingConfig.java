package com.bittech.everthing.config;

import jdk.nashorn.internal.objects.annotations.Getter;

import java.awt.*;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;

public class EverthingConfig {
    private static volatile EverthingConfig config;
    /**
     * 建立索引的路径
     */
    private Set<String> includePath = new HashSet<>();
    /**
     * 排除索引文件路径
     */
    private Set<String> excludePath = new HashSet<>();

    private EverthingConfig() {

    }

    public static EverthingConfig getInstance() {
        if (config == null) {
            synchronized (EverthingConfig.class) {
                if (config == null) {
                    config = new EverthingConfig();
                    //获取文件系统
                    FileSystem fileSystem = FileSystems.getDefault();
                    Iterable<Path> iterable = fileSystem.getRootDirectories();
                    iterable.forEach(path -> config.includePath.add(path.toString()));
                    //排除项目
                    String osname = System.getProperty("os.name");
                    if (osname.startsWith("Windows")) {
                        config.getExcludePath().add("C:\\Windows");
                        config.getExcludePath().add("C:\\Program Files(X86");
                        config.getExcludePath().add("C:\\Program Files");
                        config.getExcludePath().add("C:\\ProgramData");
                    } else {
                        config.getExcludePath().add("/tmp");
                        config.getExcludePath().add("/etd");
                        config.getExcludePath().add("/root");
                    }
                    config.initDefaultPathsConfig();

                }

            }

        }
        return config;

    }

    private void initDefaultPathsConfig() {
        config.getExcludePath().add("C:\\Windows");
        config.getExcludePath().add("C:\\Program Files(X86");
        config.getExcludePath().add("C:\\Program Files");
        config.getExcludePath().add("C:\\ProgramData");
        config.getExcludePath().add("/tmp");
        config.getExcludePath().add("/etd");
        config.getExcludePath().add("/root");
    }

    private Set<String> getExcludePath() {
        return excludePath;
    }

    public Set<String> getIncludePath() {
        return includePath;
    }
}

