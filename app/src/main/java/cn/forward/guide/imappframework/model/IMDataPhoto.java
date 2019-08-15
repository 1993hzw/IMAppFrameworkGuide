package cn.forward.guide.imappframework.model;

public class IMDataPhoto extends IMData {

    public int width;
    public int height;

    public String url;

    public IMDataPhoto() {
        super(Type.PHOTO);
    }
}
