package fabian.codecraft.controller;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ScriptController {
    private static final Logger LOGGER = LogManager.getLogger();

    public static ScriptController Instance;
    private ScriptEngineManager manager;

    public Map<String, String> languages = new HashMap();
    public Map<String, ScriptEngineFactory> factories = new HashMap();

    public ScriptController() {
        Instance = this;
        this.manager = new ScriptEngineManager();

        Iterator iterator = this.manager.getEngineFactories().iterator();

        while(iterator.hasNext()) {
            ScriptEngineFactory fac = (ScriptEngineFactory)iterator.next();

            try {
                if (!fac.getExtensions().isEmpty() && fac.getScriptEngine() instanceof Invocable) {
                    String ext = "." + (fac.getExtensions().get(0)).toLowerCase();
                    LOGGER.info(fac.getLanguageName() + ": " + ext);

                    this.languages.put(fac.getLanguageName(), ext);
                    this.factories.put(fac.getLanguageName().toLowerCase(), fac);
                }
            } catch (Throwable t) {
                LOGGER.info("Skipping " + fac.getLanguageName());
            }
        }
    }

    public ScriptEngine getEngineByName(String language) {
        ScriptEngineFactory fac = this.factories.get(language.toLowerCase());
        return fac == null ? null : fac.getScriptEngine();
    }

    public String eval(String script, Entity executor) {
        ScriptContainer container = new ScriptContainer(script);

        container.setExecutor(executor);
        container.run();

        return container.getConsoleLog();
    }
}
