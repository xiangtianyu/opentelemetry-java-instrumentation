package io.opentelemetry.javaagent.instrumentation.bjwzds;

import feign.RequestTemplate;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.instrumentation.api.instrumenter.Instrumenter;
import io.opentelemetry.instrumentation.api.instrumenter.http.HttpClientAttributesExtractor;
import io.opentelemetry.instrumentation.api.instrumenter.http.HttpClientAttributesGetter;
import io.opentelemetry.instrumentation.api.instrumenter.http.HttpSpanNameExtractor;
import io.opentelemetry.instrumentation.api.instrumenter.net.NetClientAttributesGetter;

public class FeignSingleton {
  private static final String INSTRUMENTATION_NAME = "io.opentelemetry.feign-1.0";
  private static final Instrumenter<RequestTemplate, Void> INSTRUMENTER;

  static {
    HttpClientAttributesGetter<RequestTemplate, Void> httpAttributesGetter =
        new FeignHttpAttributesGetter();

    NetClientAttributesGetter<RequestTemplate, Void> netAttributesGetter =
        new FeignNetAttributesGetter();

    INSTRUMENTER =
        Instrumenter.<RequestTemplate, Void>builder(
                GlobalOpenTelemetry.get(),
                INSTRUMENTATION_NAME,
                HttpSpanNameExtractor.create(httpAttributesGetter))
            .addAttributesExtractor(
                HttpClientAttributesExtractor.builder(httpAttributesGetter, netAttributesGetter).build())
            .buildClientInstrumenter(HttpHeaderSetter.INSTANCE);
  }

  public static Instrumenter<RequestTemplate, Void> instrumenter() {
    return INSTRUMENTER;
  }

  private FeignSingleton() {}
}
