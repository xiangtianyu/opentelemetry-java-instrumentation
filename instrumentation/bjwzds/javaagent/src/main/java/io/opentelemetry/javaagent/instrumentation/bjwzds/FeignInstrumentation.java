package io.opentelemetry.javaagent.instrumentation.bjwzds;

import feign.RequestTemplate;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import io.opentelemetry.javaagent.bootstrap.Java8BytecodeBridge;
import io.opentelemetry.javaagent.extension.instrumentation.TypeInstrumentation;
import io.opentelemetry.javaagent.extension.instrumentation.TypeTransformer;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

import static io.opentelemetry.javaagent.instrumentation.bjwzds.FeignSingleton.instrumenter;
import static net.bytebuddy.matcher.ElementMatchers.named;

public class FeignInstrumentation implements TypeInstrumentation {
  @Override
  public ElementMatcher<TypeDescription> typeMatcher() {
    return named("feign.SynchronousMethodHandler");
  }

  @Override
  public void transform(TypeTransformer transformer) {
    transformer.applyAdviceToMethod(
        named("executeAndDecode"), this.getClass().getName() + "$RequestAdvice");
  }

  @SuppressWarnings("unused")
  public static class RequestAdvice {

    @Advice.OnMethodEnter(suppress = Throwable.class)
    public static void addRequestEnter(
        @Advice.Argument(0) RequestTemplate template,
        @Advice.Local("otelContext") Context context,
        @Advice.Local("otelScope") Scope scope) {
      Context parentContext = Java8BytecodeBridge.currentContext();

      if (!instrumenter().shouldStart(parentContext, template)) {
        return;
      }

      context = instrumenter().start(parentContext, template);
      scope = context.makeCurrent();
    }

    @Advice.OnMethodExit(onThrowable = Throwable.class, suppress = Throwable.class)
    public static void addRequestExit(
        @Advice.Argument(0) RequestTemplate template,
        @Advice.Thrown Throwable exception,
        @Advice.Local("otelContext") Context context,
        @Advice.Local("otelScope") Scope scope) {
      if (scope == null) {
        return;
      }

      scope.close();

      instrumenter().end(context, template, null, exception);
    }
  }
}
