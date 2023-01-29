package io.opentelemetry.javaagent.instrumentation.bjwzds;

import feign.RequestTemplate;
import io.opentelemetry.context.propagation.TextMapSetter;
import javax.annotation.Nullable;

enum HttpHeaderSetter implements TextMapSetter<RequestTemplate> {
  INSTANCE;

  @Override
  public void set(@Nullable RequestTemplate carrier, String key, String value) {
    if (carrier == null) {
      return;
    }
    carrier.header(key, value);
  }
}