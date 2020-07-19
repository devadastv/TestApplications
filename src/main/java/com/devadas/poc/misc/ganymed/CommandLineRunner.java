package com.devadas.poc.misc.ganymed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.ethz.ssh2.StreamGobbler;

public class CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(CommandLineRunner.class);

    public static void execAsyncCmdWithCallback(final String command, final Callback callback, final Map<String, Object> arguments) throws IOException {
        executeAsyncCmdWithCallback(command, callback, arguments);
    }

    public static void execAsyncCmdWithCallback(final Callback callback, final Map<String, Object> arguments, String... command) throws IOException {
        executeAsyncCmdWithCallback(command, callback, arguments);
    }

    private static void executeAsyncCmdWithCallback(final Object command, final Callback callback, final Map<String, Object> arguments) throws IOException {
        Process p;
        logger.info("exec cmdline in aysnc: {}", command);
        if (command instanceof String) {
            p = Runtime.getRuntime().exec((String) command);
        } else {
            p = Runtime.getRuntime().exec((String[]) command);
        }
        try (final StreamGobbler esg = new StreamGobbler(p.getErrorStream());
            final InputStreamReader esr = new InputStreamReader(esg);
            final BufferedReader errReader = new BufferedReader(esr);
            final StreamGobbler isg = new StreamGobbler(p.getInputStream());
            final InputStreamReader isr = new InputStreamReader(isg);
            final BufferedReader inputReader = new BufferedReader(isr)) {
            arguments.put("errReader", errReader);
            arguments.put("inputReader", inputReader);
            arguments.put("command", command);
            Worker worker = new Worker(p, callback, arguments);
            worker.start();
        }
    }

    public static Object[] execCmdlineWithTimeout(String command, long responseTimeout)
            throws IOException, TimeoutException, InterruptedException {
        return executeCmdlineWithTimeout(command, responseTimeout);
    }

    public static Object[] execCmdlineWithTimeout(long responseTimeout, String... command)
            throws IOException, TimeoutException, InterruptedException {
        return executeCmdlineWithTimeout(command, responseTimeout);
    }

    
    //execute a command line with a defined timeout in ms sec
    private static Object[] executeCmdlineWithTimeout(Object command, long responseTimeout)
            throws IOException, TimeoutException, InterruptedException {
        logger.info("exec cmdline: {} in {} ms", command, responseTimeout);

        Process p;
        if (command instanceof String) {
            p = Runtime.getRuntime().exec((String) command);
        } else {
            p = Runtime.getRuntime().exec((String[]) command);
        }

        try (final StreamGobbler esg = new StreamGobbler(p.getErrorStream());
             final InputStreamReader esr = new InputStreamReader(esg);
             final BufferedReader errReader = new BufferedReader(esr);
             final StreamGobbler isg = new StreamGobbler(p.getInputStream());
             final InputStreamReader isr = new InputStreamReader(isg);
             final BufferedReader inputReader = new BufferedReader(isr)) {

            Worker worker = new Worker(p);
            worker.start();
            try {
                String line;
                StringBuilder output = new StringBuilder();
                StringBuilder error = new StringBuilder();
                while ((line = inputReader.readLine()) != null) {
                    output.append(line + "\n");
                }
    
                while ((line = errReader.readLine()) != null) {
                    error.append(line + "\n");
                }
    
                worker.join(responseTimeout);
    
                logger.info("cmd:\n{}\nexitcode:\n{}\nstdout:\n{}\nstderr:\n{}", command, worker.exit, output, error);
    
                if (worker.exit != null) {
                    return new Object[]{worker.exit, output.toString(), error.toString()};
                } else {
                    logger.info("exec cmdline exit code is null, probably timedout");
                    throw new TimeoutException();
                }
            } catch (InterruptedException ex) {
                worker.interrupt();
                Thread.currentThread().interrupt();
                throw ex;
            }
        } finally {
            try {
                p.destroy();
            } catch (Exception t) {
                //ignore
            }
        }
    }

    private interface Callback {
        void runCallbackMethod(Integer exit, String stdout, String stderr, Map<String, Object> arguments);
    }

    private static class Worker extends Thread {
        private final Process process;
        private Map<String, Object> arguments;
        private Integer exit;
        private Callback callback;
        StringBuilder output = new StringBuilder();
        StringBuilder error = new StringBuilder();

        private Worker(Process process) {
            this(process, null, null);
        }

        private Worker(Process process, Callback callback, Map<String, Object> arguments) {
            this.process = process;
            this.callback = callback;
            this.arguments = arguments;
        }

        @Override
        public void run() {
            try {
                if (arguments != null && arguments.size() > 0) {
                    try (final BufferedReader errReader = (BufferedReader) arguments.get("errReader");
                         final BufferedReader inputReader = (BufferedReader) arguments.get("inputReader")) {
                        String line;
                        while ((line = inputReader.readLine()) != null) {
                            output.append(line + "\n");
                        }

                        while ((line = errReader.readLine()) != null) {
                            error.append(line + "\n");
                        }
                    } catch (IOException e) {
                        logger.error("Exception occurred reading stdout and stderr", e);
                    }
                }
                exit = process.waitFor();
            } catch (InterruptedException ignore) {
                Thread.currentThread().interrupt();
            } finally {
                logger.info("Worker finished task, calling callback: {}", callback);
                if (callback != null) {
                    callback.runCallbackMethod(exit, output.toString(), error.toString(), arguments);
                    process.destroy();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        execAsyncCmdWithCallback((exit, stdout, stderr, arguments) -> {
            logger.info("==================call back is called===================");
            logger.info("{}", exit);
            logger.info("stdout>>> {}", stdout);
            logger.info("stderr>>> {}", stderr);
            logger.info("{}", Arrays.asList(arguments));
        },
        new HashMap<String, Object>(),
        "help");
    }
}
