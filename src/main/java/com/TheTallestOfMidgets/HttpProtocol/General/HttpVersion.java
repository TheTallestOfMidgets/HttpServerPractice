package com.TheTallestOfMidgets.HttpProtocol.General;

public class HttpVersion {
    private final int major;
    private final int minor;

    public HttpVersion(int major, int minor){
        this.major = major;
        this.minor = minor;
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public boolean equals(HttpVersion version){
        return version.getMajor() == major && version.getMinor() == minor;
    }
}
