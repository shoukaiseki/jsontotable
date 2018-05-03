package testjsonlist.model;

/**
 **/
public class WorkorderModel {

    private String wonum;

    /**
     * 已下载返回标识
     */
    private boolean retb;

    public boolean isRetb() {
        return retb;
    }

    public void setRetb(boolean retb) {
        this.retb = retb;
    }

    public String getWonum() {
        return wonum;
    }

    public void setWonum(String wonum) {
        this.wonum = wonum;
    }
}
