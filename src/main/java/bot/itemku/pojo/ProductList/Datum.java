
package bot.itemku.pojo.ProductList;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Generated("jsonschema2pojo")
public class Datum {

    private Object competitorPrice;
    private Game game;
    private Integer gameId;
    private Integer id;
    private List<Image> images = null;
    private Integer isInsertedPromotion;
    private Integer itemCategoryId;
    private Integer itemInfoGroupId;
    private ItemType itemType;
    private Integer itemTypeId;
    private Object marketPrice;
    private Integer minOrder;
    private String name;
    private OrderRecord orderRecord;
    private Integer price;
    private Object sellerEmblem;
    private String seoString;
    private String serverName;
    private Integer stock;
    private Integer useAds;
    private Integer useAutoDelivery;
    private Integer useFastDelivery;
    private List<Object> wholesale = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Object getCompetitorPrice() {
        return competitorPrice;
    }

    public void setCompetitorPrice(Object competitorPrice) {
        this.competitorPrice = competitorPrice;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Integer getIsInsertedPromotion() {
        return isInsertedPromotion;
    }

    public void setIsInsertedPromotion(Integer isInsertedPromotion) {
        this.isInsertedPromotion = isInsertedPromotion;
    }

    public Integer getItemCategoryId() {
        return itemCategoryId;
    }

    public void setItemCategoryId(Integer itemCategoryId) {
        this.itemCategoryId = itemCategoryId;
    }

    public Integer getItemInfoGroupId() {
        return itemInfoGroupId;
    }

    public void setItemInfoGroupId(Integer itemInfoGroupId) {
        this.itemInfoGroupId = itemInfoGroupId;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public Integer getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(Integer itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public Object getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Object marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getMinOrder() {
        return minOrder;
    }

    public void setMinOrder(Integer minOrder) {
        this.minOrder = minOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrderRecord getOrderRecord() {
        return orderRecord;
    }

    public void setOrderRecord(OrderRecord orderRecord) {
        this.orderRecord = orderRecord;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Object getSellerEmblem() {
        return sellerEmblem;
    }

    public void setSellerEmblem(Object sellerEmblem) {
        this.sellerEmblem = sellerEmblem;
    }

    public String getSeoString() {
        return seoString;
    }

    public void setSeoString(String seoString) {
        this.seoString = seoString;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getUseAds() {
        return useAds;
    }

    public void setUseAds(Integer useAds) {
        this.useAds = useAds;
    }

    public Integer getUseAutoDelivery() {
        return useAutoDelivery;
    }

    public void setUseAutoDelivery(Integer useAutoDelivery) {
        this.useAutoDelivery = useAutoDelivery;
    }

    public Integer getUseFastDelivery() {
        return useFastDelivery;
    }

    public void setUseFastDelivery(Integer useFastDelivery) {
        this.useFastDelivery = useFastDelivery;
    }

    public List<Object> getWholesale() {
        return wholesale;
    }

    public void setWholesale(List<Object> wholesale) {
        this.wholesale = wholesale;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
