package de.epischel.fefe;

import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.nio.channels.Channels;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * von der webseite http://ptrace.fefe.de/wp/
 */
public class WPCK2 {
	
	final static int N_CONSUMERS = Runtime.getRuntime().availableProcessors();

    public static void main(final String[] args) throws Exception {
        final long timestamp = System.currentTimeMillis();
        
        final BlockingQueue<String> queue = new LinkedBlockingQueue<>(1024);
        final LineConsumer[] consumer = new LineConsumer[N_CONSUMERS];
        for (int j = 0; j < N_CONSUMERS; j++) {
            LineConsumer lineConsumer = new LineConsumer(queue);
            consumer[j]=lineConsumer;
			new Thread(lineConsumer).start();
        }
        
        System.out.println("created threads");
        System.err.println("time: " + (System.currentTimeMillis() - timestamp));
        
        try (final FileInputStream fis = new FileInputStream(InputData.INPUT_FILE_NAME)) {
                final BufferedReader reader = new BufferedReader(Channels.newReader(fis.getChannel(),
                                Charset.forName("ISO-8859-1").newDecoder(), 1048576), 262144);
                String line;
                while((line=reader.readLine())!=null) {
                	queue.put(line);                	
                }                
        }
        final Map<String, Integer> map = consumer[0].getCountMap();
        for(LineConsumer c : consumer) {
        	c.setFinished();        	
        }
        for(int i = 1; i < N_CONSUMERS; i++) {
        	for(Entry<String, Integer> entry : consumer[i].getCountMap().entrySet()) {
        		map.merge(entry.getKey(), entry.getValue(), (oldValue, value) -> oldValue+value);
        	}
        }
        
        
        final StringBuilder sb = new StringBuilder();
        map.entrySet().stream().parallel().sorted((a, b) -> b.getValue() - a.getValue()).sequential()
        	.forEach(e -> sb.append(e.getValue()).append(' ').append(e.getKey()).append('\n'));
        System.out.println(sb.toString());
        
        System.err.println("time: " + (System.currentTimeMillis() - timestamp));
        System.out.println(N_CONSUMERS);
    }

}

class LineConsumer implements Runnable {
	private final BlockingQueue<String> queue;
    private final Map<String, Integer> countMap;
    private boolean finished = false;
    
    public LineConsumer(final BlockingQueue<String> queue) {
		this.queue = queue;
		this.countMap = new HashMap<>(0xFFFF);
	}

	@Override
	public void run() {
		try {
            while (!finished) {
                String line = queue.poll(100, TimeUnit.MILLISECONDS);
                if (line==null) {
                	//System.out.println("null");
                	continue;
                }
                for(final String word : line.split(" ")) {
                	countMap.merge(word, 1, (oldVal, val) -> oldVal + 1);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
		
	}
	
	Map<String, Integer> getCountMap() {
		return countMap;
	}
    
    void setFinished() {
    	finished = true;
    }
}