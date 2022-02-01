package net.guides.springboot2.crud.dto;

public class CommentDto {
    private Integer Orderid;
    private Integer Expertid;
    private Integer customerid;
    private Double score;
    private String desciption;

    public Integer getOrderid() {
        return Orderid;
    }

    public void setOrderid(Integer orderid) {
        Orderid = orderid;
    }

    public Integer getExpertid() {
        return Expertid;
    }

    public void setExpertid(Integer expertid) {
        Expertid = expertid;
    }

    public Integer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }
}
