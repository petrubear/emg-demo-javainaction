package emg.demo.jiact.cap15;

import java.util.concurrent.*;

public class FutureSamples {
    private ExecutorService executorService;
    int sleep1 = 2000;
    int sleep2 = 6000;

    private void setUp() {
        executorService = Executors.newFixedThreadPool(8);
    }

    private void shutDown() {
        executorService.shutdown();
    }

    private void log(String message, long from) {
        System.out.println("[from: " + from + "]: " + message);
    }

    private void log(String message) {
        log(message, Thread.currentThread().getId());
    }

    private int f(int x) {
        try {
            log("f(x)");
            Thread.sleep(sleep1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 10;
    }

    private int g(int x) {
        try {
            log("g(x)");
            Thread.sleep(sleep2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 7;
    }

    private int directCall(int x) {
        log("*****************************************************");
        log("directCall(x)");
        int y = f(x);
        int z = g(x);
        return y + z;
    }

    private Future<Integer> fp(int x) {
        return executorService.submit(() -> f(x));
    }

    private Future<Integer> gp(int x) {
        return executorService.submit(() -> g(x));
    }

    private int completableFutureCall(int x) {
        log("*****************************************************");
        log("completableFutureCall(x)");
        var f = new CompletableFuture<Integer>();
        executorService.submit(() -> f.complete(f(x)));
        var g = new CompletableFuture<Integer>();
        executorService.submit(() -> g.complete(g(x)));

        var z = f.thenCombine(g, Integer::sum);

        try {
            return z.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private int futureCall(int x) {
        log("*****************************************************");
        log("futureCall(x)");
        try {
            var y = fp(x);
            var z = gp(x);
            return y.get() + z.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void main(String... args) {
        final int x = 1337;
        FutureSamples samples = new FutureSamples();
        samples.setUp();

        long time = System.currentTimeMillis();
        try {
            int result = samples.directCall(x);
            System.out.println("result = " + result);
        } finally {
            time = System.currentTimeMillis() - time;
            System.out.println(String.format("[time] = %ss", time / 1000));
        }

        time = System.currentTimeMillis();
        try {
            int result = samples.futureCall(x);
            System.out.println("result = " + result);
        } finally {
            time = System.currentTimeMillis() - time;
            System.out.println(String.format("[time] = %ss", time / 1000));
        }

        time = System.currentTimeMillis();
        try {
            int result = samples.completableFutureCall(x);
            System.out.println("result = " + result);
        } finally {
            time = System.currentTimeMillis() - time;
            System.out.println(String.format("[time] = %ss", time / 1000));
        }

        samples.shutDown();
    }
}
