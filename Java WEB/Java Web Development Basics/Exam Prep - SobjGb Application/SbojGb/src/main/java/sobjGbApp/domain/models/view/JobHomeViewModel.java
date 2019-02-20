package sobjGbApp.domain.models.view;

public class JobHomeViewModel {

    private String id;
    private String sectorImg;
    private String profession;

    public JobHomeViewModel() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSectorImg() {
        return this.sectorImg;
    }

    public void setSectorImg(String sectorImg) {
        this.sectorImg = sectorImg;
    }

    public String getProfession() {
        return this.profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
