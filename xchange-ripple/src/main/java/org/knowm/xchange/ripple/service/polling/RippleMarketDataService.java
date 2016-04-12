package org.knowm.xchange.ripple.service.polling;

import java.io.IOException;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.ripple.RippleAdapters;
import org.knowm.xchange.ripple.RippleExchange;
import org.knowm.xchange.ripple.dto.marketdata.RippleOrderBook;
import org.knowm.xchange.ripple.service.polling.params.RippleMarketDataParams;
import org.knowm.xchange.service.polling.marketdata.PollingMarketDataService;

public class RippleMarketDataService extends RippleMarketDataServiceRaw implements PollingMarketDataService {

  public RippleMarketDataService(final Exchange exchange) {
    super(exchange);
  }

  /**
   * If the base currency is not XRP then the returned orders' additional data map contains a value for {@link RippleExchange.DATA_BASE_COUNTERPARTY},
   * similarly if the counter currency is not XRP then {@link RippleExchange.DATA_COUNTER_COUNTERPARTY} is populated.
   * 
   * @param currencyPair the base/counter currency pair
   * @param args a RippleMarketDataParams object needs to be supplied
   */
  @Override
  public OrderBook getOrderBook(final CurrencyPair currencyPair, final Object... args) throws IOException {
    if ((args.length > 0) && (args[0] instanceof RippleMarketDataParams)) {
      final RippleMarketDataParams params = (RippleMarketDataParams) args[0];
      final RippleOrderBook orderBook = getRippleOrderBook(currencyPair, params);
      return RippleAdapters.adaptOrderBook(orderBook, params, currencyPair);
    } else {
      throw new ExchangeException("RippleMarketDataParams is missing");
    }
  }

  @Override
  public Ticker getTicker(final CurrencyPair currencyPair, final Object... args)
      throws ExchangeException, NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {
    throw new NotYetImplementedForExchangeException();
  }

  @Override
  public Trades getTrades(final CurrencyPair currencyPair, final Object... args)
      throws ExchangeException, NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {
    throw new NotYetImplementedForExchangeException();
  }
}