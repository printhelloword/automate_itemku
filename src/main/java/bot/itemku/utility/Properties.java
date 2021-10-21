package bot.itemku.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Properties {

    @Autowired
    public Properties() {
    }

    public static String botRefreshInterval;
    @Value("${bot.refresh.interval}")
    public void setbotRefreshInterval(String value) {
        botRefreshInterval = value;
    }

    public static String botProfile;
    @Value("${profile.firefox}")
    public void setBotProfile(String value) {
        botProfile = value;
    }

    public static String geckodriverPath;
    @Value("${path.geckodriver}")
    public void setGeckodriverPath(String value) {
        geckodriverPath = value;
    }
}
