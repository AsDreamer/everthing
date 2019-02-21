package com.bittech.everthing.core.model;

import com.bittech.everthing.core.dao.FileIndexDao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 文件类型
 */

public enum  FileType {

   IMG("jpeg","png","jpe","gif"),

    Doc("ppt","pptx","doc","docx","pdf"),
    BInN("exe","sh","jar","msi"),//二进制类型
    ARCHIVE("zip","rar"),
    OTHER("*");
    /**
     * 对应的文件类型拓展名集合
     */
   private Set<String> extend = new HashSet<>();
   FileType(String...extend){
       this.extend.addAll(Arrays.asList(extend));
   }

    /**
     * 根据文件拓展名获取文件类型
     * @param extend
     * @return
     */
   public static FileType lookup(String extend){
       for(FileType fileType :FileType.values()){
           if(fileType.extend.contains(extend)){
               return fileType;
           }
       }
       return FileType.OTHER;

   }

    /**
     * 根据文件类型名获取文件类型
     * @param name
     * @return
     */
    public static FileType lookupByname(String name){
        for(FileType fileType :FileType.values()){
            if(fileType.name().equals(name)){
                return fileType;
            }
        }
        return FileType.OTHER;

    }

}
