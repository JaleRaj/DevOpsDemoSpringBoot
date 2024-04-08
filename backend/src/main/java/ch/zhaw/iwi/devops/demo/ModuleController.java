package ch.zhaw.iwi.devops.demo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ModuleController {

    private Map<Integer, Module> modules = new HashMap<Integer, Module>();

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        this.modules.put(1,new Module(1, "aF&E", "FS24"));
        this.modules.put(2,new Module(2, "ITS", "HS23"));
        this.modules.put(3,new Module(3, "EPA", "HS23"));
        this.modules.put(4,new Module(4, "DSC", "FS24"));
        System.out.println("Init Data");
    }

    @GetMapping("/testmodule")
    public String test() {
        return "module app is up and running!";
    }

    @GetMapping("/services/modules/ping")
    public String ping() {
        String languageCode = "de";
        return "{ \"status\": \"ok\", \"userId\": \"admin"+ "\", \"languageCode\": \"" + languageCode + "\",\"version\": \"0.0.1" + "\"}";
    }

    @GetMapping("/modules/count")
    public int count() {
        return this.modules.size();
    }

    @GetMapping("/services/module")
    public List<PathListEntryModule<Integer>> module() {
        var result = new ArrayList<PathListEntryModule<Integer>>();
        for (var module : this.modules.values()) {
            var entry = new PathListEntryModule<Integer>();
            entry.setKey(module.getId(), "moduleKey");
            entry.setName(module.getTitle());
            entry.getDetails().add(module.getDescription());
            entry.setTooltip(module.getDescription());
            result.add(entry);
        }
        return result;
    }

    @GetMapping("/services/module/{key}")
    public Module getModule(@PathVariable Integer key) {
        return this.modules.get(key);
    }

    @PostMapping("/services/module")
    public void createmodule(@RequestBody Module module) {
        var newId = this.modules.keySet().stream().max(Comparator.naturalOrder()).orElse(0) + 1;
        module.setId(newId);
        this.modules.put(newId, module);
    }

    @PutMapping("/services/module/{id}")
    public void createmodule(@PathVariable Integer id, @RequestBody Module module) {
        module.setId(id);
        this.modules.put(id, module);
    }

    @DeleteMapping("/services/module/{key}")
    public Module deleteModule(@PathVariable Integer key) {
        return this.modules.remove(key);
    }


}
