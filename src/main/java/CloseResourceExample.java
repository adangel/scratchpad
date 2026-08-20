import java.util.concurrent.*;

public class CloseResourceExample {
  public void forkJoin() throws Exception {
    // ForkJoinPool is autocloseable since Java 19 - and you'll get a violation from CloseResource
    ForkJoinPool forkJoinPool = new ForkJoinPool();
    try {
        forkJoinPool
            .submit(() -> System.out.println("x"))
            .get();
    } finally {
        forkJoinPool.shutdown();
    }
  }

  public void executorService() throws Exception {
    // ExecutorService is autocloseable since Java 19 - and you'll get a violation from CloseResource
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    try {
      executorService
            .submit(() -> System.out.println("x"))
            .get();
    } finally {
      executorService.shutdown();
    }
  }
}
