package bot.itemku.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Inboxes {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String message;
    private String sender;
    private Integer status;
    private Date createDate;
    private String trxId;

    public Inboxes() {
    }

    public static Inboxes createInboxFromParsedParams(String message, String trx, Date date, String trxId){
        return new Inboxes(message, "/trx", 0, date, trxId);
    }

    public Inboxes(String message, String sender, Integer status, Date createDate, String trxId) {
        this.message = message;
        this.sender = sender;
        this.status = status;
        this.createDate = createDate;
        this.trxId = trxId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "message", nullable = true, length = 255)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "sender", nullable = true, length = 255)
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "create_date", nullable = true)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "trx_id", nullable = true, length = 255)
    public String getTrxId() {
        return trxId;
    }

    public void setTrxId(String trxId) {
        this.trxId = trxId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inboxes inboxes = (Inboxes) o;
        return id == inboxes.id &&
                Objects.equals(message, inboxes.message) &&
                Objects.equals(sender, inboxes.sender) &&
                Objects.equals(status, inboxes.status) &&
                Objects.equals(createDate, inboxes.createDate) &&
                Objects.equals(trxId, inboxes.trxId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, sender, status, createDate, trxId);
    }
}
