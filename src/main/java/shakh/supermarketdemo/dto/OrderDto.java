package shakh.supermarketdemo.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private Long adminId;
    private Long debitorId;
    private String adminName;
    private String debitorsName;
    private Double paidCost;
    private Double totalCost;
    private Double unpaidCost;

    public OrderDto(Long id, Long adminId, Long debitorId, String adminName, String debitorsName, Double paidCost, Double totalCost, Double unpaidCost, List<OrderedItemsDto> orderedItems) {
        this.id = id;
        this.adminId = adminId;
        this.debitorId = debitorId;
        this.adminName = adminName;
        this.debitorsName = debitorsName;
        this.paidCost = paidCost;
        this.totalCost = totalCost;
        this.unpaidCost = unpaidCost;
        this.orderedItems = orderedItems;
    }

    private List<OrderedItemsDto> orderedItems;

    public OrderDto(Long id, Long adminId, Long debitorId, String adminName, String debitorsName, Double paidCost, Double totalCost) {
        this.id = id;
        this.adminId = adminId;
        this.debitorId = debitorId;
        this.adminName = adminName;
        this.debitorsName = debitorsName;
        this.paidCost = paidCost;
        this.totalCost = totalCost;
    }
}
