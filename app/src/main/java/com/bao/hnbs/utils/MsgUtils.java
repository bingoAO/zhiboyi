package com.bao.hnbs.utils;


import android.util.Base64;

import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HttpContext;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

/**
 * @author dan.shan
 * @since 2013-08-12 3:23 PM
 */
public class MsgUtils {

//    public static void main(String[] args) throws Exception {
//
//        SmsUtils smsUtils = new SmsUtils();
//        smsUtils.testStatus();
//        smsUtils.testSend();
//    }

    public static String testSend(String mobile,String msg) throws Exception {

        DefaultHttpClient client = new DefaultHttpClient();

        client.addRequestInterceptor(new HttpRequestInterceptor() {
            @Override
            public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
                request.addHeader("Accept-Encoding", "gzip");
                request.addHeader("Authorization", "Basic " + Base64.encodeToString("api:key-9bd46d8b7e8bf173f7b0d54832658622".getBytes("utf-8"), Base64.DEFAULT));
            }
        });

        client.getParams().setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 30000);
        client.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT, 30000);

        HttpPost request = new HttpPost("https://sms-api.luosimao.com/v1/send.json");

        ByteArrayOutputStream bos = null;
        InputStream bis = null;
        byte[] buf = new byte[10240];

        String content = null;
        try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("mobile", mobile));
            params.add(new BasicNameValuePair("message", msg));
            request.setEntity(new UrlEncodedFormEntity(params, "utf-8"));


            HttpResponse response = client.execute(request);

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                bis = response.getEntity().getContent();
                Header[] gzip = response.getHeaders("Content-Encoding");

                bos = new ByteArrayOutputStream();
                int count;
                while ((count = bis.read(buf)) != -1) {
                    bos.write(buf, 0, count);
                }
                bis.close();

                if (gzip.length > 0 && gzip[0].getValue().equalsIgnoreCase("gzip")) {
                    GZIPInputStream gzin = new GZIPInputStream(new ByteArrayInputStream(bos.toByteArray()));
                    StringBuffer sb = new StringBuffer();
                    int size;
                    while ((size = gzin.read(buf)) != -1) {
                        sb.append(new String(buf, 0, size, "utf-8"));
                    }
                    gzin.close();
                    bos.close();

                    content = sb.toString();
                } else {
                    content = bos.toString();
                }

                LogUtils.i("content = " + content);
            } else {
                LogUtils.i("error code is " + response.getStatusLine().getStatusCode());
            }
            return content;

        } finally {
            if (bis != null) {
                try {
                    bis.close();// 最后要关闭BufferedReader
                } catch (Exception e) {
                }
            }
        }
    }

    public static String testStatus() throws Exception {

        DefaultHttpClient client = new DefaultHttpClient();

        client.addRequestInterceptor(new HttpRequestInterceptor() {
            @Override
            public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
                request.addHeader("Accept-Encoding", "gzip");
                request.addHeader("Authorization", "Basic " + Base64.encodeToString("api:key-9bd46d8b7e8bf173f7b0d54832658622".getBytes("utf-8"), Base64.DEFAULT));
            }
        });

        client.getParams().setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 30000);
        client.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT, 30000);

        HttpGet request = new HttpGet("https://sms-api.luosimao.com/v1/status.json");

        ByteArrayOutputStream bos = null;
        InputStream bis = null;
        byte[] buf = new byte[10240];

        String content = null;
        try {
            HttpResponse response = client.execute(request);

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                bis = response.getEntity().getContent();
                Header[] gzip = response.getHeaders("Content-Encoding");

                bos = new ByteArrayOutputStream();
                int count;
                while ((count = bis.read(buf)) != -1) {
                    bos.write(buf, 0, count);
                }
                bis.close();

                if (gzip.length > 0 && gzip[0].getValue().equalsIgnoreCase("gzip")) {
                    GZIPInputStream gzin = new GZIPInputStream(new ByteArrayInputStream(bos.toByteArray()));
                    StringBuffer sb = new StringBuffer();
                    int size;
                    while ((size = gzin.read(buf)) != -1) {
                        sb.append(new String(buf, 0, size, "utf-8"));
                    }
                    gzin.close();
                    bos.close();

                    content = sb.toString();
                } else {
                    content = bos.toString();
                }

                LogUtils.i(content);
            } else {
                LogUtils.i("error code is " + response.getStatusLine().getStatusCode());
            }
            return content;

        } finally {
            if (bis != null) {
                try {
                    bis.close();// 最后要关闭BufferedReader
                } catch (Exception e) {
                }
            }
        }
    }
}