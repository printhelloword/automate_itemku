package bot.itemku.pojo;

public class ResponsePojo {
//    private static final String TAG_STATUS_FAILED="failed"; //default to failed
    private String message;
    private String trxId;
    private String status;


    public ResponsePojo() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTrxId() {
        return trxId;
    }

    public void setTrxId(String trxId) {
        this.trxId = trxId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ResponsePojo(String message, String trxId, String status) {
        this.message = message;
        this.trxId = trxId;
        this.status = status;
    }

}
