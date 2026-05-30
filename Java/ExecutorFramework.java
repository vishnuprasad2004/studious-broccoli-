import java.util.List;
import java.util.concurrent.*;

public class ExecutorFramework {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Callable<String> task = () -> {
            return "Hello";
        };
        Future<?> future = service.submit(task);
        System.out.println("World");
        System.out.println(future.get());
        System.out.println(service.submit(() -> System.out.println("print statement"), "DONE"));

        List<Callable<Integer>> tasks = List.of(
                () -> {
                    Thread.sleep(1000);
                    return 1;
                },
                () -> {
                    Thread.sleep(1000);
                    return 2;
                },
                () -> {
                    Thread.sleep(1000);
                    return 3;
                }
        );

        List<Future<Integer>> futures = service.invokeAll(tasks,1000, TimeUnit.MILLISECONDS);
        futures.forEach(f -> {
            try {
                System.out.println(f.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        service.shutdown();
        service.awaitTermination(100, TimeUnit.MILLISECONDS);
    }
}
