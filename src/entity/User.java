package entity;

import java.util.Date;

import view.transit;

// 用户类
public class User {
    private Integer id;
    private String sort;
    private String Product_name;
    private Integer Amount;
    private Integer Unite;
    private Integer Allcost;
    /* 购入单号，时间 */
    private Date Deliver_date;
    private Integer Deliver_order;
    /* 客户表+供货商 */
    private String Client_name;
    private Integer Client_id;
    private String Client_place;

    private Date Import_date;
    /* 入库表 */
    private Boolean product_sale;

    /* 售出单号，时间 */
    private Integer Deal_order;
    private Date Deal_date;

    public Date getDeal_date() {
        return Deal_date;
    }

    public void setDeal_date(Date deal_date) {
        Deal_date = deal_date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getClient_name() {
        return Client_name;
    }

    public void setClient_name(String client_name) {
        Client_name = client_name;
    }

    public Integer getClient_id() {
        return Client_id;
    }

    public void setClient_id(Integer client_id) {
        Client_id = client_id;
    }

    public String getClient_place() {
        return Client_place;
    }

    public void setClient_place(String client_place) {
        Client_place = client_place;
    }

    public String getProduct_name() {
        return Product_name;
    }

    public void setProduct_name(String product_name) {
        Product_name = product_name;
    }

    public Integer getAmount() {
        return Amount;
    }

    public void setAmount(Integer amount) {
        Amount = amount;
    }

    public Integer getUnite() {
        return Unite;
    }

    public void setUnite(Integer unite) {
        Unite = unite;
    }

    public Integer getAllcost() {
        return Allcost;
    }

    public void setAllcost(Integer allcost) {
        Allcost = allcost;
    }

    public Date getDeliver_date() {
        return Deliver_date;
    }

    public void setDeliver_date(Date deliver_date) {
        Deliver_date = deliver_date;
    }

    public Integer getDeliver_order() {
        return Deliver_order;
    }

    public void setDeliver_order(Integer deliver_order) {
        Deliver_order = deliver_order;
    }

    public Date getImport_date() {
        return Import_date;
    }

    public void setImport_date(Date import_date) {
        Import_date = import_date;
    }

    public Boolean getProduct_sale() {
        return product_sale;
    }

    public void setProduct_sale(Boolean product_sale) {
        this.product_sale = product_sale;
    }

    public Integer getDeal_order() {
        return Deal_order;
    }

    public void setDeal_order(Integer deal_order) {
        Deal_order = deal_order;
    }

    /* 售出表 */
    public User(Integer id, Integer Deal_order, String sort, String Product_name, Integer Amount, Integer Unite,
            Integer Allcost, Integer Deliver_order, Date Deal_date, Integer Client_id) {
        setId(id);
        setDeal_order(Deal_order);
        setSort(sort);
        setProduct_name(Product_name);
        setAmount(Amount);
        setUnite(Unite);
        setAllcost(Allcost);
        setDeliver_order(Deliver_order);
        setDeal_date(Deal_date);
        setClient_id(Client_id);
    }

    /* 客户和供货商表 */
    public User(Integer id, Integer Client_id, String Client_name, String Client_place) {

        setId(id);
        setClient_id(Client_id);
        setClient_name(Client_name);
        setClient_place(Client_place);
    }

    /* 供货/购入记录表 */
    public User(Integer id, Integer Supply_id, Integer Deliver_order, String sort, String Product_name, Integer Amount,
            Integer Unite, Integer Allcost, Date Deliver_date) {
        setId(id);
        setClient_id(Supply_id);
        setDeliver_order(Deliver_order);
        setSort(sort);
        setProduct_name(Product_name);
        setAmount(Amount);
        setUnite(Unite);
        setAllcost(Allcost);
        setDeliver_date(Deliver_date);
    }

    // 查询入库记录
    public User(Integer id, String sort, String Product_name, Integer amount, Integer Unite, Integer Allcost,
            Date Deliver_date, Integer Deliver_order, Date Import_date, Boolean product_sale) {
        setId(id);
        setSort(sort);
        setProduct_name(Product_name);
        setAmount(amount);
        setUnite(Unite);
        setAllcost(Allcost);
        setDeliver_date(Deliver_date);
        setDeliver_order(Deliver_order);
        setImport_date(Import_date);
        setProduct_sale(product_sale);
    }

    // 考虑是直接多写还是内部根据参数来//由于参数列表允许
    public Object getCol(int col) {
        switch (transit.getSelection()) {
            case 1: {
                switch (col) {
                    case 0:
                        return getId();
                    case 1:
                        return getSort();
                    case 2:
                        return getProduct_name();
                    case 3:
                        return getAmount();
                    case 4:
                        return getUnite();
                    case 5:
                        return getAllcost();
                    case 6:
                        return getDeliver_date();
                    case 7:
                        return getDeliver_order();
                    case 8:
                        return getImport_date();
                    case 9:
                        return getProduct_sale();
                }
            }
                break;
            case 2: {
                switch (col) {
                    case 0:
                        return getId();
                    case 1:
                        return getClient_id();
                    case 2:
                        return getDeliver_order();
                    case 3:
                        return getSort();
                    case 4:
                        return getProduct_name();
                    case 5:
                        return getAmount();
                    case 6:
                        return getUnite();
                    case 7:
                        return getAllcost();
                    case 8:
                        return getDeliver_date();
                }
            }
                break;
            case 3: {
                switch (col) {
                    case 0:
                        return getId();
                    case 1:
                        return getDeal_order();
                    case 2:
                        return getSort();
                    case 3:
                        return getProduct_name();
                    case 4:
                        return getAmount();
                    case 5:
                        return getUnite();
                    case 6:
                        return getAllcost();
                    case 7:
                        return getDeliver_order();
                    case 8:
                        return getDeal_date();
                    case 9:
                        return getClient_id();
                }
            } 
            
            case 4: {
                switch (col) {
                    case 0:
                        return getId();
                    case 1:
                        return getClient_id();
                    case 2:
                        return getClient_name();
                    case 3:
                        return getClient_place();
                }
            }
                break;
            case 5: {
                switch (col) {
                    case 0:
                        return getId();
                    case 1:
                        return getClient_id();
                    case 2:
                        return getClient_name();
                    case 3:
                        return getClient_place();
                }
            }
                break;
            default:
                break;
        }
        return "";
    }
}
