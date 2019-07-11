package com.shanghai.logistics.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 项目名称：KangJianMedical
 * 包名：com.sunking.medical.base.utils
 * 描述：图片文件操作工具箱
 * 作者： ITBOY
 * 日期： 2018/1/3
 * 时间： 18:13
 */

public class ImageUtil {

    //图片文件路径转Bitmap
    public static Bitmap filePathToBitmap(String filePath) {
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        return bitmap;
    }

    //

    /**
     * 大图片文件路径转Bitmap
     * @param filePath  图片路径
     * @param inSampleSize  缩小的倍数
     * @return
     */
    public static Bitmap filePathToBitmap(String filePath,int inSampleSize) {
        Bitmap bitmap = BitmapFactory.decodeFile(filePath,getBitmapOption(inSampleSize));
        return bitmap;
    }


    /**
     * 获取Bitmap工厂选项
     * @param inSampleSize Bitmap 缩小的倍数
     * @return
     */
    public static BitmapFactory.Options getBitmapOption(int inSampleSize) {
        System.gc();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPurgeable = true;
        options.inSampleSize = inSampleSize;
        return options;
    }


    /**
     * @Description 质量压缩方法
     * @author XiongJie
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int length = baos.toByteArray().length / 1024;
        if (length > 400)
            image.compress(Bitmap.CompressFormat.JPEG, 60, baos);
        else if (length > 300){
            image.compress(Bitmap.CompressFormat.JPEG, 70, baos);
        }else if (length>200){
            image.compress(Bitmap.CompressFormat.JPEG, 80, baos);
        }else {
            image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        }
//        // 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
//        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        int options = 100;
//
//        // 循环判断如果压缩后图片是否大于200kb,大于继续压缩
//        while (baos.toByteArray().length / 1024 > 150) {
//            // 重置baos即清空baos
//            baos.reset();
//            // 这里压缩options%，把压缩后的数据存放到baos中
//            image.compress(Bitmap.CompressFormat.JPEG, options, baos);
//            // 每次都减少10
//            options -= 10;
//        }
        // 把压缩后的数据baos存放到ByteArrayInputStream中
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        // 把ByteArrayInputStream数据生成图片
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
        try {
            isBm.close();
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }



    /**
     * Bitmap对象保存为图片文件
     * @param bitmap
     * @param pathName 指定图片保存路径
     */
    public static File saveBitmapFile(Bitmap bitmap, String pathName) {
        File file = new File(pathName);//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }



}
