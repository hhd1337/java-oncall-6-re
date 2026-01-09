package oncall;

import oncall.controller.OncallController;

public class Application {
    public static void main(String[] args) {
        OncallConfig oncallConfig = new OncallConfig();
        OncallController oncallController = oncallConfig.oncallController();
        oncallController.process();
    }
}
