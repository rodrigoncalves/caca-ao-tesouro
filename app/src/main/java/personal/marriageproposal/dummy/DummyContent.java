package personal.marriageproposal.dummy;

import android.content.Context;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import personal.marriageproposal.db.DataBaseHelper;

/**
 * Helper class for providing sample title for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {
    private List<Hint> hints_list;
    private HashMap<String, Hint> hints_map;

    public DummyContent(Context context) {
        DataBaseHelper db = new DataBaseHelper(context);
        List<Hint> hints = db.getAllHints();

        this.hints_list = new LinkedList<>();
        this.hints_map = new HashMap<>();
        for (Hint item : hints) {
            if (item.status == 1) {
                this.hints_list.add(item);
                this.hints_map.put(item.id, item);

            }
        }
    }

    /**
     * An array of sample (dummy) items.
     */
    public List<Hint> getItems() {
        return hints_list;
    }

    /**
     * A map of sample (dummy) items, by ID.
     */
    public Map<String, Hint> getItemsMap() {
        return hints_map;
    }

    /**
     * A dummy item representing a piece of title.
     */
    public static class Hint {
        public final String id;
        public final String title;
        public final String details;
        public final String password;
        public final int status;

        public Hint(String id, String title, String details, String password, int status) {
            this.id = id;
            this.title = title;
            this.details = details;
            this.password = password;
            this.status = status;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
