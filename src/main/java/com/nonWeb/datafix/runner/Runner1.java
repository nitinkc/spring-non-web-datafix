package com.nonWeb.datafix.runner;

import com.google.common.collect.Lists;
import com.nonWeb.datafix.fileDataFix.DataWriteIntoFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.nonWeb.datafix.util.DateTimeUtil.getLocalDateTime;

@Component
@Slf4j
@RequiredArgsConstructor
@Order(value = 1)
@ConditionalOnExpression("${myDataFix1:false}")
public class Runner1 implements CommandLineRunner {
    //Make fine to be used with Required Args Constructor
    private final DataWriteIntoFile dataWriteIntoFile;

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting Runner 1");
        //Do processing
        doDataProcessing();
        log.info(("Ending Runner 1 at : " + getLocalDateTime()));
    }

    private void doDataProcessing() {
        final int BATCH_SIZE = 1000;

        //Read from a CSV file and Write into another
        String inputFile = "src/main/resources/file/word-list.txt";
        Path inputPath = Paths.get(inputFile);
        String outputFile = "src/main/resources/file/output.txt";
        Path outputPath = Paths.get(outputFile);

        List<String> stringList = new ArrayList<>();
        try {
            stringList  = Files.lines(inputPath).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<List<String>> partition= Lists.partition(stringList, BATCH_SIZE);

        long startP = System.currentTimeMillis();
        partition.forEach(singleStringList -> {
            singleStringList.parallelStream()
                    .forEach(singleString -> dataWriteIntoFile.doSomethingParallely(singleString, outputPath));
        });
        long endP = System.currentTimeMillis();

        long startS = System.currentTimeMillis();
        stringList.forEach(str -> dataWriteIntoFile.doSomethingParallely(str, outputPath));
        long endS = System.currentTimeMillis();

        results(stringList.size(), partition.size(), BATCH_SIZE, endP - startP, endS - startS);
        System.out.println("Processing Ended");
    }

    private static void results(int size, int partitionSize, int BATCH_SIZE, long parallelTime, long sequentialTime) {
        final String FILENAME = "src/main/resources/file/nitin.txt";
        PrintWriter output = null;
        // Erasing files if already exist
        try {
            output = new PrintWriter(new FileWriter(FILENAME, true));
        } catch (IOException e) {
            e.printStackTrace();
        }

        output.println("###########################################################################");
        output.println(getLocalDateTime());
        output.println("Total # of Strings " + size + " with total # Partition is " + partitionSize);
        output.println("Batch Size : " + BATCH_SIZE);
        output.println("Total Time Taken in parallel : " + parallelTime);
        output.println("Total Time Taken in sequential : " + sequentialTime);

        // Does not write without flush
        output.flush();
        output.close();
    }
}
