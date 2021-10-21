package bot.itemku.Controller;

import bot.itemku.ItemkuBot;
import bot.itemku.model.Inboxes;
import bot.itemku.model.Outboxes;
import bot.itemku.pojo.ResponsePojo;
import bot.itemku.utility.DBUtilInboxes;
import bot.itemku.utility.DBUtilOutboxess;
import bot.itemku.utility.InputValidator;
import org.json.JSONObject;

import java.util.Map;

public class TransactionController {
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(TransactionController.class);

    private static final String STATUS_FAILED = "Failed";
    private static final String STATUS_SUCCESS = "Success";

    private static final String MSG_ERROR_TRX_ID = "TrxID Tidak Valid";
    private static final String MSG_ERROR_TRX_ID_EXISTS = "TrxID Sudah Terdapat Di Database";

    private String trxId;

    private Integer inboxId;

    private ResponsePojo responsePojo = new ResponsePojo();
    private ItemkuBot itemkuBot;

    public TransactionController(String trxId) {
        this.trxId = trxId;
    }

    public void setTrxId(String trxId) {
        this.trxId = trxId;
    }

    public static TransactionController ofRequest(String trxId) {
        return new TransactionController(trxId);
    }

    public ResponsePojo getResponsePojo() {
        initResponsePojo(trxId);

        try {
            validateRequestAndStartTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Transaction Failed");
        }

        printResponseJsonAndSaveToOutbox(new JSONObject(responsePojo));

        return responsePojo;
    }

    private void validateRequestAndStartTransaction() {
        if (!isTrxValid()) {
            updateResponseMessage(MSG_ERROR_TRX_ID);
        } else {
            if (isTrxIdAlreadyExists(responsePojo.getTrxId())) {
                logger.info(MSG_ERROR_TRX_ID_EXISTS + ", MENGECEK OUTBOX");
                String outbox = DBUtilOutboxess.getOutbox(responsePojo.getTrxId());
                updateResponseMessage(outbox);
                responsePojo.setStatus(updateStatusByParsedOutbox(outbox));
            } else {
                saveToInbox(createNewInbox(trxId));
                checkInboxisSavedAndStartTransaction();
            }
        }
    }

    private String updateStatusByParsedOutbox(String outbox) {
        return outbox.contains(STATUS_SUCCESS) ? STATUS_SUCCESS : STATUS_FAILED;
    }

    private boolean isTrxValid() {
        return (InputValidator.isInputNumeric(responsePojo.getTrxId()));
    }

    private void initResponsePojo(String trxId) {
        responsePojo.setTrxId(trxId);
        responsePojo.setMessage("");
        responsePojo.setStatus(STATUS_FAILED);
    }


    private void printResponseJsonAndSaveToOutbox(JSONObject jsonObject) {
        logger.info("Returning JSON : ");
        logger.info(jsonObject.toString(4).replace("//", ""));
        if (inboxId != null)
            saveToOutbox(createNewBoutbox());
    }

    private void checkInboxisSavedAndStartTransaction() {

        if (itemkuBot == null)
            itemkuBot = new ItemkuBot();

        itemkuBot.setBotIdleStatus(false);

        if (isSaveToInboxSucceed()) {
            itemkuBot = new ItemkuBot();
            Map<Boolean, String> transactionResult = itemkuBot.startTransaction();
            if (transactionResult != null) {
                for (Map.Entry<Boolean, String> entry : transactionResult.entrySet()) {
                    responsePojo.setStatus((entry.getKey()) ? STATUS_SUCCESS : STATUS_FAILED);
                    responsePojo.setMessage(entry.getValue());
                }
            }
        }
    }

    private Inboxes createNewInbox(String trxId) {
        String message = trxId;
        return new Inboxes(message, getRequesParams(), 0, getJavaUtilDate(), trxId);
    }

    private String getRequesParams() {
        return trxId;
    }

    private void saveToOutbox(Outboxes newBoutbox) {
        try {
            DBUtilOutboxess.saveOutbox(newBoutbox);
        } catch (Exception e) {
            logger.info(e.getMessage());
            logger.info("Failed Save To Outbox");
        }
    }

    private void saveToInbox(Inboxes newInbox) {
        try {
            inboxId = DBUtilInboxes.saveInbox(newInbox);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Failed Save To DB");
        }
    }

    private Outboxes createNewBoutbox() {
        return new Outboxes(new JSONObject(responsePojo).toString(), null, getJavaUtilDate(), inboxId);
    }

    private boolean isSaveToInboxSucceed() {
        return inboxId != null;
    }

    private java.util.Date getJavaUtilDate() {
        return new java.util.Date();
    }

    private boolean isTrxIdAlreadyExists(String trxId) {
        return DBUtilInboxes.isTrxIdExists(trxId);
    }


    private void updateResponseMessage(String message) {
        responsePojo.setMessage(message);
    }


}
