package bot.itemku.Controller;

import bot.itemku.pojo.ResponsePojo;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class RequestListener {

    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(RequestListener.class);
    private static TransactionController processRequest;

    @GetMapping("/trx/{trxId}")
    public String processRequestAndAndGetResponseByTransaction(
            @PathVariable String trxId)
            throws IOException {

        logger.info("======Incoming Request======= : trx/" + trxId);

        if (processRequest == null)
            processRequest = TransactionController.ofRequest(trxId);

        processRequest.setTrxId(trxId);

        ResponsePojo responsePojo = processRequest.getResponsePojo();

        return new JSONObject(responsePojo).toString().replace("\\", "");
    }

    public static void startBrowser() {
        if (processRequest == null)
            processRequest = TransactionController.ofRequest("0");

        processRequest.getResponsePojo();
    }


}
