package com.shanghai.logistics.util;

import android.util.Log;

import com.hjq.toast.ToastUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/*
 * 文件上传
 * */
public class FileUploadUtil {

    public static Map uploadInfo(String fileName, Map<String, RequestBody> map, File file) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        map.put(fileName + "\";filename=\"" + file.getName(), requestFile);
        return map;

    }

    public static Map uploadInfo(String[] fileName, Map<String, RequestBody> map, File[] file) {
        RequestBody requestFile;

        for (int i = 0; i < file.length; i++) {
            if (file[i] != null) {
                requestFile = RequestBody.create(MediaType.parse("application/octet-stream"), file[i]);
                map.put(fileName[i] + "\";filename=\"" + file[i].getName(), requestFile);
            }


        }


//        MultipartBody.Part IDCardImgFront = MultipartBody.Part.createFormData("IDCardImgFront", nativeFile.getName(), requestFile);
//        MultipartBody.Part IDCardImgReverse = MultipartBody.Part.createFormData("IDCardImgReverse", nativeFile.getName(), requestFile);

        return map;
    }

    public static Map uploadInfo(String fileName, Map<String, RequestBody> map, List<File> file) {
        RequestBody requestFile;

        for (int i = 0; i < file.size(); i++) {
            requestFile = RequestBody.create(MediaType.parse("application/octet-stream"), file.get(i));
            map.put(fileName + "\";filename=\"" + file.get(i).getName(), requestFile);

        }


//        MultipartBody.Part IDCardImgFront = MultipartBody.Part.createFormData("IDCardImgFront", nativeFile.getName(), requestFile);
//        MultipartBody.Part IDCardImgReverse = MultipartBody.Part.createFormData("IDCardImgReverse", nativeFile.getName(), requestFile);

        return map;
    }

    public static Map uploadInfo(String[] fileName, Map<String, RequestBody> map, List<File> file) {
        RequestBody requestFile;

        for (int i = 0; i < file.size(); i++) {
            requestFile = RequestBody.create(MediaType.parse("application/octet-stream"), file.get(i));
            map.put(fileName[i] + "\";filename=\"" + file.get(i).getName(), requestFile);

        }


//        MultipartBody.Part IDCardImgFront = MultipartBody.Part.createFormData("IDCardImgFront", nativeFile.getName(), requestFile);
//        MultipartBody.Part IDCardImgReverse = MultipartBody.Part.createFormData("IDCardImgReverse", nativeFile.getName(), requestFile);

        return map;
    }

    public static RequestBody requestBody(String value) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), value);
        return requestBody;
    }

}
