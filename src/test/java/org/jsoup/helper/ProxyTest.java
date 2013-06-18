package org.jsoup.helper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * @author: danielpo
 * Date: 6/18/13
 * Time: 4:47 PM
 */
public class ProxyTest {
    public static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_2) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11";
    public static final String URL="http://whatismyipaddress.com/";
    public static final String GOOGLE_URL = "https://www.google.com";
    public Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress("63.141.249.37",3128));
    @Test
    public void testProxySupport() throws IOException {
        Document docWithoutProxy = Jsoup.connect(URL).userAgent(USER_AGENT).timeout(10000).get();
        String ip = docWithoutProxy.select("h2[style=margin:0px;padding-bottom:10px;]").select("span").first().text();
        System.out.println("Initial IP : "+ip);
        //get using proxy

        Document doc = Jsoup.connect(URL).userAgent(USER_AGENT).timeout(10000).withProxy(proxy).get();
        String ipProxy = doc.select("h2[style=margin:0px;padding-bottom:10px;]").select("span").first().text();
        System.out.println("Proxy IP : "+ipProxy);
      //  Assert.assertTrue(ip.equals("") && ipProxy.equals("") && !ip.equals(ipProxy));
    }

    @Test
    public void testGoogleProxy() throws IOException {
        Document doc = Jsoup.connect(GOOGLE_URL).userAgent(USER_AGENT).timeout(10000).withProxy(proxy).get();
        String searchButton = doc.select("span[id=gbqfsa]").first().text();
        System.out.println(searchButton);
       // Assert.assertTrue(searchButton.contains("Google"));
    }
}
