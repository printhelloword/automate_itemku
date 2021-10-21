
package bot.itemku.pojo.ProductList;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@Generated("jsonschema2pojo")
public class ItemType {

    private String howToTradeFaqId;
    private Integer itemCategoryId;
    private String name;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getHowToTradeFaqId() {
        return howToTradeFaqId;
    }

    public void setHowToTradeFaqId(String howToTradeFaqId) {
        this.howToTradeFaqId = howToTradeFaqId;
    }

    public Integer getItemCategoryId() {
        return itemCategoryId;
    }

    public void setItemCategoryId(Integer itemCategoryId) {
        this.itemCategoryId = itemCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
