package com.smsimulator.server.root;

import com.google.gson.Gson;
import com.smsimulator.server.restlets.*;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

/**
 * Created by skaveesh on 2018-05-21.
 */

public class InboundRoot extends Application {

    public static Gson gson = new Gson();

    public Restlet createInboundRoot() {
        Router router = new Router(getContext());
        router.attach("/", new WelcomeRestlet());

        router.attach("/player/createaccount", new PlayerAccountCreateRestlet());
        router.attach("/player/login", new LoginPlayerRestlet());
        router.attach("/player/scoreboard/{startturn}", new ScoreboardRestlet());
        router.attach("/player/ranking", new RankingsRestlet());

        router.attach("/bank/account/createaccount", new BankAccountCreateRestlet());
        router.attach("/bank/account/check/{name}", new BankAccountExistenceRestlet());
        router.attach("/bank/account/balance/{name}", new BankBalanceRestlet());
        router.attach("/bank/account/profile/{name}", new BankProfileRestlet());

        router.attach("/broker/account/createaccount", new BrokerAccountCreateRestlet());
        router.attach("/broker/account/check/{name}", new BrokerAccountExistenceRestlet());
        router.attach("/broker/account/portfolio/{name}", new BrokerPortfolioRestlet());
        router.attach("/broker/stock/getall", new StockMarketRestlet());
        router.attach("/broker/stock/get/{stockname}", new StockPriceListOfCompanyRestlet());
        router.attach("/broker/stock/get/{stockname}/{index}", new StockPriceOfCompanyRestlet());
        router.attach("/broker/stock/buy", new StockBuyRestlet());
        router.attach("/broker/stock/sell", new StockSellRestlet());

        router.attach("/game/ready/{name}", new GameReadyRestlet());
        router.attach("/game/status", new GameStatusRestlet());
        router.attach("/game/analyser/recommendation", new AnalyserRecommendationRestlet());

        return router;
    }
}
