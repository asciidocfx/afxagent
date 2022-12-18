package com.kodedu.afxagent;

import java.lang.instrument.Instrumentation;
import java.util.concurrent.CountDownLatch;

public class InstrumentFactory {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void agentmain(String agentArgs, Instrumentation instrumentation) {
        inst = instrumentation;
        countDownLatch.countDown();
    }

    public static void premain(String agentArgs, Instrumentation instrumentation) {
        inst = instrumentation;
        countDownLatch.countDown();
    }

    private static Instrumentation inst;

    public static Instrumentation getInstrumentation() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return inst;
    }
}
