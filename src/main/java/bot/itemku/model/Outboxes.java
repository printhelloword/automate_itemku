package bot.itemku.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Outboxes {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String message;
    private String receiver;
    private Date createDate;
    private Integer inboxId;

    public Outboxes(String message, String receiver, Date createDate, Integer inboxId) {
        this.message = message;
        this.receiver = receiver;
        this.createDate = createDate;
        this.inboxId = inboxId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "message", nullable = true, length = -1)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "receiver", nullable = true, length = 255)
    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Basic
    @Column(name = "create_date", nullable = true)
    public Date getCreateDate() {
        return createDate;
    }

    public Outboxes() {
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "inbox_id", nullable = true)
    public Integer getInboxId() {
        return inboxId;
    }

    public void setInboxId(Integer inboxId) {
        this.inboxId = inboxId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Outboxes outboxes = (Outboxes) o;
        return id == outboxes.id &&
                Objects.equals(message, outboxes.message) &&
                Objects.equals(receiver, outboxes.receiver) &&
                Objects.equals(createDate, outboxes.createDate) &&
                Objects.equals(inboxId, outboxes.inboxId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, receiver, createDate, inboxId);
    }
}
