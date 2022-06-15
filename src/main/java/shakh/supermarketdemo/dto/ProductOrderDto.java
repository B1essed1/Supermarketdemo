package shakh.supermarketdemo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import shakh.supermarketdemo.data.Debitors;
import shakh.supermarketdemo.data.OrderItem;
import shakh.supermarketdemo.data.Payment;
import shakh.supermarketdemo.data.ProductOrder;
import shakh.supermarketdemo.data.securitymodel.Admins;
import shakh.supermarketdemo.service.AdminService;
import shakh.supermarketdemo.service.DebitorService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductOrderDto {

    private Long id;

    private Double paidCost;

    private Double totalCost;

    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "dd.MM.yyyy HH:mm", timezone = "Asia/Tashkent")
    private Date createdTime;

    List<OrderItem> orderItems = new ArrayList<>();
    private Long debitorId;

    private String firsName;
    private String lastName;
    private String additionalDetails;

    private Boolean isActive;

    private List<Payment> payment = new ArrayList<>();

    private Long adminId;

    private String adminUsername;

    private Double paymentAmount;

    public ProductOrderDto(ProductOrder productOrder, List<OrderItem> orderItems) {
        if (productOrder.getId() != null) {
            setId(productOrder.getId());
        }
        setPaidCost(productOrder.getPaidCost());
        setTotalCost(productOrder.getTotalCost());
        setCreatedTime(productOrder.getCreatedTime());
        setIsActive(productOrder.getIsActive());

        if (productOrder.getDebitors() != null) {
            setDebitorId(productOrder.getDebitors().getId());
            setFirsName(productOrder.getDebitors().getFirstName());
            setLastName(productOrder.getDebitors().getLastName());
            setAdditionalDetails(productOrder.getDebitors().getAdditionalDetails());
        }

        if (productOrder.getAdmins() != null) {
            setAdminId(productOrder.getAdmins().getId());
            setAdminUsername(productOrder.getAdmins().getUsername());
        }
        setOrderItems(orderItems);
    }

    public ProductOrder convertToProductOrder(DebitorService debitorService, AdminService adminService) {
        ProductOrder productOrder = new ProductOrder();
        return convertToProductOrder(productOrder, debitorService, adminService);
    }

    public ProductOrder convertToProductOrder(ProductOrder productOrder, DebitorService debitorService, AdminService adminService) {
        Date now = new Date();


        if (id != null) {
            productOrder.setId(id);
        }
        productOrder.setPaidCost(paidCost);
        productOrder.setTotalCost(totalCost);
        productOrder.setCreatedTime(now);

        if (debitorId != null) {
            Debitors debitors = debitorService.getDebitorById(debitorId);
            productOrder.setDebitors(debitors);
        }
        if (adminId != null) {
            Admins admins = adminService.getAdminById(adminId);
            productOrder.setAdmins(admins);
        }
        return productOrder;
    }
}


