package br.com.depro.fw.typezero.infrastructure.model.mail;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

public class AnexoExterno {

    private String name;
    private URL fileURL;
    private File file;
    private InputStream inputStream;
    

    public AnexoExterno() {
    }

    public AnexoExterno(String name, File file) {
        this.name = name;
        this.file = file;
    }

    public AnexoExterno(String name, InputStream inputStream) {
        this.name = name;
        this.inputStream = inputStream;
    }

    public AnexoExterno(String name, URL fileURL) {
        this.name = name;
        this.fileURL = fileURL;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setFileURL(URL fileURL) {
        this.fileURL = fileURL;
    }

    public URL getFileURL() {
        return fileURL;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
    
}
