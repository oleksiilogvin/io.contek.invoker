package io.contek.invoker.binancespot.api.websocket.market;

import io.contek.invoker.binancespot.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.binancespot.api.websocket.common.WebSocketStreamMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.binancespot.api.websocket.common.constants.WebSocketChannelKeys._bookTicker;

@ThreadSafe
public final class BookTickerChannel
    extends MarketWebSocketChannel<BookTickerChannel.Id, BookTickerChannel.Message> {

  BookTickerChannel(Id id, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id, requestIdGenerator);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
  }

  @Immutable
  public static final class Id extends MarketWebSocketChannelId<Message> {

    private Id(String symbol) {
      super(symbol, _bookTicker);
    }

    public static Id of(String symbol) {
      return new Id(symbol);
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketStreamMessage<BookTickerEvent> {}
}
