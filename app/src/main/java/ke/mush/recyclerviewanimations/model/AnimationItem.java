package ke.mush.recyclerviewanimations.model;

/**
 * Created by mush on 2/9/18.
 */

public class AnimationItem {
    private final String name;
    private final int resourceId;

    public AnimationItem(String name, int resourceId) {
        this.name = name;
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public int getResourceId() {
        return resourceId;
    }
}
