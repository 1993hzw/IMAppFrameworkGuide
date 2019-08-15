package cn.forward.guide.imappframework.model;

public class IMData {

    public final Type mType;

    public IMData(Type type) {
        mType = type;
    }

    public enum Type {
        PHOTO
    }
}
