package Course;

public class CourseItem {
    private String cname;
    private String cdate;
    private String ctime;
    private String ccoach;
    public CourseItem(String cname,String cdate,String ctime,String ccoach){
        this.cname=cname;
        this.cdate=cdate;
        this.ctime=ctime;
        this.ccoach=ccoach;
    }
    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getCcoach() {
        return ccoach;
    }

    public void setCcoach(String ccoach) {
        this.ccoach = ccoach;
    }
}
