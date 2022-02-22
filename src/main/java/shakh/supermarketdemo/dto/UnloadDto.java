package shakh.supermarketdemo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import shakh.supermarketdemo.data.Product;
import shakh.supermarketdemo.data.Unload;
import shakh.supermarketdemo.service.ProductService;

import java.util.Date;

@NoArgsConstructor
@Data
public class UnloadDto {

    private Long id;

    private Double amount;

    private Double priceOfBuy;

    private Double priceOfSell;

    private Boolean isActive = true;

    private Long productId;

    public Unload convertToUnload(ProductService productService) {
        Unload unload = new Unload();
        return convertToUnload(unload, productService);
    }

    public Unload convertToUnload(Unload unload, ProductService productService) {
        Date now = new Date();
        if (id != null) unload.setId(id);
        unload.setAmount(amount);
        unload.setCreatedTime(now);
        unload.setPriceOfBuy(priceOfBuy);
        unload.setPriceOfSell(priceOfSell);

        if (productId != null) {
            Product product = productService.getProductById(productId);
            product.setLastUpdatedTime(now);
            product.setPriceOfBuy(priceOfBuy);
            product.setPriceOfSell(priceOfSell);
            product.setAmount(product.getAmount() + amount);
            unload.setProduct(product);
        }
        return unload;
    }


}
