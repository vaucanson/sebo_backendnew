package dto;

import java.io.Serializable;

public class OrderedProduct implements Serializable
{
    private String name;
    private String category;
    private String supplier;
    private String reference;
    private int orderedQuantity;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getSupplier()
    {
        return supplier;
    }

    public void setSupplier(String supplier)
    {
        this.supplier = supplier;
    }

    public String getReference()
    {
        return reference;
    }

    public void setReference(String reference)
    {
        this.reference = reference;
    }

    public int getOrderedQuantity()
    {
        return orderedQuantity;
    }

    public void setOrderedQuantity(int orderedQuantity)
    {
        this.orderedQuantity = orderedQuantity;
    }

    /**
     * Constructeur vide
     */
    public OrderedProduct()
    {
    }

    /**
     * Constructeur
     * @param reference
     * @param name
     * @param category
     * @param supplier
     * @param orderedQuantity 
     */
    public OrderedProduct(String reference,
                            String name, 
                            String category, 
                            String supplier,
                            int orderedQuantity
                            )
    {
        this.name = name;
        this.category = category;
        this.supplier= supplier;
        this.reference = reference;
        this.orderedQuantity = orderedQuantity;
    }


    /**
     * toString
     * @return 
     */
    @Override
    public String toString()
    {
        return "tostring : " + this.reference;
    }

}
