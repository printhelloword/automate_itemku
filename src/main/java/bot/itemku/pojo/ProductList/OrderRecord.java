
package bot.itemku.pojo.ProductList;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@Generated("jsonschema2pojo")
public class OrderRecord {

    private Integer productId;
    private Integer successfulOrderCount;
    private Integer failedOrderCount;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getSuccessfulOrderCount() {
        return successfulOrderCount;
    }

    public void setSuccessfulOrderCount(Integer successfulOrderCount) {
        this.successfulOrderCount = successfulOrderCount;
    }

    public Integer getFailedOrderCount() {
        return failedOrderCount;
    }

    public void setFailedOrderCount(Integer failedOrderCount) {
        this.failedOrderCount = failedOrderCount;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
