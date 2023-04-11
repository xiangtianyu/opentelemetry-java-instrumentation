package io.opentelemetry.javaagent.instrumentation.demo;

import com.google.auto.service.AutoService;
import io.opentelemetry.javaagent.extension.instrumentation.InstrumentationModule;
import io.opentelemetry.javaagent.extension.instrumentation.TypeInstrumentation;
import java.util.Arrays;
import java.util.List;

@AutoService(InstrumentationModule.class)
public class MineInstrumentationModule extends InstrumentationModule {
  public MineInstrumentationModule() {
    super("mine", "mine-1.0");
  }

  @Override
  public List<TypeInstrumentation> typeInstrumentations() {
    return Arrays.asList(new MineHeaderInstrumentation(), new MineEventInstrumentation());
  }
}
