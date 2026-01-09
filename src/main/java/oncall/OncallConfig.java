package oncall;

import oncall.controller.InputHandler;
import oncall.controller.IteratorInputTemplate;
import oncall.controller.OncallController;
import oncall.view.InputView;
import oncall.view.OutputView;

public class OncallConfig {

    private InputView inputView;
    private OutputView outputView;
    private IteratorInputTemplate iteratorInputTemplate;
    private InputHandler inputHandler;
    private OncallController oncallController;

    public InputView inputView() {
        if (inputView == null) {
            inputView = new InputView();
        }
        return inputView;
    }

    public OutputView outputView() {
        if (outputView == null) {
            outputView = new OutputView();
        }
        return outputView;
    }

    public IteratorInputTemplate iteratorInputTemplate() {
        if (iteratorInputTemplate == null) {
            iteratorInputTemplate = new IteratorInputTemplate(outputView());
        }
        return iteratorInputTemplate;
    }

    public InputHandler iteratorInputHandler() {
        if (inputHandler == null) {
            inputHandler = new InputHandler(inputView(), iteratorInputTemplate());
        }
        return inputHandler;
    }

    public OncallController oncallController() {
        if (oncallController == null) {
            oncallController = new OncallController(iteratorInputHandler(), outputView());
        }
        return oncallController;
    }
}
