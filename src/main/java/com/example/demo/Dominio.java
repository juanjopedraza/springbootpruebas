package com.example.demo;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class Dominio {
    public Dominio() throws MalformedURLException {

        final String currentURL = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        URL requestURL = new URL(currentURL);
        

        this.puerto = requestURL.getPort();
        this.host = requestURL.getHost();
        this.protocolo = requestURL.getProtocol();
        this.path = requestURL.getPath();
        this.query = requestURL.getQuery();

        /* información del servidor */
        InetAddress iaddr=null;
        String hostnameCanonical=null;
        String hostname=null;
        byte[] address=null;
        try {
            iaddr = InetAddress.getByName(InetAddress.getLocalHost().getHostName());
            this.addr = iaddr.toString();
            this.canonnical = iaddr.getCanonicalHostName();
            this.address = iaddr.getAddress();
            this.hostname = iaddr.getHostName();
        } catch (UnknownHostException e) {
            this.error="UnknownHostException";
        }
        this.url = currentURL;
        int y=url.indexOf('.');
        if (y==-1) {
            this.dominio= "sin-dominio";
            this.tld = "sin-tld";
        }
        else {
            this.dominio = url.substring(url.indexOf("://")+3,y);
            this.tld = url.substring(y+1).replace(":"+this.puerto,"");
        }
    }
    String host;
    String protocolo;
    String dominio;
    String tld;
    int puerto;
    String addr;

    byte[] address;
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
    public byte[] getAddress() {
        return address;
    }


    public String getCanonnical() {
        return canonnical;
    }

    public String getHostname() {
        return hostname;
    }

    public String getHost() {
        return host;
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

    public int getPuerto() {
        return puerto;
    }

}
