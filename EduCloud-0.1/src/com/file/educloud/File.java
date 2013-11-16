package com.file.educloud;

/**
 * Created with IntelliJ IDEA.
 * User: save
 * Date: 13-11-16
 * Time: 下午3:29
 * To change this template use File | Settings | File Templates.
 */
public class File {
    String uuid;                //文件uuid      （唯一标识）
    int type;                   //类型
    String name;                //文件名
    int imgId;               //文件图像id
    int btnImgId;            //按钮样式id
    String user;                //所属用户


    public File(String uuid, int type, String name, int imgId,int btnImgId, String user) {
        this.uuid = uuid;
        this.type = type;
        this.name = name;
        this.imgId = imgId;
        this.btnImgId = btnImgId;
        this.user = user;
    }

    public File() {
        this.uuid = "";
        this.type = -1;
        this.name = "";
        this.imgId = -1;
        this.user = "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public String getUuid() {
        return uuid;
    }

    public int getType() {
        return type;
    }

    public int getBtnImgId() {
        return btnImgId;
    }

    public String getName() {
        return name;
    }

    public int getImgId() {
        return imgId;
    }

    public String toString(){
         return "The File is " +"uuid:"+ uuid+ "type:"+type+"name:"+name+"imgId:"+imgId+"user:"+user;
    }
}
