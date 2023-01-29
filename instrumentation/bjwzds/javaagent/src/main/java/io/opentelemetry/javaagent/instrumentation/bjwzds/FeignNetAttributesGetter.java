package io.opentelemetry.javaagent.instrumentation.bjwzds;

import feign.RequestTemplate;
import io.opentelemetry.instrumentation.api.instrumenter.net.NetClientAttributesGetter;
import javax.annotation.Nullable;

public class FeignNetAttributesGetter implements NetClientAttributesGetter<RequestTemplate, Void> {
  @Nullable
  @Override
  public String transport(RequestTemplate requestTemplate, @Nullable Void unused) {
    return "transport";
  }

  @Nullable
  @Override
  public String peerName(RequestTemplate requestTemplate) {
    return "peerName";
  }

  @Nullable
  @Override
  public Integer peerPort(RequestTemplate requestTemplate) {
    return 10000;
  }
}
