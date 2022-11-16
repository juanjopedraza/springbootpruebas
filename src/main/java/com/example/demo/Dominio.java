package com.example.demo;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class Dominio {
    public Dominio() throws MalformedURLException {
        InetAddress addr=null;
        String hostnameCanonical=null;
        String hostname=null;
        byte[] address=null;

        final String currentURL =
                ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        URL requestURL = new URL(currentURL);

        try {
            addr = InetAddress.getByName(InetAddress.getLocalHost().getHostName());
            hostnameCanonical = addr.getCanonicalHostName();
            address = addr.getAddress();
            hostname = addr.getHostName();
        } catch (UnknownHostException e) {
            this.error="UnknownHostException";
        }
        this.url = currentURL;
        int x=url.indexOf('.');
        this.protocolo = url.substring(0,x);
        int y=url.indexOf('.');
        this.dominio = url.substring(x,y);
        this.puerto= 80;
        this.tld= ".es.com";
    }

    String protocolo;
    String dominio;
    String tld;
    int puerto;
    String addr;
    String canonnical;
    String hostname;
    String url;
    String path;
    String error;
    String query;

    public String getError() {
        return error;
    }

    public String getAddr() {
        return addr;
    }

    public String getCanonnical() {
        return canonnical;
    }

    public String getHostname() {
        return hostname;
    }

    public String getUrl() {
        return url;
    }

    public String getPath() {
        return path;
    }

    public String getQuery() {
        return query;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public String getDominio() {
        return dominio;
    }

    public String getTld() {
        return tld;
    }

    public String getPuerto() {
        return puerto;
    }

}
