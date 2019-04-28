package kr.hs.foodmate;

public class list_item{
        private int profile_img;
        private String nick;
        private String title;
        private String tag;

    public int getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(int profile_img) {
        this.profile_img = profile_img;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public list_item(int profile_img, String nick, String title, String tag) {
        this.profile_img = profile_img;
        this.nick = nick;
        this.title = title;
        this.tag = tag;
    }
}
