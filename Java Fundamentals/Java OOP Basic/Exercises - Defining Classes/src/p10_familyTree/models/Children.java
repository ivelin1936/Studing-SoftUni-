package p10_familyTree.models;

public class Children {

    private String name;
    private String birthday;

    public Children() {
    }

    public Children(String name, String birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.getName(), this.getBirthday());
    }
}
