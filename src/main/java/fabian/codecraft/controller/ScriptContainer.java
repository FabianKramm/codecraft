package fabian.codecraft.controller;

import fabian.codecraft.api.Api;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.script.ScriptEngine;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.TreeMap;
import java.util.function.Function;

public class ScriptContainer {
    public static Entity CurrentExecutor;
    private static final Logger LOGGER = LogManager.getLogger();

    private Entity executor;
    private TreeMap<Long, String> console = new TreeMap<>();
    private boolean hasError = false;
    private ScriptEngine engine = null;
    private boolean init = false;
    private String script;
    private String language;

    public ScriptContainer(String script) {
        this.script = script;

        if (ScriptController.Instance != null && ScriptController.Instance.languages.size() > 0) {
            this.language = (String)ScriptController.Instance.languages.keySet().toArray()[0];
        }
    }

    public ScriptContainer(String script, String language) {
        this.script = script;
        this.language = language;
    }

    public void setExecutor(Entity executor) {
        this.executor = executor;
    }

    public void run() {
        if (!this.hasError && this.language != null) {
            this.setEngine(this.language);
            if (this.engine != null) {
                synchronized("lock") {
                    if (this.executor != null) {
                        ScriptContainer.CurrentExecutor = this.executor;
                    }

                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    this.engine.getContext().setWriter(pw);
                    this.engine.getContext().setErrorWriter(pw);

                    try {
                        if (!this.init) {
                            this.engine.eval(this.script);
                            this.init = true;
                        }

                        // ((Invocable)this.engine).invokeFunction();
                    } catch (Throwable t) {
                        this.hasError = true;

                        this.appendConsole(t.getMessage());
                        // t.printStackTrace(pw);
                        // ServerUtils.NotifyOPs(pw.toString());
                    } finally {
                        this.appendConsole(sw.getBuffer().toString().trim());
                        pw.close();
                    }

                    ScriptContainer.CurrentExecutor = null;
                }
            }
        }
    }

    public void appendConsole(String message) {
        if (message != null && !message.isEmpty()) {
            long time = System.currentTimeMillis();
            if (this.console.containsKey(time)) {
                message = this.console.get(time) + "\n" + message;
            }

            this.console.put(time, message);

            while(this.console.size() > 40) {
                this.console.remove(this.console.firstKey());
            }
        }
    }

    public void setEngine(String scriptLanguage) {
         this.engine = ScriptController.Instance.getEngineByName(scriptLanguage);
            if (this.engine == null) {
                this.hasError = true;
            } else {
                // Iterator var2 = Data.entrySet().iterator();
                // while(var2.hasNext()) {
                //    this.engine.put((String)entry.getKey(), entry.getValue());
                // }

                this.engine.put("log", new ScriptContainer.Log());
                this.engine.put("api", Api.class);

                this.init = false;
            }
    }

    public boolean isValid() {
        return this.init && !this.hasError;
    }

    public String getConsoleLog() {
        return String.join("\n", this.console.values().toArray(new String[0]));
    }

    private class Log implements Function<Object, Void> {
        private Log() { }

        public Void apply(Object o) {
            ScriptContainer.this.appendConsole(o + "");
            LOGGER.info(o + "");
            return null;
        }
    }
}
