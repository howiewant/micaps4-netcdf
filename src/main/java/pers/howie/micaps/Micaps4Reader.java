package pers.howie.micaps;

import pers.howie.micaps.entity.Micaps4;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author howie
 * @since 2023/6/8
 */
public class Micaps4Reader {
    private final File file;

    public Micaps4Reader(File file) {
        this.file = file;
    }

    public Micaps4 read() throws IOException {
        Micaps4.Builder builder = new Micaps4.Builder();
        List<String> data = new ArrayList<>();
        try (LineNumberReader reader = new LineNumberReader(new FileReader(file))) {
            String s;
            while ((s = reader.readLine()) != null) {
                if (reader.getLineNumber() == 1) {
                    if (!s.startsWith("diamond 4")) {
                        throw new RuntimeException("不是micaps 四类数据");
                    }
                } else if (reader.getLineNumber() == 2) {
                    builder.header(s);
                } else {
                    data.add(s);
                }
            }
        }
        return builder.data(data).build();
    }
}
