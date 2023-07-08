package Code;

import java.util.Comparator;

public class ApplicationComparator implements Comparator<Application> {
    @Override
    public int compare(Application a1, Application a2) {
        return a1.getSubmissionTime().compareTo(a2.getSubmissionTime());
    }
}
