package com.smsimulator.server.restlets;

import com.smsimulator.core.Broker;
import com.smsimulator.core.Player;
import com.smsimulator.core.Portfolio;
import com.smsimulator.gsoncore.BrokerPortfolio;
import com.smsimulator.server.root.InboundRoot;
import com.smsimulator.server.security.JWTSecurity;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.MediaType;
import org.restlet.data.Method;
import org.restlet.data.Status;

/**
 * Project UCD_FinalProject_SAVICK
 * Created by skaveesh on 2018-06-13.
 */
public class BrokerPortfolioRestlet extends JWTSecurity {
    @Override
    public void handle(Request request, Response response) {
        super.handle(request, response);

        if (request.getMethod().equals(Method.POST) && tokenAccepted) {

            int playerUid = new Player().getUidFromName((String) request.getAttributes().get("name"));

            if (playerUid != -1) {
                Portfolio portfolio = new Broker().portfolio((String) request.getAttributes().get("name"));

                if(portfolio != null) {
                    //response gson object
                    com.smsimulator.gsoncore.Portfolio portfolio1 = new com.smsimulator.gsoncore.Portfolio();
                    portfolio1.setName(portfolio.getName());
                    portfolio1.setOwnStockList(portfolio.getOwnStockList());
                    portfolio1.setBroughtStockList(portfolio.getBroughtStockList());
                    portfolio1.setSoldStockList(portfolio.getSoldStockList());

                    BrokerPortfolio brokerPortfolio = new BrokerPortfolio();
                    brokerPortfolio.setPortfolio(portfolio1);

                    response.setEntity(InboundRoot.gson.toJson(brokerPortfolio), MediaType.APPLICATION_JSON);
                    response.setStatus(Status.SUCCESS_OK);
                }else {
                    response.setStatus(Status.CLIENT_ERROR_FORBIDDEN);
                }
            } else
                response.setStatus(Status.CLIENT_ERROR_FORBIDDEN);
        } else {
            response.setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);
        }
    }
}
