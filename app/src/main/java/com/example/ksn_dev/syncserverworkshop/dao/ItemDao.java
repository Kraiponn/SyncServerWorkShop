package com.example.ksn_dev.syncserverworkshop.dao;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemDao {
    /**
     * id : 2020384
     * link : http://500px.com/photo/262298227/untitled-by-%E5%97%A8
     * image_url : https://drscdn.500px.org/photo/262298227/m%3D1080_k%3D1_a%3D1/v2?client_application_id=25967&user_id=823112&webp=true&sig=2c3bc8244818cbc59d24fb8e58b38ffb034bd265ee493e23adb418d163c90952
     * caption : Untitled
     * user_id : 26221633
     * username : 7c0ff59c244928c4cbdb2e7ac1b127680
     * profile_picture : https://drscdn.500px.org/user_avatar/26221633/q%3D85_w%3D300_h%3D300/v2?webp=true&v=14&sig=c485b86f3805dd2933f799686829ca17b83a2eaeed7b23da938a83c12e068988
     * tags : ["建筑物","黑白"]
     * created_time : 2018-06-15T13:02:34-04:00
     * camera : EVA-DL00
     * lens :
     * focal_length : 4.5
     * iso : 50
     * shutter_speed : 1605000/10
     * aperture : F2.2
     */

    @SerializedName("id")
    private int id;

    @SerializedName("link")
    private String link;

    @SerializedName("image_url")
    private String image_url;

    @SerializedName("caption")
    private String caption;

    @SerializedName("user_id")
    private int user_id;

    @SerializedName("username")
    private String username;

    @SerializedName("profile_picture")
    private String profile_picture;

    @SerializedName("created_time")
    private String created_time;

    @SerializedName("camera")
    private String camera;

    @SerializedName("lens")
    private String lens;

    @SerializedName("focal_length")
    private String focal_length;

    @SerializedName("iso")
    private String iso;

    @SerializedName("shutter_speed")
    private String shutter_speed;

    @SerializedName("aperture")
    private String aperture;

    @SerializedName("tags")
    private List<String> tags;


    public ItemDao() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getLens() {
        return lens;
    }

    public void setLens(String lens) {
        this.lens = lens;
    }

    public String getFocal_length() {
        return focal_length;
    }

    public void setFocal_length(String focal_length) {
        this.focal_length = focal_length;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getShutter_speed() {
        return shutter_speed;
    }

    public void setShutter_speed(String shutter_speed) {
        this.shutter_speed = shutter_speed;
    }

    public String getAperture() {
        return aperture;
    }

    public void setAperture(String aperture) {
        this.aperture = aperture;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
