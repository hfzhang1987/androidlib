package com.pengpeng.android.client.manager;

import com.orhanobut.logger.Logger;
import com.pengpeng.android.client.api.TestApi;
import com.pengpeng.android.client.api.interceptor.CacheInterceptor;
import com.pengpeng.android.client.api.interceptor.HeaderInterceptor;
import com.pengpeng.android.client.api.server.BaseServer;
import com.pengpeng.android.client.api.server.TestServer;
import com.pengpeng.android.client.base.BaseAPP;
import com.pengpeng.android.client.base.Constants;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author zhanghongfei
 * @version V:2.0.0
 * @Title APIManager
 * @Package com.pengpeng.android.client.manager
 * @Description:
 * @date 2017/2/10 11:30
 */

public class APIManager {
    private Retrofit mTestRetrofit;

    private static APIManager mAPIManager = new APIManager();

    private static APIManager getInstance() {
        return mAPIManager;
    }

    private APIManager() {
        mTestRetrofit = createRetrofit(TestServer.getInstance());
    }
    private Retrofit createRetrofit(BaseServer server) {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .sslSocketFactory(getSSLSF())
                .readTimeout(Constants.HTTP_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(Constants.HTTP_CONNECTTIMEOUT,TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(new CacheInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(server.getDomain())
                .build();
        return retrofit;
    }

    public static <T>T create(Class<T> api){
        if (TestApi.class == api){
            return APIManager.getInstance().mTestRetrofit.create(api);
        }
        return null;
    }

    public static BaseServer getServer(Class api) {
        if (TestApi.class == api) {
            return TestServer.getInstance();
        } else {
            return null;
        }
    }


    public SSLSocketFactory getSSLSF() {
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            // uwca.crt 打包在 asset 中，
            // 该证书可以从https://itconnect.uw.edu/security/securing-computer/install/safari-os-x/下载
            InputStream caInput = new BufferedInputStream(BaseAPP.mBaseAPP.getAssets().open("coamc.crt"));
            Certificate ca;
            try {
                ca = cf.generateCertificate(caInput);
                Logger.e("ca=" + ((X509Certificate) ca).getSubjectDN());
                Logger.e("key=" + ((X509Certificate) ca).getPublicKey());
            } finally {
                caInput.close();
            }

            // Create a KeyStore containing our trusted CAs
            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);

            // Create a TrustManager that trusts the CAs in our KeyStore
            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);
            // Create an SSLContext that uses our TrustManager
            SSLContext context = SSLContext.getInstance("TLSv1", "AndroidOpenSSL");
            context.init(null, tmf.getTrustManagers(), null);
            return context.getSocketFactory();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;
    }
}
