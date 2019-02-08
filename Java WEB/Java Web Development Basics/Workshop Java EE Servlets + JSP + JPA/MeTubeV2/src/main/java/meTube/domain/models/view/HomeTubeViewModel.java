package meTube.domain.models.view;

public class HomeTubeViewModel {

    private String id;
    private String title;
    private String youTubeId;
    private String uploader;

    public HomeTubeViewModel() {
    }

    public String getTitle() {
        return this.title;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYouTubeId() {
        return this.youTubeId;
    }

    public void setYouTubeId(String youTubeId) {
        this.youTubeId = youTubeId;
    }

    public String getUploader() {
        return this.uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }
}
