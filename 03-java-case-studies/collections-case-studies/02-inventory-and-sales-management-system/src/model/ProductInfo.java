package model;

public class ProductInfo {
    private String productId;
    private String productName;
    private double unitPrice;
    private ProductCategory productCategory;
    private int availableStock;

    private String companyId;
    private String companyName;
    private String companyContact;
    private String companyLocation;

    public ProductInfo(
            String productId,
            String productName,
            double unitPrice,
            ProductCategory productCategory,
            int availableStock,
            String companyId,
            String companyName,
            String companyContact,
            String companyLocation) {

        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.productCategory = productCategory;
        this.availableStock = availableStock;
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyContact = companyContact;
        this.companyLocation = companyLocation;
    }

    @Override
    public String toString() {
        return
                "========== Product Details ==========" +
                "\nProduct Id:      " + productId +
                "\nName:            " + productName +
                "\nPrice:           " + unitPrice +
                "\nCategory:        " + productCategory +
                "\nAvailable Stock: " + availableStock +
                "\n" +
                "\n========== Company Details ==========" +
                "\nCompany ID:       " + companyId +
                "\nCompany Name:     " + companyName +
                "\nCompany Contact:  " + companyContact +
                "\nCompany Location: " + companyLocation;
    }
}
