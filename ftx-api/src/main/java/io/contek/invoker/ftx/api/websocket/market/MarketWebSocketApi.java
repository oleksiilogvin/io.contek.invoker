package io.contek.invoker.ftx.api.websocket.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.contek.invoker.ftx.api.websocket.WebSocketApi;

import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public final class MarketWebSocketApi extends WebSocketApi {

  private final Map<OrderBookChannel.Id, OrderBookChannel> orderBookChannels = new HashMap<>();
  private final Map<TickerChannel.Id, TickerChannel> tickerChannels = new HashMap<>();
  private final Map<TradesChannel.Id, TradesChannel> tradesChannels = new HashMap<>();
  private final Map<MarketsChannel.Id, MarketsChannel> marketsChannels = new HashMap<>();

  public MarketWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public OrderBookChannel getOrderBookChannel(OrderBookChannel.Id id) {
    synchronized (orderBookChannels) {
      return orderBookChannels.computeIfAbsent(
          id,
          k -> {
            OrderBookChannel result = new OrderBookChannel(k);
            attach(result);
            return result;
          });
    }
  }

  public TickerChannel getTickerChannel(TickerChannel.Id id) {
    synchronized (tickerChannels) {
      return tickerChannels.computeIfAbsent(
          id,
          k -> {
            TickerChannel result = new TickerChannel(k);
            attach(result);
            return result;
          });
    }
  }

  public TradesChannel getTradesChannel(TradesChannel.Id id) {
    synchronized (tradesChannels) {
      return tradesChannels.computeIfAbsent(
          id,
          k -> {
            TradesChannel result = new TradesChannel(k);
            attach(result);
            return result;
          });
    }
  }

  public MarketsChannel getMarketsChannel(MarketsChannel.Id id) {
    synchronized (marketsChannels) {
      return marketsChannels.computeIfAbsent(
          id,
          k -> {
            MarketsChannel result = new MarketsChannel(k);
            attach(result);
            return result;
          });
    }
  }
}
