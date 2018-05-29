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

import com.squareup.okhttp.*;
import okio.Buffer;
import okio.BufferedSource;

import javax.swing.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * <p>Interceptor for OKHttp that logs the request and response to the logcat.</p>
 */
public class OKHttpLoggingInterceptor implements Interceptor {
    public static final Charset UTF8 = Charset.forName("UTF-8");
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

        Buffer buffer1 = null;
        try {
            if (request.body() != null) {
                buffer1 = new Buffer();
                request.body().writeTo(buffer1);
                textArea.append( "Request body:  " + buffer1.readUtf8() + "\n");
            }
        } catch (Exception ignored) {
            // Do nothing
        } finally {
            if (buffer1 != null) {
                buffer1.close();
            }
        }

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
    }
}
