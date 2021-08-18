package io.contek.invoker.ftx.api.rest.user;

import org.junit.jupiter.api.Test;

import static io.contek.invoker.ftx.api.rest.Constants.SUCCESS_FIELD;
import static io.contek.invoker.ftx.api.rest.Constants.USER_REST_API;
import static org.assertj.core.api.Assertions.assertThat;

class GetOrderHistoryTest {

  @Test
  void testGetOrderHistorySuccess() {
    final GetOrderHistory.Response response = USER_REST_API.getOrderHistory().submit();
    assertThat(response).hasFieldOrPropertyWithValue(SUCCESS_FIELD, true);
  }
}
