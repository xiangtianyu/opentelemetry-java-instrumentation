package io.opentelemetry.javaagent.instrumentation.bjwzds;

import io.opentelemetry.context.Context;
import io.opentelemetry.context.ContextKey;
import io.opentelemetry.context.propagation.TextMapGetter;
import io.opentelemetry.context.propagation.TextMapPropagator;
import io.opentelemetry.context.propagation.TextMapSetter;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Collections;

public class ColorPropagator implements TextMapPropagator {
  private static final String FIELD = "color";
  private static final ContextKey<String> PROPAGATION_KEY =
      ContextKey.named("propagation.color");

  @Override
  public Collection<String> fields() {
    return Collections.singletonList(FIELD);
  }

  @Override
  public <C> void inject(Context context, @Nullable C carrier, TextMapSetter<C> setter) {
    String v = context.get(PROPAGATION_KEY);

    if (v != null) {
      setter.set(carrier, FIELD, v);
    }
  }

  @Override
  public <C> Context extract(Context context, @Nullable C carrier, TextMapGetter<C> getter) {
    String v = getter.get(carrier, FIELD);
    if (v != null) {
      return context.with(PROPAGATION_KEY, v);
    } else {
      return context;
    }
  }
}
