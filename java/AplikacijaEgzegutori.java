/**
 * 
 */
package aplikacije;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionService;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Vitor 2
 *
 */
public class AplikacijaEgzegutori {
	
	Integer broj1;
	Integer broj2;
	Integer broj3;
	Integer broj4;
	
	CompletableFuture<String> processStringAsync(String input){
		Executor executor = Executors.newSingleThreadExecutor();
		CompletableFuture<String> future = 
			    CompletableFuture.supplyAsync(() -> input.trim() , executor);
		return future;
	}
	
	Future<Integer> runTaskOnASingleExecutor(Integer x, Integer y){
		Executor executor = Executors.newSingleThreadExecutor();
		CompletionService<Integer> service = new ExecutorCompletionService<Integer>(executor);
		Future<Integer> result = service.submit(getCallableTask(x,y));
		return result;
	}
	
	Future<Integer> runTaskOnAnExecutorService(Integer x, Integer y){
		ExecutorService service = Executors.newFixedThreadPool(4);
		Future<Integer> result = service.submit(getCallableTask(x,y));
		service.shutdown();
		return result;
	}
	
	CompletableFuture<Integer> runTaskForCompletableFutureResult(Integer x, Integer y){
		ExecutorService service = Executors.newFixedThreadPool(4);
		CompletableFuture<Integer> result = new CompletableFuture<Integer>();
		service.execute(() -> {
			try{
				result.complete(getCallableTask(x,y).call());
			}
			catch (Throwable t){
				result.completeExceptionally(t);
			}
		});
		return result;
	}
	
	CompletableFuture<Integer> runTaskForCompletableFutureResultScheduled(Integer x, Integer y){
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		CompletableFuture<Integer> result = new CompletableFuture<Integer>();
		executor.schedule(() -> {
			try{
				result.complete(getCallableTask(x,y).call());
			}
			catch (Throwable t){
				result.completeExceptionally(t);
			}
		},2, TimeUnit.SECONDS);
		return result;
	}
	
	Callable<Integer> getCallableTask(Integer x, Integer y){
		class Task implements Callable<Integer>{
			Integer x;
			Integer y;
			Task(Integer x,Integer y){
				this.x = x;
				this.y = y;
			}
			public Integer call(){
				return x + y;
			}	
		}
		return new Task(x,y);
	}
	
	Object notify(Object result){
		System.out.println((Integer)result);
		if(this.broj3 == null){
			broj3 = (Integer) result;
			return result;
		}
		if(this.broj4 == null){
			broj4 = (Integer) result;
			return result;
		}
 		return result;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args){
		AplikacijaEgzegutori app = new AplikacijaEgzegutori();
		
		
		
		Future<Integer> result = app.runTaskOnASingleExecutor(1,2);
		Future<Integer> result2 = app.runTaskOnAnExecutorService(3, 4);
		
		//blocking call za broj1
		try{
			app.broj1 = result.get();
		}
		catch (Exception e){
			app.broj1 = -1;
		}
		
		//blocking call za broj1
		try{
			app.broj2 = result2.get();
		}
		catch (Exception e){
			app.broj2 = -1;
		}
	
		CompletableFuture<Integer> result3 = app.runTaskForCompletableFutureResult(5,6);
		//result3.thenApply(app::notify);
		result3.thenAccept(r -> app.broj3 = r);
		CompletableFuture<Integer> result4 = app.runTaskForCompletableFutureResultScheduled(7,8);
		//result4.thenApply(app::notify);
		result4.thenAccept(r -> app.broj4 = r);
		
		/**
		 * thenApply prosledjuje rezultat kao parametar funkciji koja se pozove sa method reference\
		 * thenAccept sluzi za setovanje rezultata
		 */
		
		System.out.println("helou wrld");
		
		CompletableFuture<String> trimovanString = app.processStringAsync(" neki string ");
		
		trimovanString.thenApply(s -> {
			System.out.println(s); return s; //moze da pozove metodu npr app::nekaMetoda i automatski ce joj prosledidi rezultat s
		});

		trimovanString.thenAccept(s -> System.out.println(s));

	}

}

