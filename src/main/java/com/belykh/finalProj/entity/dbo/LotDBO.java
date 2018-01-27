package com.belykh.finalProj.entity.dbo;

import java.util.Date;
import java.util.Objects;

/**
 * Created by panda on 7.1.18.
 */
public class LotDBO {
    private Long id;
    private Long buyerId;
    private Long ownerId;
    private Long flowerId;
    private Long addressId;
    private Double startPrice;
    private Double currentPrice;
    private LotState state;
    private int count;
    private Date end;
    private String description;

    public LotDBO(Long id, Long buyerId, Long ownerId, Long flowerId, Long addressId, Double startPrice, Double currentPrice, LotState state, int count, Date end, String description) {
        this.id = id;
        this.buyerId = buyerId;
        this.ownerId = ownerId;
        this.flowerId = flowerId;
        this.addressId = addressId;
        this.startPrice = startPrice;
        this.currentPrice = currentPrice;
        this.state = state;
        this.count = count;
        this.end = end;
        this.description = description;
    }

    public LotDBO() {
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getFlowerId() {
        return flowerId;
    }

    public void setFlowerId(Long flowerId) {
        this.flowerId = flowerId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Double startPrice) {
        this.startPrice = startPrice;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public LotState getState() {
        return state;
    }

    public void setState(LotState state) {
        this.state = state;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LotDBO lotDBO = (LotDBO) o;
        return count == lotDBO.count &&
                Objects.equals(id, lotDBO.id) &&
                Objects.equals(buyerId, lotDBO.buyerId) &&
                Objects.equals(ownerId, lotDBO.ownerId) &&
                Objects.equals(flowerId, lotDBO.flowerId) &&
                Objects.equals(addressId, lotDBO.addressId) &&
                Objects.equals(startPrice, lotDBO.startPrice) &&
                Objects.equals(currentPrice, lotDBO.currentPrice) &&
                state == lotDBO.state &&
                Objects.equals(end, lotDBO.end) &&
                Objects.equals(description, lotDBO.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, buyerId,  ownerId, flowerId, addressId, startPrice, currentPrice, state, count, end, description);
    }

    @Override
    public String toString() {
        return "LotDBO{" +
                "id=" + id +
                ", buyerId=" + buyerId +
                ", ownerId=" + ownerId +
                ", flowerId=" + flowerId +
                ", addressId=" + addressId +
                ", startPrice=" + startPrice +
                ", currentPrice=" + currentPrice +
                ", state=" + state +
                ", count=" + count +
                ", end=" + end +
                ", description='" + description + '\'' +
                '}';
    }
}