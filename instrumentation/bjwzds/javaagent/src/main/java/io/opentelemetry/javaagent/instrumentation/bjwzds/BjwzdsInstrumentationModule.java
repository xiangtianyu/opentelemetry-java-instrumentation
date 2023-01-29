package io.opentelemetry.javaagent.instrumentation.bjwzds;

import com.google.auto.service.AutoService;
import io.opentelemetry.javaagent.extension.instrumentation.InstrumentationModule;
import io.opentelemetry.javaagent.extension.instrumentation.TypeInstrumentation;
import java.util.Arrays;
import java.util.List;

@AutoService(InstrumentationModule.class)
public class BjwzdsInstrumentationModule extends InstrumentationModule {
  public BjwzdsInstrumentationModule() {
    super("bjwzds", "bjwzds-1.0");
  }

  @Override
  public List<TypeInstrumentation> typeInstrumentations() {
    return Arrays.asList(new BjwzdsInstrumentation(), new FeignInstrumentation());
  }
}
