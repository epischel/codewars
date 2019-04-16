package de.epischel.fefe;

import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.nio.channels.Channels;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * von der webseite http://ptrace.fefe.de/wp/
 */
public class WPCK {

    public static void main(final String[] args) throws Exception {
        final long timestamp = System.currentTimeMillis();
        
        final Map<String, Integer> map = new ConcurrentHashMap<>(1048576);
        try (final FileInputStream fis = new FileInputStream(InputData.INPUT_FILE_NAME)) {
                final Stream<String> s = new BufferedReader(Channels.newReader(fis.getChannel(),
                                Charset.forName("ISO-8859-1").newDecoder(), 1048576), 262144).lines().parallel();
                
                s.map(line -> new StringTokenizer(line, " ")).forEach(st -> {
                        while (st.hasMoreTokens()) {
                                map.merge(st.nextToken(), 1, (oldVal, val) -> oldVal + 1);
                        }
                });
        }
         
        final StringBuilder sb = new StringBuilder();
        map.entrySet().stream().parallel().sorted((a, b) -> b.getValue() - a.getValue()).sequential()
                        .forEach(e -> sb.append(e.getValue()).append(' ').append(e.getKey()).append('\n'));
        System.out.println(sb.toString());
        
        System.err.println("time: " + (System.currentTimeMillis() - timestamp));
}
    
}
