package meTube.domain.models.viewModels;

public class TubeDetailsViewModel {

    private String id;
    private String name;
    private String description;
    private String youTubeLink;
    private String uploader;

    public TubeDetailsViewModel() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYouTubeLink() {
        return this.youTubeLink;
    }

    public void setYouTubeLink(String youTubeLink) {
        this.youTubeLink = youTubeLink;
    }

    public String getUploader() {
        return this.uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public boolean isYouTube() {
        return this.getYouTubeLink().contains("youtu.be");
    }

    public String getYouTubeFrameId() {
        String[] linkTokens = this.getYouTubeLink().split("/");
        String youTubeFrameId = linkTokens[linkTokens.length - 1];
        return youTubeFrameId;
    }
}
