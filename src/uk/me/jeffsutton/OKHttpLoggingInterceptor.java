/*
 * Copyright (c) 2013-2015 Jeff Sutton.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package uk.me.jeffsutton;

import android.util.Log;

import com.squareup.okhttp.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okio.Buffer;
import okio.BufferedSource;

import javax.swing.*;

/**
 * <p>Interceptor for OKHttp that logs the request and response to the logcat.</p>
 */
public class OKHttpLoggingInterceptor implements Interceptor {
    public static final Charset UTF8 = Charset.forName("UTF-8");
    private static final String LOG_TAG = OKHttpLoggingInterceptor.class.getSimpleName();
    private JTextArea textArea;

    public OKHttpLoggingInterceptor(JTextArea textArea) {
        this.textArea = textArea;
    }

    /**
     *
     * @param chain
     * @return
     * @throws IOException
     */
    @Override
    public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        textArea.append("\n---------------------------------------------------\n");
        textArea.append("Request to:    [" + request.method() + "] " + request.urlString() + "\n");

        try {
            if (request.body() != null) {
                Buffer buffer = new Buffer();
                request.body().writeTo(buffer);
                textArea.append( "Request body:  " + buffer.readUtf8() + "\n");
            }
        } catch (Exception ignored) {}

//        Headers headers = request.headers();
//        Map<String, String> newHeads = new HashMap<String, String>();
//
//        for (Map.Entry<String, List<String>> entry : headers.toMultimap().entrySet()) {
//            if (entry.getKey().equalsIgnoreCase("Accept-Encoding") && entry.getValue().contains("gzip")) {
//
//            } else {
//                newHeads.put(entry.getKey(), entry.getValue().toString());
//                textArea.append("Request head:  " + entry.getKey()
//                        + ": " + entry.getValue() + "\n");
//            }
//        }
//
//        request = request.newBuilder().headers(Headers.of(newHeads)).build();

        long t1 = System.nanoTime();
        com.squareup.okhttp.Response response = chain.proceed(request);


        long t2 = System.nanoTime();

        ResponseBody responseBody = response.body();

        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.buffer();

        Charset charset = UTF8;
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
            charset = contentType.charset(UTF8);
        }

        String msg = buffer.clone().readString(charset);


        for (Map.Entry<String, List<String>> entry : response.request().headers().toMultimap().entrySet()) {

                textArea.append("Request head:  " + entry.getKey()
                        + ": " + entry.getValue() + "\n");

        }


        textArea.append( String.format("\n\nResponse from: %s in %.1fms%n",
                response.request().urlString(), (t2 - t1) / 1e6d) + "\n");
        textArea.append( "Response code: " + response.code() + " " + response.message() + "\n");

        Headers headers2 = response.headers();
        for (Map.Entry<String, List<String>> entry : headers2.toMultimap().entrySet()) {
            textArea.append( "Response head: " + entry.getKey()
                    + ": " + entry.getValue() + "\n");
        }

        textArea.append( "Response body: " + msg + "\n");
        return response;
//        return response.newBuilder()
//                .body(ResponseBody.create(response.body().contentType(), bo))
//                .build();
    }
}
