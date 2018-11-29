package movies.hd.box.movies.show.box.model;

public class movie {
    private String name;
    private String picUrl;

    public String getName() {
        return name;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public movie(String name, String picUrl) {

        this.name = name;
        this.picUrl = picUrl;
    }
}
