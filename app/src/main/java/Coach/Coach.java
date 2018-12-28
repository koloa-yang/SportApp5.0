package Coach;

//教练个人信息
public class Coach{
    private String name;
    private String sex;
    private String year;
    private String sop;
    private int imageId;
    public Coach(String name, String sex, String year, String sop, int imageId){
        this.name = name;
        this.sex = sex;
        this.year = year;
        this.sop = sop;
        this.imageId = imageId;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setSex(String sex){
        this.sex=sex;
    }
    public void setYear(String year){
        this.year=year;
    }
    public void setSop(String sop){
        this.sop=sop;
    }
    public String getName(){
        return name;
    }
    public String getSex(){
        return sex;
    }
    public String getYear(){
        return year;
    }
    public String getSop(){
        return sop;
    }
    public int getImageId() {
        return imageId;
    }
}