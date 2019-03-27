package hello;

import java.util.List;
import java.util.Map;

public class DataResponse<T> {
    private String page;

    /**
     * 总页数
     */
    private String total;
    private Integer records;
    private List<T> rows;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }


    public List<T> getRows() {
        return rows;
    }

    public Integer getRecords() {
        return records;
    }

    public void setRecords(Integer records) {
        this.records = records;
    }

    public void setRows(List<T> rows) {

        this.rows = rows;
    }


}
